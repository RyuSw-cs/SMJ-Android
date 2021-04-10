package com.example.smj.data.datasource;

import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class NetworkManager<T> {
    private static final String baseURL ="https://smj-server-heroku.herokuapp.com";
    private Retrofit retrofit;
    private static NetworkManager instance = null;
    public T apiService;

    private NetworkManager(Class<T> apiInterface){
        retrofit = new Retrofit.Builder()
                .baseUrl(baseURL)
                .addConverterFactory(GsonConverterFactory.create())
                .build();
        apiService = apiInterface.cast(retrofit.create(apiInterface));
    }
    public static <T> NetworkManager getInstance(Class<T> apiInterface){
        if(instance == null){
            instance = new NetworkManager(apiInterface);
        }
        else{
            //instance = NetworkManager(apiInterface);
        }
        return instance;
    }
}
