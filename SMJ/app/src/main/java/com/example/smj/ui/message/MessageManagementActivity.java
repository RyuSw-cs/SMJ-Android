package com.example.smj.ui.message;

import android.content.Context;
import android.os.Bundle;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.smj.Manager.JWTManager;
import com.example.smj.R;
import com.example.smj.callback.RetrofitOnSuccess;
import com.example.smj.data.entity.Message.MessageManageData;
import com.example.smj.domain.usecase.MessageUseCase;

import java.util.ArrayList;
import java.util.List;

public class MessageManagementActivity extends AppCompatActivity implements RetrofitOnSuccess {

    private RecyclerView messageRecyclerView;
    private LinearLayoutManager layoutManager;
    private MessageUseCase messageUseCase;
    private String token;
    private List<MessageManageData>dataList = new ArrayList<>();

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_message_management);
        init();
    }

    private void init(){
        messageUseCase = new MessageUseCase(this);
        token =  JWTManager.getSharedPreference(this,getString(R.string.saved_JWT));
        messageUseCase.getData(token);

        messageRecyclerView = findViewById(R.id.message_management_list);
        layoutManager = new LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false);
        messageRecyclerView.setLayoutManager(layoutManager);
        messageRecyclerView.setAdapter(new MessageManagementAdapter(this, dataList));
    }

    @Override
    public void onSuccess(Object object) {
        //서버에서 데이터 받아오기 성공
        dataList = (List<MessageManageData>)object;
    }
}
