package com.example.smj.callback;

import com.example.smj.data.entity.Schedule.Alarm;

import java.util.ArrayList;

public interface ScheduleGetData {
    void clickSuccess(ArrayList<Alarm> list);
}
