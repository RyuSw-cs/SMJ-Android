package com.example.smj.data.datasource;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class JWTRemoteDataSource {
    private static final String URL ="https://smj-server-heroku.herokuapp.com";
    private static Retrofit retrofit;

    public static Retrofit getInstance(){
        Gson gson = new GsonBuilder()
                .setLenient()
                .create();
        if(retrofit == null){
            retrofit = new Retrofit.Builder()
                    .baseUrl(URL)
                    .addConverterFactory(GsonConverterFactory.create(gson))
                    .build();
        }
        return retrofit;
    }
}
