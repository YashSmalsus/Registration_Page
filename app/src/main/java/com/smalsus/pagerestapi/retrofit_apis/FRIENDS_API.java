package com.smalsus.pagerestapi.retrofit_apis;

import com.smalsus.pagerestapi.Friends;

import java.util.ArrayList;
import java.util.List;

import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Header;
import retrofit2.http.Path;

public interface FRIENDS_API
{
    @GET("user/{userId}/associate")
    Call<List<Friends>> getFriends(
            @Path("userId") String userId,
            @Header("token") String token
    );
}
