package com.project.terrain.Controller;

import com.project.terrain.APISET.Apiset;

import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class ApiController {
    private static final String url="http://192.168.1.103:8087/users/";
    private static ApiController clientobject;
    private static Retrofit retrofit;

    public ApiController() {
        retrofit = new Retrofit.Builder()
                .baseUrl(url)
                .addConverterFactory(GsonConverterFactory.create())
                .build();
    }

    public static  synchronized ApiController getInstance(){
        if(clientobject == null){
            clientobject = new ApiController();
        }
        return clientobject;

    }
    public Apiset getapi() {
        return retrofit.create(Apiset.class);
    }
}
