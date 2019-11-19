package com.smalsus.pagerestapi;

import com.smalsus.pagerestapi.retrofit_apis.UPDATE_USER_PROFILE_DETAILS_API;

import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class RetrofitUpdateUserDetailsClient
{
    private static  final  String base_update_url = "http://3.19.76.185:1211/";
    private static RetrofitUpdateUserDetailsClient retrofitUpdateUserDetailsClient;
    private Retrofit retrofitUserProfileDetailsUpdate;
    private RetrofitUpdateUserDetailsClient()
    {
        retrofitUserProfileDetailsUpdate = new Retrofit.Builder()
                .baseUrl(base_update_url)
                .addConverterFactory(GsonConverterFactory.create())
                .build();
    }
    public static synchronized RetrofitUpdateUserDetailsClient getInstance()
    {
        if(retrofitUpdateUserDetailsClient == null)
        {
            retrofitUpdateUserDetailsClient = new RetrofitUpdateUserDetailsClient();
        }
        return retrofitUpdateUserDetailsClient;
    }
    public UPDATE_USER_PROFILE_DETAILS_API getNewApi()
    {
        return retrofitUserProfileDetailsUpdate.create(UPDATE_USER_PROFILE_DETAILS_API.class);
    }
}
