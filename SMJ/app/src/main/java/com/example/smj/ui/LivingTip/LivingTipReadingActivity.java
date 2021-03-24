package com.example.smj.ui.LivingTip;

import android.app.Dialog;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.View;
import android.view.Window;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.Nullable;
import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;

import com.example.smj.Manager.JWTManager;
import com.example.smj.R;
import com.example.smj.domain.usecase.LivingTipUseCase;
import com.example.smj.domain.usecase.TransactionUseCase;

public class LivingTipReadingActivity extends AppCompatActivity {

    Dialog moreView;
    ImageButton moreBtn;
    Button deleteBtn;
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

        deleteBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Log.d("확인","클릭 확인");
                LivingTipUseCase livingTipUseCase = new LivingTipUseCase();
                livingTipUseCase.deleteData(key,id);
            }
        });
    }

    public void showMoreView(){
        moreView.show();
    }
}
