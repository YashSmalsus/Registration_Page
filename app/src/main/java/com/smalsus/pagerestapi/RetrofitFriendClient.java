package com.smalsus.pagerestapi;

import com.smalsus.pagerestapi.retrofit_apis.FRIENDS_API;

import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class RetrofitFriendClient
{
    private static  final  String base_friends_url = "http://3.19.76.185:1211/";
    private static RetrofitFriendClient retrofitFriendClient;
    private Retrofit retrofitFriend;
    private RetrofitFriendClient()
    {
        retrofitFriend = new Retrofit.Builder()
                .baseUrl(base_friends_url)
                .addConverterFactory(GsonConverterFactory.create())
                .build();
    }
    public static synchronized RetrofitFriendClient getInstance()
    {
        if(retrofitFriendClient == null)
        {
            retrofitFriendClient = new RetrofitFriendClient();
        }
        return retrofitFriendClient;
    }
    public FRIENDS_API getNewApi()
    {
        return retrofitFriend.create(FRIENDS_API.class);
    }
}
