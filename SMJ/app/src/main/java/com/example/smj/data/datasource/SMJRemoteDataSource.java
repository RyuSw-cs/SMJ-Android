package com.example.smj.data.datasource;

import com.google.gson.Gson;

import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class SMJRemoteDataSource<T> {
    private static final String baseURL ="https://smj-server-heroku.herokuapp.com";
    private Retrofit retrofit;
    private static SMJRemoteDataSource instance = null;
    public T apiService;

    private SMJRemoteDataSource(Class<T> apiInterface){
        retrofit = new Retrofit.Builder()
                .baseUrl(baseURL)
                .addConverterFactory(GsonConverterFactory.create())
                .build();
        apiService = apiInterface.cast(retrofit.create(apiInterface));
    }
    public static <T> SMJRemoteDataSource getInstance(Class<T> apiInterface){
        if(instance == null){
            instance = new SMJRemoteDataSource(apiInterface);
        }
        return instance;
    }
}
