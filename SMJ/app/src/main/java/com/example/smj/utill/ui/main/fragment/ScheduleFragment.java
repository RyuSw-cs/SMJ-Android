package com.example.smj.utill.ui.main.fragment;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.fragment.app.Fragment;

import com.example.smj.R;

public class ScheduleFragment extends Fragment {
    public ScheduleFragment(){

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle saveInstanceState){
        //스케쥴 액티비티 나올 시 교체
        View view = inflater.inflate(R.layout.activity_mypage,container,false);
        return view;
    }
}
