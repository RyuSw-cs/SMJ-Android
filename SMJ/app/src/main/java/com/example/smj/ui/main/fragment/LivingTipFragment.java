package com.example.smj.ui.main.fragment;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CalendarView;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.smj.R;
import com.example.smj.ui.LivingTip.LivingTipPostAdapter;
import com.example.smj.ui.LivingTip.LivingTipPostData;

import java.util.ArrayList;

//홈 버튼
public class LivingTipFragment extends Fragment {

    ArrayList<LivingTipPostData> data = new ArrayList<>();
    RecyclerView recyclerView;
    LivingTipPostAdapter adapter;

    public LivingTipFragment(){
    }
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle saveInstanceState){
        View view = inflater.inflate(R.layout.activity_living_tip_main,container,false);

        recyclerView = (RecyclerView) view.findViewById(R.id.living_tip_post_list);
        data.add(new LivingTipPostData("청소","제목","글내용","글쓴이","날짜","이미지"));
        data.add(new LivingTipPostData("청소2","제목2","글내용2","글쓴이2","날짜2","이미지2"));

        recyclerView.setHasFixedSize(true);
        adapter = new LivingTipPostAdapter(getActivity(), data);
        recyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));
        recyclerView.setAdapter(adapter);
        return view;
    }

}
