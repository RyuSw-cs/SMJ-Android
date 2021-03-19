package com.example.smj.ui.transaction;

import android.app.Dialog;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.Window;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.TextView;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.AppCompatImageButton;

import com.example.smj.R;
import com.example.smj.ui.LivingTip.LivingTipReadingActivity;

import java.util.ArrayList;

public class TransactionReadingActivity extends AppCompatActivity{

    private ImageButton moreBtn;
    private TransactionPostData data;
    private TextView category, title, writer, date, content;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_transaction_reading);
        init();
    }

    private void init(){

        moreBtn = findViewById(R.id.more_btn);
        category = findViewById(R.id.transaction_reading_post_category);
        title = findViewById(R.id.transaction_reading_post_title);
        writer = findViewById(R.id.transaction_reading_post_writer);
        date = findViewById(R.id.transaction_reading_post_date);
        content = findViewById(R.id.transaction_reading_post_content);

        //표시할 값 객체로 받기
        Intent intent = getIntent();
        data = (TransactionPostData)intent.getSerializableExtra("data");

        category.setText(data.getCategory());
        title.setText(data.getTitle());
        writer.setText(data.getWriter());
        date.setText(data.getDate());
        content.setText(data.getContents());

        moreBtn.setOnClickListener(v -> showMoreView());
    }

    public void showMoreView(){
        //삭제를 위해 id값 인텐트로 전달
        Intent intent = new Intent(this, TransactionMoreInfo.class);
        intent.putExtra("id",data.getWriter());
        startActivity(intent);
    }
}
