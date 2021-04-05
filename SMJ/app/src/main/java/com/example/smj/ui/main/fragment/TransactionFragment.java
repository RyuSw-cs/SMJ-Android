package com.example.smj.ui.main.fragment;

import android.content.Intent;
import android.os.Bundle;
import android.os.Parcelable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.smj.Manager.JWTManager;
import com.example.smj.R;
import com.example.smj.callback.TransactionGetData;
import com.example.smj.data.entity.board.boardData;
import com.example.smj.domain.usecase.TransactionUseCase;
import com.example.smj.ui.transaction.TransactionCreateActivity;
import com.example.smj.ui.transaction.TransactionPostAdapter;
import com.example.smj.ui.transaction.TransactionPostData;

import java.util.ArrayList;
import java.util.List;

public class TransactionFragment extends Fragment implements TransactionGetData {
    private List<TransactionPostData> data = new ArrayList<>();
    private RecyclerView recyclerView;
    private TransactionPostAdapter adapter;
    private TransactionUseCase transactionUseCase;
    private Button writeButton;
    private String token;

    public TransactionFragment(){

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle saveInstanceState){
        transactionUseCase = new TransactionUseCase(this);
        View view = inflater.inflate(R.layout.activity_transaction_main,container,false);

        recyclerView = (RecyclerView) view.findViewById(R.id.transaction_post_list);

        token =  JWTManager.getSharedPreference(getContext(),getString(R.string.saved_JWT));
        //데이터 받아오기
        transactionUseCase.getData(token);
        writeButton = (Button) view.findViewById(R.id.write_btn);
        writeButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getActivity(), TransactionCreateActivity.class);
                startActivity(intent);
            }
        });
        return view;
    }

    @Override
    public void onSuccess(List<boardData> list) {
        data.clear();
        //list를 받았을때 값을 add, 리사이클러뷰에 뿌림
        int getListSize = list.size();
        for(int i = 0; i<getListSize; i++) {
            //profile이미지 변경
            if(list.get(i).getType().equals("TRADE")) {
                data.add(new TransactionPostData(list.get(i).getCategory().getName(), list.get(i).getTitle(), list.get(i).getContent(), list.get(i).getWriter(),
                        list.get(i).getCreatedAt(), "프로필이미지입니다", list.get(i).getId()));
            }
        }
        recyclerView.setHasFixedSize(true);
        adapter = new TransactionPostAdapter(getActivity(), data, transactionUseCase);
        recyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));
        recyclerView.setAdapter(adapter);
    }
    @Override
    public void onResume(){
        super.onResume();
        transactionUseCase.getData(token);
        adapter = new TransactionPostAdapter(getActivity(), data,transactionUseCase);
        recyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));
        recyclerView.setAdapter(adapter);
    }
}
