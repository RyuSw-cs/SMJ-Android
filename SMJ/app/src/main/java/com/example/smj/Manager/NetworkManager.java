package com.example.smj.Manager;

import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class NetworkManager<T> {

    private static final String baseUrl = "https://smj-server-heroku.herokuapp.com";
    private static NetworkManager instance = null;
    private Retrofit retrofit;
    public T apiService;

    // 생성자 private
    private NetworkManager(Class<T> apiInterface){
        retrofit = new Retrofit.Builder()
                .baseUrl(baseUrl)
                .addConverterFactory(GsonConverterFactory.create())
                .build();
        apiService = retrofit.create(apiInterface);
    }

    public static <T> NetworkManager getInstance(Class<T> apiInterface){
        if(instance == null){
            instance = new NetworkManager(apiInterface);
        }
        return instance;
    }
}
