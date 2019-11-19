package com.smalsus.pagerestapi.retrofit_apis;

import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.POST;

public interface API
{
    @FormUrlEncoded
    @POST("user")
    Call<ResponseBody> createUser(
      @Field("phoneNo") String phoneNo,
      @Field("fName") String fName,
      @Field("lName") String lName,
      @Field("gender") int gender,
      @Field("dob") String dob,
      @Field("emailID") String emailid,
      @Field("userName") String userName,
      @Field("password") String password);


}
