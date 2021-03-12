package com.example.smj.data.entity.Schedule;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Header;

public interface Entity_Schedule {
    @GET("api/alarms")
    Call<Alarm>getAlarm(
            @Header("Authorization") String token
    );
}
