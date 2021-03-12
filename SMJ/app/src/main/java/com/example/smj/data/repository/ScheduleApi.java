package com.example.smj.data.repository;

import com.example.smj.data.datasource.SMJRemoteDataSource;
import com.example.smj.data.entity.Schedule.Alarm;
import com.example.smj.data.entity.Schedule.Entity_Schedule;
import com.example.smj.domain.usecase.ScheduleUseCase;

import java.util.ArrayList;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class ScheduleApi {
    private ArrayList<Alarm> localList = new ArrayList<>();
    public void retrieveLocals(String key, ScheduleUseCase scheduleUseCase){
        Entity_Schedule entitySchedule = SMJRemoteDataSource.getInstance().create(Entity_Schedule.class);
        Call<Alarm> call = entitySchedule.getAlarm("가져온 토큰값을 넣어주세요");
        call.enqueue(new Callback<Alarm>() {
            @Override
            public void onResponse(Call<Alarm> call, Response<Alarm> response) {
                if(response.isSuccessful()){
                    //알람 가져오기 성공
                    /*
                    localList.addAll(response.body().getDocuments());
                    convenienceUseCase.onSuccess(localList);
                     */
                }
            }

            @Override
            public void onFailure(Call<Alarm> call, Throwable t) {

            }
        });
    }
}
