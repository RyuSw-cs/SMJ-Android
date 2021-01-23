package com.example.smj.ui.schedule;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import com.example.smj.R;
import com.example.smj.application.App;
import com.example.smj.utill.UiHelper;

public class ScheduleAlarmPageActivity extends AppCompatActivity {
    private ViewGroup alarmDelete;
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        setContentView(R.layout.activity_schedule_alarmpage);
        init();
        alarmDelete.setOnClickListener((view) ->{
            Intent intent = new Intent(this, ScheduleAlarmDeletePopupActivity.class);
            intent.putExtra("data", "Test Popup");
            startActivityForResult(intent, 1);
        });
    }
    protected void init(){
        alarmDelete = findViewById(R.id.schedule_allday_clicklayout);
    }
}
