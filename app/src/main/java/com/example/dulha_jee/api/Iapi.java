package com.example.dulha_jee.api;

import com.example.dulha_jee.authenthication.ResetBody;
import com.example.dulha_jee.dashboard.WaistCoatFragment;
import com.example.dulha_jee.pojo.CoatRequestBody;
import com.example.dulha_jee.pojo.EmailBody;
import com.example.dulha_jee.pojo.GetUserResponseBody;
import com.example.dulha_jee.pojo.HtmlResponseBody;
import com.example.dulha_jee.pojo.InnerSuitRequestBody;
import com.example.dulha_jee.pojo.KurtaRequestBody;
import com.example.dulha_jee.pojo.LoginBody;
import com.example.dulha_jee.pojo.LoginResponseBody;
import com.example.dulha_jee.pojo.PantPojo;
import com.example.dulha_jee.pojo.RegisterBody;
import com.example.dulha_jee.pojo.SearchResponseBody;
import com.example.dulha_jee.pojo.SherwaniRequestBody;
import com.example.dulha_jee.pojo.ShirtRequestBody;
import com.example.dulha_jee.pojo.WaistCoatFragmentrequestBody;

import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.Response;
import retrofit2.http.Body;
import retrofit2.http.GET;
import retrofit2.http.Header;
import retrofit2.http.POST;
import retrofit2.http.Query;

public interface Iapi {

    @POST("api/auth/create")
    Call<LoginResponseBody> registerUser(@Body RegisterBody body);

    @POST("api/auth/signIn")
    Call<LoginResponseBody> loginUser(@Body LoginBody body);

    @POST("api/auth/forgetPassword")
    Call<ResponseBody> forgotPassword(@Body EmailBody body);

    @POST("api/auth/resetPassword")
    Call<ResponseBody> resetBody(@Body ResetBody body);

    @GET("api/users/getAll")
    Call<GetUserResponseBody> getUsers(@Header("Authorization") String token);

    @POST("api/kurta/create")
    Call<HtmlResponseBody> createKurta(@Header("Authorization") String token, @Body KurtaRequestBody kurtaRequestBody);

    @POST("api/sherwani/create")
    Call<HtmlResponseBody> createSherwani(@Header("Authorization") String token, @Body SherwaniRequestBody kurtaRequestBody);

    @POST("api/coat/create")
    Call<HtmlResponseBody> createCoat(@Header("Authorization") String token, @Body CoatRequestBody kurtaRequestBody);

    @POST("api/waist-coat/create")
    Call<HtmlResponseBody> createWaistCoat(@Header("Authorization") String token, @Body WaistCoatFragmentrequestBody kurtaRequestBody);

    @POST("api/shirt/create")
    Call<HtmlResponseBody> createShirt(@Header("Authorization") String token, @Body ShirtRequestBody kurtaRequestBody);

    @POST("api/pant/create")
    Call<HtmlResponseBody> createPant(@Header("Authorization") String token, @Body PantPojo kurtaRequestBody);

    @POST("api/inner-suit/create")
    Call<HtmlResponseBody> createInnerSuit(@Header("Authorization") String token, @Body InnerSuitRequestBody kurtaRequestBody);

    @POST("api/updateStatus")
    Call<ResponseBody> updateStatus(@Header("Authorization") String token);


    @GET("api/search")
    Call<SearchResponseBody> search(@Header("Authorization") String token,
                                    @Query("date") String date,
                                    @Query("customer_name") String customer_name,
                                    @Query("order_number") String order_number,
                                    @Query("karigar") String karigar,
                                    @Query("mobile_number") String mobile_number);

}
