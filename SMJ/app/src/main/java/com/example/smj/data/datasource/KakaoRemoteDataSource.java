package com.example.smj.data.datasource;

import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class KakaoRemoteDataSource<T> {

    private static final String baseURL ="https://dapi.kakao.com/";
    private Retrofit retrofit;
    private static KakaoRemoteDataSource instance = null;
    public T apiService;

    private KakaoRemoteDataSource(Class<T> apiInterface){
        retrofit = new Retrofit.Builder()
                .baseUrl(baseURL)
                .addConverterFactory(GsonConverterFactory.create())
                .build();
        apiService = apiInterface.cast(retrofit.create(apiInterface));
    }
    public static <T> KakaoRemoteDataSource getInstance(Class<T> apiInterface){
        if(instance == null){
            instance = new KakaoRemoteDataSource(apiInterface);
        }
        return instance;
    }
}
