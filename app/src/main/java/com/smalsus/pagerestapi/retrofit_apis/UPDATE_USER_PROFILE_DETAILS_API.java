package com.smalsus.pagerestapi.retrofit_apis;

import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.Header;
import retrofit2.http.PUT;
import retrofit2.http.Path;

public interface UPDATE_USER_PROFILE_DETAILS_API
{
    @FormUrlEncoded
    @PUT("user/{userId}")
    Call<ResponseBody> updateUserProfileDetails(
            @Path("userId") String userId,
            @Field("phoneNo") String phoneNo,
            @Field("fName") String fName,
            @Field("lName") String lName,
            @Field("gender") int gender,
            @Field("dob") String dob,
            @Field("password") String password,
            @Header("token") String token
    );
}
