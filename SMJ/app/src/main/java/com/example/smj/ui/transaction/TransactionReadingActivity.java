package com.example.smj.ui.transaction;

import android.app.Activity;
import android.app.Dialog;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.util.Log;
import android.view.View;
import android.view.Window;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.TextView;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.AppCompatImageButton;

import com.example.smj.Manager.JWTManager;
import com.example.smj.R;
import com.example.smj.callback.BoardIdGetData;
import com.example.smj.domain.usecase.TransactionUseCase;
import com.example.smj.ui.LivingTip.LivingTipReadingActivity;

import java.util.ArrayList;

public class TransactionReadingActivity extends AppCompatActivity {

    private ImageButton moreBtn;
    private TransactionPostData data;
    private TextView category, title, writer, date, content;
    private TransactionUseCase transactionUseCase;
    private Dialog moreView;
    private Button deleteBtn, modifyBtn;
    private String key;
    private Boolean check = false;
    public static ArrayList<Activity>activityStack = new ArrayList<>();

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_transaction_reading);
        activityStack.add(this);
        init();
    }

    private void check(){
        if(check){
            finish();
            check = false;
        }
    }

    private void init(){

        Intent intent = getIntent();

        transactionUseCase = new TransactionUseCase(this);

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
        modifyBtn = (Button) moreView.findViewById(R.id.reading_modified);

        //표시할 값 객체로 받기
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

        modifyBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getApplicationContext(), TransactionModifyActivity.class);
                intent.putExtra("modifyData",data);
                startActivity(intent);
            }
        });
        transactionUseCase.getMyData(key);
    }

    public void showMoreView(){
        moreView.show();
    }


    //내 게시글 데이터를 다 가져오고~
    public void onSuccessMyData(ArrayList<Integer>list) {
        int getListSize = list.size();
        for(int i = 0; i<getListSize; i++){
            if(data.getId() == list.get(i)) {
                moreBtn.setVisibility(View.VISIBLE);
                moreBtn.setEnabled(true);
            }
        }
    }
}

