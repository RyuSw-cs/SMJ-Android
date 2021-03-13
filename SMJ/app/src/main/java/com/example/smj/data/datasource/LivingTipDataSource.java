package com.example.smj.data.datasource;

import android.util.Log;

import com.example.smj.data.entity.LivingTipDTO;
import com.example.smj.data.entity.LivingTipRetrofitService;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class LivingTipDataSource {
    private static final String URL ="https://smj-server-heroku.herokuapp.com";
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
