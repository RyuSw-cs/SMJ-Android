package com.example.smj.Manager;

import com.example.smj.callback.RetrofitOnSuccess;

import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class NetworkManager {

    private static final String baseUrl = "https://smj-server-heroku.herokuapp.com";
    private static NetworkManager instance = null;
    private Retrofit retrofit;

    // 생성자 private
    private NetworkManager(){
        retrofit = new Retrofit.Builder()
                .baseUrl(baseUrl)
                .addConverterFactory(GsonConverterFactory.create())
                .build();
    }

    public static NetworkManager getInstance(){
        if(instance == null){
            instance = new NetworkManager();
        }
        return instance;
    }
<<<<<<< HEAD
    public Retrofit getRetrofit(){
=======

    public Retrofit getRetrofit() {
>>>>>>> develop
        return retrofit;
    }
}
