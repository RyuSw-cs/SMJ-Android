package com.example.smj.domain.usecase;

import com.example.smj.data.entity.Schedule.Alarm;
import com.example.smj.data.entity.board.boardData;
import com.example.smj.data.repository.LivingTipApi;
import com.example.smj.data.repository.ScheduleApi;
import com.example.smj.ui.main.fragment.LivingTipFragment;

import java.util.ArrayList;
import java.util.List;

public class LivingTipUseCase {
    private LivingTipApi livingTipApi;
    private LivingTipFragment livingTipFragment;
    private List<boardData> list = new ArrayList<>();

    public LivingTipUseCase(LivingTipFragment livingTipFragment){
        livingTipApi = new LivingTipApi();
        this.livingTipFragment = livingTipFragment;
    }

    //GET
    public void getData(String key){
        livingTipApi.getData(key, this);
    }

    //POST
    public void postData(boardData data, String key){
        livingTipApi.postData(data, key);
    }

    //PUT
    public void putData(boardData data, String key, String id){ livingTipApi.putData(data, key, id); }

    //DELETE
    public void deleteData(String key, String id){
        livingTipApi.deleteData(key, id);
    }
}
