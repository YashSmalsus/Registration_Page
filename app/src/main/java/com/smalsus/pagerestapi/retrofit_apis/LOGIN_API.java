package com.smalsus.pagerestapi.retrofit_apis;

import com.smalsus.pagerestapi.LoginResponseUserDetails;

import retrofit2.Call;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.POST;
public interface LOGIN_API
{
    @FormUrlEncoded
    @POST("user/login")
    Call<LoginResponseUserDetails> accessUser(
            @Field("emailID") String emailid,
            @Field("password") String password
    );
}
