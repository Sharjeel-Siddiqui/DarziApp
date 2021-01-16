package com.example.dulha_jee.authenthication;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import com.example.dulha_jee.R;
import com.example.dulha_jee.api.ApiClient;
import com.example.dulha_jee.api.Iapi;

import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class ResetFragment extends Fragment {

    EditText et_resetpassword_token, et_email, et_password;
    Button resetbtn;
    Iapi iapi;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_reset, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        et_resetpassword_token = view.findViewById(R.id.et_resetpassword_token);
        et_email = view.findViewById(R.id.et_email);
        et_password = view.findViewById(R.id.et_password);

        resetbtn = view.findViewById(R.id.resetbtn);
        iapi = ApiClient.getClient().create(Iapi.class);

        resetbtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Toast.makeText(getActivity(), "Password reset successfully...", Toast.LENGTH_SHORT).show();
                /*ResetBody resetBody = new ResetBody(et_resetpassword_token.getText().toString(), et_password.getText().toString(), et_email.getText().toString());
                iapi.resetBody(resetBody).enqueue(new Callback<ResponseBody>() {
                    @Override
                    public void onResponse(Call<ResponseBody> call, Response<ResponseBody> response) {
                        if (response.isSuccessful()) {
                            Toast.makeText(getActivity(), "Password Reset...", Toast.LENGTH_SHORT).show();
                        }
                        Toast.makeText(getActivity(), "CODE" + response.code(), Toast.LENGTH_SHORT).show();
                    }

                    @Override
                    public void onFailure(Call<ResponseBody> call, Throwable t) {
                        Toast.makeText(getActivity(), "", Toast.LENGTH_SHORT).show();
                    }
                });*/
            }
        });
    }
}
