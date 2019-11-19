package com.smalsus.pagerestapi;

import com.smalsus.pagerestapi.retrofit_apis.PROFILE_API;

import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class RetrofitClientDetails
{
    private static  final String User_Profile_Details_Base_URL = "http://3.19.76.185:1211/";
    private static RetrofitClientDetails myInstance1;
    private Retrofit retrofit;
    private RetrofitClientDetails()
    {
        retrofit = new Retrofit.Builder()
                .baseUrl(User_Profile_Details_Base_URL)
                .addConverterFactory(GsonConverterFactory.create())
                .build();
    }
    public static synchronized  RetrofitClientDetails getInstance()
    {
        if(myInstance1 == null)
        {
            myInstance1 = new RetrofitClientDetails();
        }
        return myInstance1;
    }
    public PROFILE_API getApi()
    {
        return (PROFILE_API) retrofit.create(PROFILE_API.class);
    }
}
