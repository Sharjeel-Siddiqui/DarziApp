package com.example.dulha_jee.api;

import com.example.dulha_jee.authenthication.ResetBody;
import com.example.dulha_jee.pojo.EmailBody;
import com.example.dulha_jee.pojo.LoginBody;
import com.example.dulha_jee.pojo.RegisterBody;

import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.Response;
import retrofit2.http.Body;
import retrofit2.http.Header;
import retrofit2.http.POST;

public interface Iapi {

    @POST("api/auth/create")
    Call<ResponseBody> registerUser(@Body RegisterBody body);

    @POST("api/auth/signIn")
    Call<ResponseBody> loginUser(@Body LoginBody body);

    @POST("api/auth/forgetPassword")
    Call<ResponseBody> forgotPassword(@Body EmailBody body);

    @POST("api/auth/resetPassword")
    Call<ResponseBody> resetBody(@Body ResetBody body);

}
