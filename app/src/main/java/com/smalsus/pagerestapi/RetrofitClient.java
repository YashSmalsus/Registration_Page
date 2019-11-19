package com.smalsus.pagerestapi;

import com.smalsus.pagerestapi.retrofit_apis.API;

import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class RetrofitClient
{
    private static  final String Sign_Up_Base_URL = "http://3.19.76.185:1211/";
    private static RetrofitClient myInstance;
    private Retrofit retrofit;
    private RetrofitClient()
    {
        retrofit = new Retrofit.Builder()
                .baseUrl(Sign_Up_Base_URL)
                .addConverterFactory(GsonConverterFactory.create())
                .build();
    }

    public static  synchronized RetrofitClient getInstance()
    {
        if(myInstance == null)
        {
            myInstance = new RetrofitClient();
        }
        return myInstance;
    }

    public API getApi()
    {
        return (API) retrofit.create(API.class);
    }
}
