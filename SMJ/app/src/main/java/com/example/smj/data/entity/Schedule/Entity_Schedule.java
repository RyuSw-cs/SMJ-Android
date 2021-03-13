package com.example.smj.data.entity.Schedule;

import java.util.List;
import java.util.Map;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.DELETE;
import retrofit2.http.FieldMap;
import retrofit2.http.GET;
import retrofit2.http.Header;
import retrofit2.http.POST;
import retrofit2.http.PUT;
import retrofit2.http.Part;
import retrofit2.http.Path;

public interface Entity_Schedule {
    @GET("api/alarms")
    Call <List<Alarm>> getAlarm(
            @Header("Authorization") String token
    );
    @POST("api/alarms")
    Call<Alarm> postAlarm(
            @Header("Authorization")String token,
            @Body Alarm post
    );
    @PUT("api/alarms/{id}")
    Call<Alarm> putAlarm(
            @Header("Authorization")String token,
            @Body Alarm put,
            @Path("id")String id
    );
    @DELETE("api/alarms/{id}")
    Call<Void> deleteAlarm(
            @Header("Authorization")String token,
            @Path("id")String id
    );
}
