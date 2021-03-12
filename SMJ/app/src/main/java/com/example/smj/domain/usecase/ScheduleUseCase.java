package com.example.smj.domain.usecase;

import com.example.smj.callback.RetrofitOnSuccess;
import com.example.smj.data.entity.Schedule.Alarm;
import com.example.smj.data.repository.ScheduleApi;
import com.example.smj.ui.main.fragment.ScheduleFragment;

import java.util.ArrayList;

public class ScheduleUseCase implements RetrofitOnSuccess {
    private ScheduleApi scheduleApi;
    private ScheduleFragment scheduleFragment;
    private ArrayList<Alarm> list = new ArrayList<>();

    public ScheduleUseCase(ScheduleFragment scheduleFragment){
        scheduleApi = new ScheduleApi();
        this.scheduleFragment = scheduleFragment;
    }
    public void sendKey(String key){
        //키값 전달
        scheduleApi.retrieveLocals(key, this);
    }
    @Override
    public void onSuccess(Object object) {
        if(object != null){
            list = (ArrayList<Alarm>)object;
            scheduleFragment.clickSuccess(list);
        }
    }
}
