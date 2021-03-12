package com.example.smj.data.repository;

import android.util.Log;

import com.example.smj.data.datasource.JWTRemoteDataSource;
import com.example.smj.data.datasource.RemoteDataSource;
import com.example.smj.data.entity.Category;
import com.example.smj.data.entity.Document;
import com.example.smj.data.entity.Entitiy_JWT;
import com.example.smj.data.entity.Entity_Convenience;
import com.example.smj.domain.usecase.ConvenienceUseCase;
import com.example.smj.domain.usecase.JWTUseCase;

import java.util.ArrayList;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class JWTApi {
    private String jwt;
    public void retrieveLocals(String at, JWTUseCase jwtUseCase){
        Entitiy_JWT entityJWT = JWTRemoteDataSource.getInstance().create(Entitiy_JWT.class);
        Call<String> call = entityJWT.getAuthorizationKey(at);
        call.enqueue(new Callback<String>() {
            @Override
            public void onResponse(Call<String> call, Response<String> response) {
                if(response.isSuccessful()){
                    jwt = response.body().toString();
                    jwtUseCase.onSuccess(jwt);
                }
            }

            @Override
            public void onFailure(Call<String> call, Throwable t) {
                Log.d("실패","123");
            }
        });
    }
}


