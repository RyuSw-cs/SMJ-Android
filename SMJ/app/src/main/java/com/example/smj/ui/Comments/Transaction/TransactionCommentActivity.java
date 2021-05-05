package com.example.smj.ui.Comments.Transaction;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.util.Log;
import android.view.View;
import android.view.inputmethod.InputMethodManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.smj.Manager.JWTManager;
import com.example.smj.R;
import com.example.smj.callback.CommentOnSuccess;
import com.example.smj.callback.MemberOnSuccess;
import com.example.smj.data.entity.Comments.CommentData;
import com.example.smj.data.entity.Comments.CommentsPostData;
import com.example.smj.data.entity.Member.MemberData;
import com.example.smj.domain.usecase.CommentsUseCase;
import com.example.smj.domain.usecase.MemberUseCase;
import com.example.smj.ui.Comments.Transaction.Adapter.TransactionCommentAdapter;

import java.util.ArrayList;
import java.util.List;

public class TransactionCommentActivity extends AppCompatActivity implements CommentOnSuccess, MemberOnSuccess {
    private RecyclerView recyclerView;
    private List<TransactionCommentData> commentData = new ArrayList<>();
    private List<MemberData> memberData = new ArrayList<>();
    private TransactionCommentAdapter adapter;
    private CommentsUseCase commentsUseCase;
    private MemberUseCase memberUseCase;
    private String token;
    private int boardId;
    private Button upload;
    private EditText content;
    private InputMethodManager inputMethodManager;

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
        content = findViewById(R.id.transaction_comment_write_text);
        inputMethodManager = (InputMethodManager)getSystemService(INPUT_METHOD_SERVICE);
        Intent intent = getIntent();
        //defaultValue 변경해야함.
        boardId = intent.getIntExtra("id",9999);
        token =  JWTManager.getSharedPreference(this,getString(R.string.saved_JWT));
        recyclerView = findViewById(R.id.transaction_comment_list);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));

        upload.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //작성 후 리사이클러뷰에
                if(content.getText().toString().equals("")){
                        Toast.makeText(getApplicationContext(),"댓글을 입력해주시기 바랍니다.",Toast.LENGTH_SHORT).show();
                }
                else{
                    commentsUseCase.postData(new CommentsPostData(content.getText().toString()),token,boardId,getApplicationContext());
                    content.setText("");
                    content.clearFocus();
                    inputMethodManager.hideSoftInputFromWindow(upload.getWindowToken(),0);
                }
            }
        });
        //댓글 데이터 받아오기
        commentsUseCase.getData(token,boardId);
        //멤버 데이터 받아오기
        memberUseCase.getData(token);
    }

    @Override
    public void onSuccess(List<CommentData>list){
        //데이터 전처리
        commentData.clear();
        int getListSize = list.size();
        for(int i = 0; i<getListSize; i++){
            commentData.add(new TransactionCommentData(list.get(i).getCreatedAt(),list.get(i).getMember().getNickName(),list.get(i).getContent(),list.get(i).getMember().getEmail(),list.get(i).getId()));
        }
        adapter = new TransactionCommentAdapter(commentData, memberData, this, commentsUseCase, token, boardId);
        adapter.refreshAdapter();
        recyclerView.setAdapter(adapter);
    }

    @Override
    public void dataChangeSuccess() {
        commentsUseCase.getData(token,boardId);
        adapter.refreshAdapter();
    }

    //사용자 데이터 받기
    @Override
    public void onDataSuccess(List<MemberData> body){
        memberData.clear();
        memberData = body;
    }
}
