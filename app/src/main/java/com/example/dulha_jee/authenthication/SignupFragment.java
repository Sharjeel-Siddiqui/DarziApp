package com.example.dulha_jee.authenthication;

import android.app.ProgressDialog;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.navigation.NavController;
import androidx.navigation.Navigation;

import com.example.dulha_jee.MainActivity;
import com.example.dulha_jee.R;
import com.example.dulha_jee.SharedPreference;
import com.example.dulha_jee.api.ApiClient;
import com.example.dulha_jee.api.Iapi;
import com.example.dulha_jee.pojo.LoginResponseBody;
import com.example.dulha_jee.pojo.RegisterBody;

import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class SignupFragment extends Fragment {
    NavController navController;
    Button button;
    Iapi apiClient;
    EditText et_userName, et_userEmail, et_userPassword;
    ProgressDialog pd;
    SharedPreference sharedPreference;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_signup, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        navController = Navigation.findNavController(view);
        button = view.findViewById(R.id.btn_signup);
        et_userName = view.findViewById(R.id.et_userName);
        et_userEmail = view.findViewById(R.id.et_userEmail);
        et_userPassword = view.findViewById(R.id.et_userPassword);
        apiClient = ApiClient.getClient().create(Iapi.class);
        pd = new ProgressDialog(getActivity());
        pd.setMessage("Please wait...");
        pd.setTitle("Signing Up...");
        sharedPreference = new SharedPreference(getActivity());



        ((MainActivity) getActivity()).setToolbarVisibility(false);

        button.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View view) {

                RegisterBody registerBody = new RegisterBody(et_userName.getText().toString(), et_userEmail.getText().toString(), et_userPassword.getText().toString());
                pd.show();
                apiClient.registerUser(registerBody).enqueue(new Callback<LoginResponseBody>() {
                    @Override
                    public void onResponse(Call<LoginResponseBody> call, Response<LoginResponseBody> response) {
                        if (response.isSuccessful()) {
                            sharedPreference.saveToken(response.body().getApi_token());
                            Toast.makeText(getActivity(), "user registered...!", Toast.LENGTH_SHORT).show();
                            navController.navigateUp();
                            pd.show();
                        }
                        pd.dismiss();
                    }

                    @Override
                    public void onFailure(Call<LoginResponseBody> call, Throwable t) {
                        Log.i("TAG", "onFailure: " + t.getMessage());
                        Toast.makeText(getActivity(), "Failed...", Toast.LENGTH_SHORT).show();
                        pd.dismiss();
                    }
                });
                //Toast.makeText(getActivity(), "User Registered...", Toast.LENGTH_SHORT).show();
               // navController.navigate(R.id.action_signupFragment_to_userList);
            }
        });
    }

}
