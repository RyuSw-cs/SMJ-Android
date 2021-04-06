package com.example.smj.domain.usecase;

import com.example.smj.callback.RetrofitOnSuccess;
import com.example.smj.data.entity.Schedule.Alarm;
import com.example.smj.data.repository.ScheduleRepository;
import com.example.smj.ui.Alarms.ScheduleFragment;

import java.util.ArrayList;
import java.util.List;

public class ScheduleUseCase implements RetrofitOnSuccess {
    private ScheduleRepository scheduleRepository;
    private ScheduleFragment scheduleFragment;
    private List<Alarm> list = new ArrayList<>();

    public ScheduleUseCase(ScheduleFragment scheduleFragment){
        scheduleRepository = new ScheduleRepository();
        this.scheduleFragment = scheduleFragment;
    }
    /* GET으로 데이터 받아올때 키값 전달 */
    public void sendKey(String key){
        //키값 전달
        scheduleRepository.retrieveLocals(key, this);
    }

    /* POST로 Alarm데이터 전송 */
    public void sendData(Alarm alarm, String key){
        scheduleRepository.postData(alarm, key);
    }

    /* PUT Alarm데이터 수정 */
    public void changeData(Alarm alarm, String key, String id){
        scheduleRepository.putData(alarm, key, id);
    }

    /* DELETE로 Alarm데이터 삭제 */
    public void deleteData(String key, String id){
        scheduleRepository.deleteData(key, id);
    }

    @Override
    public void onSuccess(Object object) {
        if(object != null){
            list = (List<Alarm>)object;
            scheduleFragment.clickSuccess(list);
        }
    }
}
