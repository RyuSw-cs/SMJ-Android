package com.example.smj.ui.Boards.Transaction;

import android.app.Dialog;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.view.View;
import android.view.Window;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.TextView;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import com.example.smj.Manager.JWTManager;
import com.example.smj.R;
import com.example.smj.domain.usecase.TransactionUseCase;

public class TransactionReadingActivity extends AppCompatActivity{

    private ImageButton moreBtn;
    private TransactionPostData data;
    private TextView category, title, writer, date, content;
    private TransactionUseCase transactionUseCase;
    private Dialog moreView;
    private Button deleteBtn;
    private int id;
    private String key;
    private Boolean check = false;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_transaction_reading);
        init();
        check_person();
    }

    private void check_person(){
        //서버 통신을 해줘야하네
        if(writer.equals("234")){

        }
    }

    private void check(){
        if(check){
            finish();
            check = false;
        }
    }

    private void init(){

        key = JWTManager.getSharedPreference(this, getString(R.string.saved_JWT));

        moreBtn = findViewById(R.id.more_btn);
        category = findViewById(R.id.transaction_reading_post_category);
        title = findViewById(R.id.transaction_reading_post_title);
        writer = findViewById(R.id.transaction_reading_post_writer);
        date = findViewById(R.id.transaction_reading_post_date);
        content = findViewById(R.id.transaction_reading_post_content);

        moreView = new Dialog(TransactionReadingActivity.this);
        moreView.requestWindowFeature(Window.FEATURE_NO_TITLE);
        moreView.setContentView(R.layout.reading_view_more);
        deleteBtn = (Button) moreView.findViewById(R.id.reading_delete);

        //표시할 값 객체로 받기
        Intent intent = getIntent();
        data = (TransactionPostData)intent.getSerializableExtra("data");

        category.setText(data.getCategory());
        title.setText(data.getTitle());
        writer.setText(data.getWriter());
        String []getDate = data.getDate();

        String dateInfo = getDate[0]+"년 "+getDate[1]+"월 "+getDate[2] + "일";

        date.setText(dateInfo);

        content.setText(data.getContents());

        moreBtn.setOnClickListener(v -> showMoreView());
        deleteBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                transactionUseCase = new TransactionUseCase();
                transactionUseCase.deleteData(key, data.getId(),getApplicationContext());
                Handler mHandler = new Handler();
                mHandler.postDelayed(new Runnable() {
                    @Override
                    public void run() {
                        check = true;
                        check();
                    }
                },500);
            }
        });
    }

    public void showMoreView(){
        moreView.show();
    }
}
