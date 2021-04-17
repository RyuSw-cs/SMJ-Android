package com.example.smj.ui.Comments.Transaction;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.view.View;
import android.widget.Button;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.smj.Manager.JWTManager;
import com.example.smj.R;
import com.example.smj.data.entity.Comments.CommentData;
import com.example.smj.data.entity.Comments.CommentsPostData;
import com.example.smj.data.entity.Member.MemberData;
import com.example.smj.domain.usecase.CommentsUseCase;
import com.example.smj.domain.usecase.MemberUseCase;
import com.example.smj.ui.Comments.Transaction.Adapter.TransactionCommentAdapter;

import java.util.ArrayList;
import java.util.List;

public class TransactionCommentActivity extends AppCompatActivity {
    private RecyclerView recyclerView;
    private List<TransactionCommentData> commentData = new ArrayList<>();
    private List<MemberData> memberData = new ArrayList<>();
    private TransactionCommentAdapter adapter;
    private CommentsUseCase commentsUseCase;
    private MemberUseCase memberUseCase;
    private String token;
    private int boardId;
    private Button upload;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_transaction_comment);
        init();
    }

    private void init(){

        commentsUseCase = new CommentsUseCase(this);
        memberUseCase = new MemberUseCase(this);
        upload = findViewById(R.id.transaction_comment_write_btn);
        Intent intent = getIntent();
        //defaultValue 변경해야함.
        boardId = intent.getIntExtra("id",9999);
        token =  JWTManager.getSharedPreference(this,getString(R.string.saved_JWT));
        recyclerView = findViewById(R.id.transaction_comment_list);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));

        upload.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //작성
                commentsUseCase.postData(new CommentsPostData("댓글 테스트"),token,boardId,getApplicationContext());
            }
        });
        //멤버 데이터 받아오기
        memberUseCase.getData(token);
    }
    public void onSuccess(List<CommentData>list){
        //데이터 전처리
        int getListSize = list.size();
        for(int i = 0; i<getListSize; i++){
            commentData.add(new TransactionCommentData(list.get(i).getCreatedAt(),list.get(i).getMember().getNickName(),list.get(i).getContent()));
        }
        //어댑터에 사용자와
        adapter = new TransactionCommentAdapter(commentData, memberData, this, commentsUseCase,token,boardId);
        recyclerView.setAdapter(adapter);
        adapter.refreshAdapter();
    }

    public void onDataSuccess(List<MemberData> body){
        memberData = body;
        commentsUseCase.getData(token,boardId);
    }
}
