package com.example.smj.ui.manage;

import android.os.Bundle;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.smj.R;
import com.example.smj.application.PostManageMainAdapter;;

public class PostManageActivity extends AppCompatActivity {
    RecyclerView recyclerView;
    RecyclerView.LayoutManager layoutManager;
    RecyclerView.Adapter adapter;

    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_postmanage);
        init();
        setRecyclerView();
    }
    private void init(){
        recyclerView = findViewById(R.id.postmanage_recyclerView);
    }
    private void setRecyclerView(){
        layoutManager = new LinearLayoutManager(this);
        recyclerView.setLayoutManager(layoutManager);
        String[] title =  {"제목","제목","제목","제목"};
        String[] contents =  {"내용","내용","내용","내용"};
        String[] date =  {"날짜","날짜","날짜","날짜"};
        String[] comment =  {"30","100","댓글 수","댓글 수"};
        adapter = new PostManageMainAdapter(title,contents,date,comment);
        recyclerView.setAdapter(adapter);
    }
}
