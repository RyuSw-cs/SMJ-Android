package com.example.smj.ui.main.fragment;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.fragment.app.Fragment;

import com.example.smj.R;

public class TradeFragment extends Fragment {
    public TradeFragment(){

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle saveInstanceState){
        //거래 액티비티 나올 시 교체
        View view = inflater.inflate(R.layout.activity_login,container,false);
        return view;
    }
}
