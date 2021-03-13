package com.example.smj.callback;

import com.example.smj.data.entity.Schedule.Alarm;
import java.util.List;

public interface ScheduleGetData {
    void clickSuccess(List<Alarm> list);
}
