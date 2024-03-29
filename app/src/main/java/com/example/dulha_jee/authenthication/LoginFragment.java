package com.example.dulha_jee.authenthication;

import android.app.ProgressDialog;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Bundle;
import android.text.TextUtils;
import android.util.Base64;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AlertDialog;
import androidx.fragment.app.DialogFragment;
import androidx.fragment.app.Fragment;
import androidx.navigation.NavController;
import androidx.navigation.NavOptions;
import androidx.navigation.Navigation;

import com.example.dulha_jee.MainActivity;
import com.example.dulha_jee.R;
import com.example.dulha_jee.SharedPreference;
import com.example.dulha_jee.api.ApiClient;
import com.example.dulha_jee.api.Iapi;
import com.example.dulha_jee.pojo.EmailBody;
import com.example.dulha_jee.pojo.LoginBody;
import com.example.dulha_jee.pojo.LoginResponseBody;
import com.example.dulha_jee.userlist.DatePickerFragment;

import java.io.ByteArrayOutputStream;
import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;

import javax.net.ssl.HttpsURLConnection;

import butterknife.BindView;
import butterknife.ButterKnife;
import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class LoginFragment extends Fragment {

    NavController navController;
    Button button, dialogSubmitbtn;
    TextView SignUp, forgetPassword;
    Iapi iapi;
    EditText et_email, et_password;
    AlertDialog.Builder builder;
    EditText emailSubmit;
    ProgressDialog pd;
    SharedPreference sharedPreference;

    @BindView(R.id.customer_cloth)
    CheckBox customer_cloth;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_login, container, false);

    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        navController = Navigation.findNavController(view);
        button = view.findViewById(R.id.btn_login);
        SignUp = view.findViewById(R.id.SignUp);
        iapi = ApiClient.getClient().create(Iapi.class);
        et_email = view.findViewById(R.id.et_email);
        et_password = view.findViewById(R.id.et_password);
        forgetPassword = view.findViewById(R.id.forgetPassword);
        builder = new AlertDialog.Builder(getActivity());
        ButterKnife.bind(this, view);
        pd = new ProgressDialog(getActivity());
        pd.setMessage("Please wait...");
        pd.setTitle("Signing...");
        sharedPreference = new SharedPreference(getActivity());


        ((MainActivity) getActivity()).setToolbarVisibility(false);

        forgetPassword.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                ViewGroup viewGroup = view.findViewById(android.R.id.content);
                View dialogView = LayoutInflater.from(getActivity()).inflate(R.layout.dialog_reset_password, viewGroup, false);
                dialogSubmitbtn = dialogView.findViewById(R.id.Submit);
                emailSubmit = dialogView.findViewById(R.id.email);
                builder.setView(dialogView);
                AlertDialog alertDialog = builder.create();
                alertDialog.setCancelable(false);
                alertDialog.show();

                dialogSubmitbtn.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        EmailBody emailBody = new EmailBody(emailSubmit.getText().toString());
                        iapi.forgotPassword(emailBody).enqueue(new Callback<ResponseBody>() {
                            @Override
                            public void onResponse(Call<ResponseBody> call, Response<ResponseBody> response) {
                                if (response.isSuccessful()) {
                                    Toast.makeText(getActivity(), "Please.. Check your email", Toast.LENGTH_SHORT).show();
                                    navController.navigate(R.id.action_loginFragment_to_resetFragment);
                                    alertDialog.dismiss();
                                }
                                Toast.makeText(getActivity(), response.message(), Toast.LENGTH_SHORT).show();
                            }

                            @Override
                            public void onFailure(Call<ResponseBody> call, Throwable t) {
                                Toast.makeText(getActivity(), "Failed..", Toast.LENGTH_SHORT).show();

                            }
                        });
                        //  navController.navigate(R.id.action_loginFragment_to_resetFragment);
                    }
                });
            }
        });

        SignUp.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                navController.navigate(R.id.action_loginFragment_to_signupFragment);
            }
        });

        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (checkValidation()) {
                    pd.show();
                    LoginBody loginBody = new LoginBody(et_email.getText().toString(), et_password.getText().toString());
                    iapi.loginUser(loginBody).enqueue(new Callback<LoginResponseBody>() {
                        @Override
                        public void onResponse(Call<LoginResponseBody> call, Response<LoginResponseBody> response) {
                            if (response.isSuccessful()) {
                                sharedPreference.saveToken(response.body().getApi_token());
                                String value = sharedPreference.getToken();
                                navController.navigate(R.id.action_loginFragment_to_userList, null, new NavOptions.Builder()
                                        .setPopUpTo(R.id.loginFragment,
                                                true).build());
                                pd.dismiss();
                            } else if (response.code() == HttpsURLConnection.HTTP_UNAUTHORIZED) {
                                Toast.makeText(getActivity(), "Your User Name or Password is Incorrect...", Toast.LENGTH_SHORT).show();
                                pd.dismiss();
                            } else {
                                Toast.makeText(getActivity(), "Something went wrong.Please try again...", Toast.LENGTH_SHORT).show();
                                pd.dismiss();
                            }
                        }

                        @Override
                        public void onFailure(Call<LoginResponseBody> call, Throwable t) {
                            Toast.makeText(getActivity(), "Failed...", Toast.LENGTH_SHORT).show();
                            Log.i("TAG", "onFailure: " + t.getMessage());
                            pd.dismiss();
                        }
                    });
                }
            }
        });

    }

    public boolean checkValidation() {

        if (TextUtils.isEmpty(et_email.getText().toString())) {
            Toast.makeText(getActivity(), "Email Address Is Required...", Toast.LENGTH_SHORT).show();
            return false;
        }


        if (TextUtils.isEmpty(et_password.getText().toString())) {
            Toast.makeText(getActivity(), "Password is required...", Toast.LENGTH_SHORT).show();
            return false;
        }

        return true;


    }


}



