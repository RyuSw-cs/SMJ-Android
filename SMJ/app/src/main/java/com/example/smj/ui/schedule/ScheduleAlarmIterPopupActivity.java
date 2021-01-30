package com.example.smj.ui.schedule;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.view.Window;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.ImageView;
import android.widget.Toast;

import com.example.smj.R;
import com.example.smj.ui.main.MainActivity;

public class ScheduleAlarmIterPopupActivity extends Activity {
    private Button daybutton, weekbutton, monthbutton, yearbutton;
    private ImageView daycheck, weekcheck, monthcheck, yearcheck;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        setContentView(R.layout.activity_schedule_alarm_iter_popup);
        init();
        Button.OnClickListener onClickListener = new Button.OnClickListener() {
            @Override
            public void onClick(View v) {
                switch (v.getId()) {
                    case R.id.schedule_alarm_everydayiter:
                        daycheck.bringToFront();
                        weekbutton.bringToFront();
                        monthbutton.bringToFront();
                        yearbutton.bringToFront();
                        setViewInvalidate(daycheck,daybutton,weekbutton,monthbutton,yearbutton);
                        break;
                    case R.id.schedule_alarm_everyweekiter:
                        weekcheck.bringToFront();
                        daybutton.bringToFront();
                        monthbutton.bringToFront();
                        yearbutton.bringToFront();
                        setViewInvalidate(weekcheck,daybutton,weekbutton,monthbutton,yearbutton);
                        break;
                    case R.id.schedule_alarm_everymonthiter:
                        monthcheck.bringToFront();
                        daybutton.bringToFront();
                        weekbutton.bringToFront();
                        yearbutton.bringToFront();
                        setViewInvalidate(monthcheck,daybutton,weekbutton,monthbutton,yearbutton);
                        break;
                    case R.id.schedule_alarm_everyyeariter:
                        yearcheck.bringToFront();
                        daybutton.bringToFront();
                        weekbutton.bringToFront();
                        monthbutton.bringToFront();
                        setViewInvalidate(yearcheck,daybutton,weekbutton,monthbutton,yearbutton);
                        break;
                }
            }
        };
        daybutton.setOnClickListener(onClickListener);
        weekbutton.setOnClickListener(onClickListener);
        monthbutton.setOnClickListener(onClickListener);
        yearbutton.setOnClickListener(onClickListener);
    }
    private void setViewInvalidate(View... views) { for (View v : views) { v.invalidate(); } }
    protected  void init(){
        daycheck = findViewById(R.id.daycheck);
        weekcheck = findViewById(R.id.weekcheck);
        monthcheck = findViewById(R.id.monthcheck);
        yearcheck = findViewById(R.id.yearcheck);
        daybutton = findViewById(R.id.schedule_alarm_everydayiter);
        weekbutton = findViewById(R.id.schedule_alarm_everyweekiter);
        monthbutton = findViewById(R.id.schedule_alarm_everymonthiter);
        yearbutton = findViewById(R.id.schedule_alarm_everyyeariter);
    }
}
