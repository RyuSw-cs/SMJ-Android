package com.example.smj.ui.schedule;

import android.app.Activity;
import android.os.Bundle;
import android.view.Window;

import com.example.smj.R;

public class ScheduleAlarmDeletePopupActivity extends Activity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        setContentView(R.layout.activity_schedule_alarmdelete_popup);
    }
}
