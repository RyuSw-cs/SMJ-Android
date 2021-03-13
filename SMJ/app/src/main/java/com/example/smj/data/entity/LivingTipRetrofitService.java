package com.example.smj.data.entity;

import com.example.smj.data.entity.Schedule.Alarm;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.DELETE;
import retrofit2.http.GET;
import retrofit2.http.Header;
import retrofit2.http.POST;
import retrofit2.http.PUT;
import retrofit2.http.Path;

public interface LivingTipRetrofitService {

    @GET("api/boards")
    Call<List<LivingTipDTO>> getLivingTIp(
            @Header("Authorization") String token
    );

    @POST("api/boards")
    Call<LivingTipDTO> postLivingTip(
            @Header("Authorization") String token,
            @Body LivingTipDTO post
    );

    @PUT("api/boards")
    Call<LivingTipDTO> putLivingTip(
            @Header("Authorization") String token,
            @Body LivingTipDTO put,
            @Path("id") String id
    );

    @DELETE("api/boards/{id}")
    Call<Void> deleteLivingTip(
            @Header("Authorization")String token,
            @Path("id")String id
    );

    @GET("api/boards/my")
    Call<List<LivingTipDTO>> getMyLivingTip(
            @Header("Authorization") String token
    );
}