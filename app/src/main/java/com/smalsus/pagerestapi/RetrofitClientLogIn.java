package com.smalsus.pagerestapi;

import com.smalsus.pagerestapi.retrofit_apis.LOGIN_API;

import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class RetrofitClientLogIn
{
    private static final String Sign_In_Base_URL = "http://3.19.76.185:1211/";
    private static RetrofitClientLogIn myNewInstance;
    private Retrofit retrofitLogIn;
    private RetrofitClientLogIn()
    {
        retrofitLogIn = new Retrofit.Builder()
                .baseUrl(Sign_In_Base_URL)
                .addConverterFactory(GsonConverterFactory.create())
                .build();
    }

    public static  synchronized RetrofitClientLogIn getInstance()
    {
        if(myNewInstance == null)
        {
            myNewInstance = new RetrofitClientLogIn();
        }
        return myNewInstance;
    }

    public LOGIN_API getNewApi()
    {
        return retrofitLogIn.create(LOGIN_API.class);
    }
}
