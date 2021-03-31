package com.example.smj.ui.transaction;

import android.app.Dialog;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.view.View;
import android.view.Window;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import com.example.smj.Manager.JWTManager;
import com.example.smj.R;
import com.example.smj.domain.usecase.TransactionUseCase;

public class TransactionModifyActivity extends AppCompatActivity {

    private TransactionPostData data;
    private TextView category, title, writer, date, content;
    private TransactionUseCase transactionUseCase;
    private String key;


    //사진은 어케 할까..
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_transaction_modify);
        init();
    }

    private void init(){

        transactionUseCase = new TransactionUseCase(this);

        key = JWTManager.getSharedPreference(this, getString(R.string.saved_JWT));

        category = findViewById(R.id.transaction_reading_post_category);
        title = findViewById(R.id.transaction_reading_post_title);
        content = findViewById(R.id.transaction_reading_post_content);

        //표시할 값 객체로 받기
        Intent intent = getIntent();
        data = (TransactionPostData)intent.getSerializableExtra("data");

        category.setText(data.getCategory());
        title.setText(data.getTitle());
        writer.setText(data.getWriter());
        content.setText(data.getContents());
    }
}
