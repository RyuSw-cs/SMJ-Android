package com.example.smj.utill.ui.main.fragment;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.fragment.app.Fragment;

import com.example.smj.R;

//홈 버튼
public class LivingTipFragment extends Fragment {
    public LivingTipFragment(){

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle saveInstanceState){
        View view = inflater.inflate(R.layout.activity_living_tip_main,container,false);
        return view;
    }
}
