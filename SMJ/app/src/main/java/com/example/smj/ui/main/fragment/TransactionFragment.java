package com.example.smj.ui.main.fragment;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.smj.R;
import com.example.smj.ui.LivingTip.LivingTipPostAdapter;
import com.example.smj.ui.LivingTip.LivingTipPostData;
import com.example.smj.ui.transaction.TransactionPostAdapter;
import com.example.smj.ui.transaction.TransactionPostData;

import java.util.ArrayList;

public class TransactionFragment extends Fragment {
    ArrayList<TransactionPostData> data = new ArrayList<>();
    RecyclerView recyclerView;
    TransactionPostAdapter adapter;

    public TransactionFragment(){

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle saveInstanceState){
        View view = inflater.inflate(R.layout.activity_transaction_main,container,false);

        recyclerView = (RecyclerView) view.findViewById(R.id.transaction_post_list);
        data.add(new TransactionPostData("거래","제목","글내용","글쓴이","날짜","이미지"));
        data.add(new TransactionPostData("거래2","제목2","글내용2","글쓴이2","날짜2","이미지2"));

        recyclerView.setHasFixedSize(true);
        adapter = new TransactionPostAdapter(getActivity(), data);
        recyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));
        recyclerView.setAdapter(adapter);
        return view;
    }
}
