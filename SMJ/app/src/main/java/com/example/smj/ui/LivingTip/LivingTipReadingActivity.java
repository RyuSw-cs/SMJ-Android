package com.example.smj.ui.LivingTip;

import android.app.Dialog;
import android.os.Bundle;
import android.view.Window;
import android.widget.ImageButton;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import com.example.smj.R;

public class LivingTipReadingActivity extends AppCompatActivity {

    Dialog moreView;
    ImageButton moreBtn;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_living_tip_reading);

        moreBtn = findViewById(R.id.more_btn);
        moreView = new Dialog(LivingTipReadingActivity.this);
        moreView.requestWindowFeature(Window.FEATURE_NO_TITLE);
        moreView.setContentView(R.layout.reading_view_more);

        moreBtn.setOnClickListener(v -> showMoreView());
    }

    public void showMoreView(){
        moreView.show();
    }
}
