package com.example.smj.data.repository;

import com.example.smj.Manager.KaKaoNetworkManager;
import com.example.smj.data.datasource.KakaoRemoteDataSource;
import com.example.smj.data.entity.Convenience.Category;
import com.example.smj.data.entity.Convenience.Document;
import com.example.smj.data.entity.Convenience.Entity_Convenience;
import com.example.smj.domain.usecase.ConvenienceUseCase;

import java.util.ArrayList;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class ConvenienceMarkerRepository {

    private ArrayList<Document>localList = new ArrayList<>();

    public void retrieveLocals(String local, double x, double y,  ConvenienceUseCase convenienceUseCase){
        Entity_Convenience entityConvenience = (Entity_Convenience) KaKaoNetworkManager.getInstance(Entity_Convenience.class).apiService;
        Call<Category> call = entityConvenience.getSearchCategory("KakaoAK 92ae20dbb333f02658441075a9144490",local,y+"",x+"",1500);
        call.enqueue(new Callback<Category>() {
            @Override
            public void onResponse(Call<Category> call, Response<Category> response) {
                if(response.isSuccessful()){
                    localList.addAll(response.body().getDocuments());
                    convenienceUseCase.onSuccess(localList);
                }
            }

            @Override
            public void onFailure(Call<Category> call, Throwable t) {

            }
        });
    }
}
