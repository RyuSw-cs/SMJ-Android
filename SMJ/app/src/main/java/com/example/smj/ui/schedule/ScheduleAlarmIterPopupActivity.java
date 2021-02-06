package com.example.smj.ui.schedule;

import android.app.Activity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.Window;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.ImageView;
import android.widget.Toast;

import com.example.smj.R;
import com.example.smj.ui.main.MainActivity;
import com.google.android.material.button.MaterialButton;

public class ScheduleAlarmIterPopupActivity extends Activity {
    private Button daybutton, weekbutton, monthbutton, yearbutton;
    private CheckBox daycheck, weekcheck, monthcheck, yearcheck;
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
                        if(daycheck.isChecked()){
                            setCheckFalseAll(daycheck,weekcheck,monthcheck,yearcheck);
                        }
                        else{
                            setCheckFalseAll(daycheck,weekcheck,monthcheck,yearcheck);
                            daycheck.setChecked(true);
                        }
                        break;
                    case R.id.schedule_alarm_everyweekiter:
                        if(weekcheck.isChecked()){
                            setCheckFalseAll(daycheck,weekcheck,monthcheck,yearcheck);
                        }
                        else{
                            setCheckFalseAll(daycheck,weekcheck,monthcheck,yearcheck);
                            weekcheck.setChecked(true);
                        }
                        break;
                    case R.id.schedule_alarm_everymonthiter:
                        if(monthcheck.isChecked()){
                            setCheckFalseAll(daycheck,weekcheck,monthcheck,yearcheck);
                        }
                        else{
                            setCheckFalseAll(daycheck,weekcheck,monthcheck,yearcheck);
                            monthcheck.setChecked(true);
                        }
                        break;
                    case R.id.schedule_alarm_everyyeariter:
                        if(yearcheck.isChecked()){
                            setCheckFalseAll(daycheck,weekcheck,monthcheck,yearcheck);
                        }
                        else{
                            setCheckFalseAll(daycheck,weekcheck,monthcheck,yearcheck);
                            yearcheck.setChecked(true);
                        }
                        break;
                }
            }
        };
        daybutton.setOnClickListener(onClickListener);
        weekbutton.setOnClickListener(onClickListener);
        monthbutton.setOnClickListener(onClickListener);
        yearbutton.setOnClickListener(onClickListener);

    }
    private void setCheckFalseAll(CheckBox... checks) { for (CheckBox b : checks) {
        b.setChecked(false); }
    }
    protected  void init(){
        daycheck = findViewById(R.id.daycheck);
        daycheck.bringToFront();
        weekcheck = findViewById(R.id.weekcheck);
        weekcheck.setButtonDrawable(R.drawable.checbox_schedule_itter);
        monthcheck = findViewById(R.id.monthcheck);
        monthcheck.setButtonDrawable(R.drawable.checbox_schedule_itter);
        yearcheck = findViewById(R.id.yearcheck);
        yearcheck.setButtonDrawable(R.drawable.checbox_schedule_itter);
        daybutton = findViewById(R.id.schedule_alarm_everydayiter);
        weekbutton = findViewById(R.id.schedule_alarm_everyweekiter);
        monthbutton = findViewById(R.id.schedule_alarm_everymonthiter);
        yearbutton = findViewById(R.id.schedule_alarm_everyyeariter);
    }
}
