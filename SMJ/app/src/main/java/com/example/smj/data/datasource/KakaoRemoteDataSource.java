package com.example.smj.data.datasource;

import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

/*
레트로핏 객체를 생성해주는 곳
 */
public class KakaoRemoteDataSource {
    private static final String URL ="https://dapi.kakao.com/";
    private static Retrofit retrofit;

    public static Retrofit getInstance(){
        if(retrofit == null){
            retrofit = new Retrofit.Builder()
                    .baseUrl(URL)
                    .addConverterFactory(GsonConverterFactory.create())
                    .build();
        }
        return retrofit;
    }
}
