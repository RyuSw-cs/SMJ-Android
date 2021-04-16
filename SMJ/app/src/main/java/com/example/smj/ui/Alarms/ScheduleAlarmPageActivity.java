package com.example.smj.ui.Alarms;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.widget.EditText;
import android.widget.TextView;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import com.example.smj.R;

public class ScheduleAlarmPageActivity extends AppCompatActivity {
    private ViewGroup alarmDelete, alarmIter;
    private TextView submitModified;
    private EditText title, content;
    private Boolean checkFocus1 = true, checkFocus2 = true;
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        setContentView(R.layout.activity_schedule_alarmpage);
        init();
        submitModified.setOnClickListener((view) ->{
            Intent intent = new Intent(this, ScheduleAlarmModifiedPopupActivity.class);
            intent.putExtra("data", "Test Popup");
            startActivityForResult(intent, 1);
        });
        alarmIter.setOnClickListener((view) ->{
            Intent intent = new Intent(this, ScheduleAlarmIterPopupActivity.class);
            intent.putExtra("data", "Test Popup");
            startActivityForResult(intent, 1);
        });
        alarmDelete.setOnClickListener((view) ->{
            Intent intent = new Intent(this, ScheduleAlarmDeletePopupActivity.class);
            intent.putExtra("data", "Test Popup");
            startActivityForResult(intent, 1);
        });
    }
    protected void init(){
        alarmIter = findViewById(R.id.schedule_allday_iterclicklayout);
        alarmDelete = findViewById(R.id.schedule_allday_clicklayout);
        submitModified = findViewById(R.id.schedule_alarmpage_submit_modified);
        title = findViewById(R.id.schedule_alarmpage_alarmsubmit);
        title.setOnFocusChangeListener(new View.OnFocusChangeListener() {
            @Override
            public void onFocusChange(View v, boolean hasFocus) {
                if(checkFocus1) {
                    title.setText("");
                    checkFocus1 = false;
                }
            }
        });
        content = findViewById(R.id.schedule_alarmpage_context);
        content.setOnFocusChangeListener(new View.OnFocusChangeListener() {
            @Override
            public void onFocusChange(View v, boolean hasFocus) {
                if(checkFocus2) {
                    content.setText("");
                    checkFocus2 = false;
                }
            }
        });
        submitModified.bringToFront();
    }
}
