package com.example.dulha_jee.dashboard;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.webkit.WebView;
import android.widget.ArrayAdapter;
import android.widget.Spinner;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import com.example.dulha_jee.R;
import com.example.dulha_jee.SharedPreference;
import com.example.dulha_jee.api.ApiClient;
import com.example.dulha_jee.api.Iapi;

import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class Customer_View_Fragment extends Fragment {

    WebView mWebView;
    Spinner dropdown_status;
    String[] status = {"آرڈر ختم کرنا ہے", "آرڈر آگیا", "کام جاری ہے", "آرڈر بن گیا", "کسٹمر کو پہنچ گیا"};
    Iapi iapi;
    SharedPreference sharedPreference;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        return inflater.inflate(R.layout.customer_detail, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        mWebView = view.findViewById(R.id.mWebView);
        iapi = ApiClient.getClient().create(Iapi.class);
        sharedPreference = new SharedPreference(getActivity());


        dropdown_status = view.findViewById(R.id.dropdown_status);

        ArrayAdapter<String> adapter1 = new ArrayAdapter<String>(getActivity(), android.R.layout.simple_spinner_item, status);
        adapter1.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        dropdown_status.setAdapter(adapter1);


        if (getArguments() != null) {
            mWebView.getSettings().setJavaScriptEnabled(true);
            mWebView.setScrollBarStyle(View.SCROLLBARS_INSIDE_OVERLAY);
            mWebView.loadUrl(getArguments().getString("url"));
        } else {
            Toast.makeText(getActivity(), "NO url found", Toast.LENGTH_SHORT).show();
        }


        view.findViewById(R.id.updateState).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                iapi.updateStatus("Bearer " + sharedPreference.getToken(), dropdown_status.getSelectedItem().toString()).enqueue(new Callback<ResponseBody>() {
                    @Override
                    public void onResponse(Call<ResponseBody> call, Response<ResponseBody> response) {
                        if (response.isSuccessful()) {
                            Toast.makeText(getActivity(),response.message() + "code : " + response.code(), Toast.LENGTH_SHORT).show();
                        } else {
                            Toast.makeText(getActivity(), response.message() + "code : " + response.code(), Toast.LENGTH_SHORT).show();
                        }
                    }

                    @Override
                    public void onFailure(Call<ResponseBody> call, Throwable t) {
                        Toast.makeText(getActivity(), "Failed...", Toast.LENGTH_SHORT).show();
                    }
                });
            }
        });


    }

}
