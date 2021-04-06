package com.example.smj.Manager;

import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class KaKaoNetworkManager<T> {

    private static final String baseUrl = "https://dapi.kakao.com/";
    private static KaKaoNetworkManager instance = null;
    private Retrofit retrofit;
    public T apiService;

    // 생성자 private
    private KaKaoNetworkManager(Class<T> apiInterface){
        retrofit = new Retrofit.Builder()
                .baseUrl(baseUrl)
                .addConverterFactory(GsonConverterFactory.create())
                .build();
        apiService = apiInterface.cast(retrofit.create(apiInterface));
    }

    public static <T> KaKaoNetworkManager getInstance(Class<T> apiInterface){
        if(instance == null){
            instance = new KaKaoNetworkManager(apiInterface);
        }
        return instance;
    }
}
