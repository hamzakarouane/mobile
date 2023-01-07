package com.project.terrain.APISET;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class Cliente {

    public static Retrofit getClient(String url){

        Gson gson = new GsonBuilder()
                .setLenient()
                .create();
        Retrofit retrofit= new Retrofit.Builder().baseUrl(url).addConverterFactory(GsonConverterFactory.create()).build();
        return  retrofit;
    }
}
