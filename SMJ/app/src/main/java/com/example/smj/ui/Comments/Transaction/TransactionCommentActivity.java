package com.example.smj.ui.Comments.Transaction;

import android.content.Intent;
import android.os.Bundle;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.smj.Manager.JWTManager;
import com.example.smj.R;
import com.example.smj.data.entity.Comments.CommentData;
import com.example.smj.domain.usecase.CommentsUseCase;

import java.util.ArrayList;
import java.util.List;

public class TransactionCommentActivity extends AppCompatActivity {
    private RecyclerView recyclerView;
    private List<TransactionCommentData> data = new ArrayList<>();
    private TransactionCommentAdapter adapter;
    private CommentsUseCase commentsUseCase;
    private String token;
    private int boardId;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_transaction_comment);
        init();
    }

    private void init(){

        commentsUseCase = new CommentsUseCase(this);
        Intent intent = getIntent();
        //defaultValue 변경해야함.
        boardId = intent.getIntExtra("id",9999);
        token =  JWTManager.getSharedPreference(this,getString(R.string.saved_JWT));
        recyclerView = findViewById(R.id.transaction_comment_list);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));

        commentsUseCase.getData(token,boardId);

    }
    public void onSuccess(List<CommentData>list){
        //데이터 전처리
        int getListSize = list.size();
        for(int i = 0; i<getListSize; i++){
            data.add(new TransactionCommentData(list.get(i).getCreatedAt(),list.get(i).getMember().getNickName(),list.get(i).getContent()));
        }
        adapter = new TransactionCommentAdapter(data);
        recyclerView.setAdapter(adapter);
    }
}
