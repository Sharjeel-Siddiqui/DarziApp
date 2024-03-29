package com.example.dulha_jee.userlist;

import android.os.Bundle;
import android.text.TextUtils;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.navigation.NavController;
import androidx.navigation.Navigation;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.dulha_jee.MainActivity;
import com.example.dulha_jee.R;
import com.example.dulha_jee.SharedPreference;
import com.example.dulha_jee.api.ApiClient;
import com.example.dulha_jee.api.Iapi;
import com.example.dulha_jee.pojo.SearchResponseBody;
import com.example.dulha_jee.pojo.UpdateStatusRequestBody;
import com.google.android.material.floatingactionbutton.FloatingActionButton;

import java.util.ArrayList;
import java.util.List;

import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class UserListFragnment extends Fragment implements IonDeleteOrder{

    RecyclerView recyclerView;
    UserAdapter userAdapter;
    List<SearchResponseBody.user> usersList;
    FloatingActionButton add;
    NavController navController;
    Iapi iapi;
    SharedPreference sharedPreference;
    TextView nrf;
    IonDeleteOrder ionDeleteOrder;


    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_userlist, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        recyclerView = view.findViewById(R.id.rv_customerList);
        iapi = ApiClient.getClient().create(Iapi.class);
        sharedPreference = new SharedPreference(getActivity());
        navController = Navigation.findNavController(view);
        ((MainActivity) getActivity()).setToolbarVisibility(true);
        ((MainActivity) getActivity()).setToolbarhere("Dulha Jee");
        nrf = view.findViewById(R.id.nrf);




        if (getArguments() != null) { // via search
            Log.i("TAG", "userist: " + getArguments().getString("userList"));

            iapi.search("Bearer " + sharedPreference.getToken(), getArguments().getString("order_date"), getArguments().getString("customer_name"),
                    getArguments().getString("customer_ordernumber"),
                    getArguments().getString("karegar_name"), getArguments().getString("customer_number")).enqueue(new Callback<SearchResponseBody>() {
                @Override
                public void onResponse(Call<SearchResponseBody> call, Response<SearchResponseBody> response) {

                    if(response.body().getData().size() > 0) {
                        usersList = response.body().getData();
                        populateRv((ArrayList<SearchResponseBody.user>) usersList);
                        nrf.setVisibility(View.GONE);
                    }else{
                        nrf.setVisibility(View.VISIBLE);
                    }

                }

                @Override
                public void onFailure(Call<SearchResponseBody> call, Throwable t) {
                    Toast.makeText(getActivity(), "Failed", Toast.LENGTH_SHORT).show();
                }
            });


        } else { //first time open
            usersList = new ArrayList<>();
            iapi.search("Bearer " + sharedPreference.getToken(), "", "", "", "", "").enqueue(new Callback<SearchResponseBody>() {
                @Override
                public void onResponse(Call<SearchResponseBody> call, Response<SearchResponseBody> response) {

                 //   Toast.makeText(getActivity(), "Success" + response.code(), Toast.LENGTH_SHORT).show();

                    if (response.body().getData() != null) {
                        usersList = response.body().getData();
                        populateRv((ArrayList<SearchResponseBody.user>) usersList);
                        nrf.setVisibility(View.GONE);
                    }else{
                        nrf.setVisibility(View.VISIBLE);
                    }
                    //navigation.navigate(R.id.userList);
                }

                @Override
                public void onFailure(Call<SearchResponseBody> call, Throwable t) {
                    Toast.makeText(getActivity(), "Failed", Toast.LENGTH_SHORT).show();
                }
            });

        }

        add = view.findViewById(R.id.add);
        add.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                navController = Navigation.findNavController(view);
                navController.navigate(R.id.action_userList_to_dashBoard);
            }
        });

    }

    public void populateRv(ArrayList<SearchResponseBody.user> users) {
        userAdapter = new UserAdapter(getActivity(), users, navController,this);
        recyclerView.setAdapter(userAdapter);
    }

    public IonDeleteOrder getRef(){
        return this.ionDeleteOrder;
    }

    @Override
    public void onDeleteOrder(String order_id, int position) {
       // Toast.makeText(getActivity(), "Order Id is : " + order_id + " position is " + position , Toast.LENGTH_SHORT).show();

        userAdapter.notifyItemRemoved(position);
      //  userAdapter. notifyItemRangeChanged(position, mDataSet.size());;

        String s1 = order_id;
        String[] words=s1.split(":");//splits the string based on whitespace
        //using java foreach loop to print elements of string array

        UpdateStatusRequestBody updateStatusRequestBody = new UpdateStatusRequestBody();
        updateStatusRequestBody.setOrder_number(String.valueOf(order_id).trim());

        iapi.deleteOrder("Bearer " + sharedPreference.getToken(),updateStatusRequestBody).enqueue(new Callback<ResponseBody>() {
            @Override
            public void onResponse(Call<ResponseBody> call, Response<ResponseBody> response) {
                Toast.makeText(getActivity(), "Order Deleted...", Toast.LENGTH_SHORT).show();
            }

            @Override
            public void onFailure(Call<ResponseBody> call, Throwable t) {
                Toast.makeText(getActivity(), "Failure...", Toast.LENGTH_SHORT).show();
            }
        });
    }
}
