package com.example.smj.ui.main.fragment;

import android.content.Intent;
import android.os.Bundle;
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
import com.example.smj.data.entity.Convenience.Category;
import com.example.smj.data.entity.board.boardCategory;
import com.example.smj.data.entity.board.boardData;
import com.example.smj.domain.usecase.TransactionUseCase;
import com.example.smj.ui.LivingTip.LivingTipPostAdapter;
import com.example.smj.ui.LivingTip.LivingTipPostData;
import com.example.smj.ui.create.CreateTradeActivity;
import com.example.smj.ui.transaction.TransactionModifyActivity;
import com.example.smj.ui.transaction.TransactionPostAdapter;
import com.example.smj.ui.transaction.TransactionPostData;

import java.util.ArrayList;
import java.util.List;

public class TransactionFragment extends Fragment implements TransactionGetData {
    List<TransactionPostData> data = new ArrayList<>();
    RecyclerView recyclerView;
    TransactionPostAdapter adapter;
    TransactionUseCase transactionUseCase;
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
                Intent intent = new Intent(getActivity(), CreateTradeActivity.class);
                startActivity(intent);
            }
        });
        /*
        boardCategory BC = new boardCategory(1,"운동기구");
        boardData bd = new boardData(BC,"내용","123",0,"title","TRADE");
        transactionUseCase.postData(bd,"eyJhbGciOiJIUzI1NiJ9.eyJzdWIiOiJyc3cxNDUyQG5hdmVyLmNvbSIsImlhdCI6MTYxNjA4ODg3NiwiZXhwIjoxNjE2MDkwNjc2fQ.kaRqb9rTn4giz2Yz15AVXtx0FVrFnEsa3wj7BNbcEqg");
         */
        return view;
    }

    @Override
    public void onSuccess(List<boardData> list) {
        //list를 받았을때 값을 add, 리사이클러뷰에 뿌림
        for(int i = 0; i<list.size(); i++) {
            //id와 profileimage, createAt 변경 해야함.
            //id값이 int인데 어떻게 표시해야할지 모르겠음
            if(list.get(i).getType().equals("TRADE")) {
                data.add(new TransactionPostData(list.get(i).getCategory().getName(), list.get(i).getTitle(), list.get(i).getContent(), "글쓴이를 추가해주세요",
                        "날짜입니다.", "이미지입니다", list.get(i).getId()));
            }
        }
        recyclerView.setHasFixedSize(true);
        adapter = new TransactionPostAdapter(getActivity(), data);
        recyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));
        recyclerView.setAdapter(adapter);
    }
}
