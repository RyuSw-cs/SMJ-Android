package com.example.smj.ui.Comments.LivingTip;

import android.content.Intent;
import android.os.Bundle;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.smj.Manager.JWTManager;
import com.example.smj.R;
import com.example.smj.data.entity.Comments.CommentData;
import com.example.smj.data.entity.Member.MemberData;
import com.example.smj.domain.usecase.CommentsUseCase;
import com.example.smj.domain.usecase.LivingTipCommentsUseCase;
import com.example.smj.domain.usecase.LivingTipMemberUseCase;
import com.example.smj.domain.usecase.MemberUseCase;
import com.example.smj.ui.Comments.Transaction.Adapter.TransactionCommentAdapter;
import com.example.smj.ui.Comments.Transaction.TransactionCommentData;

import java.util.ArrayList;
import java.util.List;

public class LivingTipCommentActivity extends AppCompatActivity {

    RecyclerView recyclerView = findViewById(R.id.living_tip_comment_list);
    ArrayList<LivingTipCommentData> commentData = new ArrayList<>();
    private List<MemberData> memberData = new ArrayList<>();
    private LivingTipCommentAdapter adapter;
    private LivingTipCommentsUseCase livingTipCommentsUseCase;
    private LivingTipMemberUseCase livingTipMemberUseCase;
    private String token;
    private int boardId;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_living_tip_comment);

        init();

    }

    private void init(){
        recyclerView = findViewById(R.id.living_tip_comment_list);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        livingTipCommentsUseCase = new LivingTipCommentsUseCase(this);
        livingTipMemberUseCase = new LivingTipMemberUseCase(this);
        upload = findViewById(R.id.transaction_comment_write_btn);
        Intent intent = getIntent();
        //defaultValue 변경해야함.
        boardId = intent.getIntExtra("id",9999);
        token =  JWTManager.getSharedPreference(this,getString(R.string.saved_JWT));
        recyclerView = findViewById(R.id.transaction_comment_list);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
    }

    public void onSuccess(List<CommentData> list){

        int getListSize = list.size();
        for(int i = 0; i<getListSize; i++){
            commentData.add(new LivingTipCommentData(list.get(i).getCreatedAt(),list.get(i).getMember().getNickName(),list.get(i).getContent()));
        }

        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        adapter = new LivingTipCommentAdapter(commentData, memberData, this, livingTipCommentsUseCase ,token,boardId);
        recyclerView.setAdapter(adapter);
        adapter.refreshAdapter();
    }

    public void onDataSuccess(List<MemberData> body){
        memberData = body;
        LivingTipCommentsUseCase.getData(token,boardId);
    }
}
