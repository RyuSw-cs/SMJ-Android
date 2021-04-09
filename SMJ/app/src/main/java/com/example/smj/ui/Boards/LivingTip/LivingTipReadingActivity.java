package com.example.smj.ui.Boards.LivingTip;

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

import com.example.smj.Manager.JWTManager;
import com.example.smj.R;
import com.example.smj.domain.usecase.LivingTipUseCase;

public class LivingTipReadingActivity extends AppCompatActivity {

    Dialog moreView;
    ImageButton moreBtn;
    Button deleteBtn;
    Button modifyBtn;
    int id;
    String category;
    String title;
    String writer;
    String date;
    String content;
    TextView categoryView;
    TextView titleView;
    TextView writerView;
    TextView dateView;
    TextView contentView;
    private boolean check = false;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_living_tip_reading);
        Intent getIntent = getIntent();

        String key = JWTManager.getSharedPreference(this,getString(R.string.saved_JWT));

        moreBtn = findViewById(R.id.more_btn);
        moreView = new Dialog(LivingTipReadingActivity.this);
        moreView.requestWindowFeature(Window.FEATURE_NO_TITLE);
        moreView.setContentView(R.layout.reading_view_more);
        deleteBtn = (Button) moreView.findViewById(R.id.reading_delete);
        modifyBtn = (Button) moreView.findViewById(R.id.reading_modified);

        categoryView = findViewById(R.id.living_tip_reading_post_category);
        titleView = findViewById(R.id.living_tip_reading_post_title);
        writerView = findViewById(R.id.living_tip_reading_post_writer);
        dateView = findViewById(R.id.living_tip_reading_post_date);
        contentView = findViewById(R.id.living_tip_reading_content);

        id = getIntent.getExtras().getInt("id");
        category = getIntent.getExtras().getString("category");
        title = getIntent.getExtras().getString("title");
        writer = getIntent.getExtras().getString("writer");
        date = getIntent.getExtras().getString("date");
        content = getIntent.getExtras().getString("content");

        categoryView.setText(category);
        titleView.setText(title);
        writerView.setText(writer);
        dateView.setText(date);
        contentView.setText(content);

        moreBtn.setOnClickListener(v -> showMoreView());

        //수정버튼 클릭
        modifyBtn.setOnClickListener(new View.OnClickListener(){

            @Override
            public void onClick(View v) {
                Intent intent = new Intent(v.getContext(), LivingTipModifyActivity.class);
                intent.putExtra("id",id);
                intent.putExtra("category",category);
                intent.putExtra("title",title);
                intent.putExtra("content",content);
                startActivity(intent);
                finish();
            }
        });

        //삭제버튼 클릭
        deleteBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Log.d("확인","클릭 확인");
                LivingTipUseCase livingTipUseCase = new LivingTipUseCase();
                livingTipUseCase.deleteData(key,id,getApplicationContext());

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

    private void check(){
        if(check){
            finish();
            check = false;
        }
    }

}
