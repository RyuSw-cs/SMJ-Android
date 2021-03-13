package com.example.smj.data.entity;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Header;

public interface Entity_JWT {
        @GET("api/auth/token")
        Call<String> getAuthorizationKey(
                @Header("Authorization")String token
        );
}

