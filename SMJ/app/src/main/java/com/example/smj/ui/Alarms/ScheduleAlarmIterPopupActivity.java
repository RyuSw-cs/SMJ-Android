package com.example.smj.ui.Alarms;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.MotionEvent;
import android.view.View;
import android.view.Window;
import android.widget.Button;
import android.widget.CheckBox;

import com.example.smj.R;

public class ScheduleAlarmIterPopupActivity extends Activity {
    private Button dayButton, weekButton, monthButton, yearButton;
    private CheckBox dayCheck, weekCheck, monthCheck, yearCheck;
    private int checkflag= 0;
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
                        if(dayCheck.isChecked()){
                            setCheckFalseAll(dayCheck,weekCheck,monthCheck,yearCheck);
                            checkflag = 0;
                        }
                        else{
                            setCheckFalseAll(dayCheck,weekCheck,monthCheck,yearCheck);
                            dayCheck.setChecked(true);
                            checkflag = 1;
                        }
                        send(v);
                        break;
                    case R.id.schedule_alarm_everyweekiter:
                        if(weekCheck.isChecked()){
                            setCheckFalseAll(dayCheck,weekCheck,monthCheck,yearCheck);
                            checkflag = 0;
                        }
                        else{
                            setCheckFalseAll(dayCheck,weekCheck,monthCheck,yearCheck);
                            weekCheck.setChecked(true);
                            checkflag = 2;
                        }
                        send(v);
                        break;
                    case R.id.schedule_alarm_everymonthiter:
                        if(monthCheck.isChecked()){
                            setCheckFalseAll(dayCheck,weekCheck,monthCheck,yearCheck);
                            checkflag = 0;
                        }
                        else{
                            setCheckFalseAll(dayCheck,weekCheck,monthCheck,yearCheck);
                            monthCheck.setChecked(true);
                            checkflag = 3;
                        }
                        send(v);
                        break;
                    case R.id.schedule_alarm_everyyeariter:
                        if(yearCheck.isChecked()){
                            setCheckFalseAll(dayCheck,weekCheck,monthCheck,yearCheck);
                            checkflag = 0;
                        }
                        else{
                            setCheckFalseAll(dayCheck,weekCheck,monthCheck,yearCheck);
                            yearCheck.setChecked(true);
                            checkflag= 4;
                        }
                        send(v);
                        break;
                }
            }
        };
        dayButton.setOnClickListener(onClickListener);
        weekButton.setOnClickListener(onClickListener);
        monthButton.setOnClickListener(onClickListener);
        yearButton.setOnClickListener(onClickListener);

    }
    private void setCheckFalseAll(CheckBox... checks) { for (CheckBox b : checks) {
        b.setChecked(false); }
    }
    @Override
    public void onBackPressed() {
        Intent intent = new Intent(this, ScheduleAlarmPageActivity.class);
        intent.putExtra("iter", checkflag);
        setResult(RESULT_OK,intent);
        super.onBackPressed();
    }
    private void send(View v){
        Intent intent = new Intent(v.getContext(), ScheduleAlarmPageActivity.class);
        intent.putExtra("iter", checkflag);
        setResult(RESULT_OK,intent);
    }
    protected  void init(){
        dayCheck = findViewById(R.id.daycheck);
        dayCheck.bringToFront();
        weekCheck = findViewById(R.id.weekcheck);
        weekCheck.setButtonDrawable(R.drawable.checbox_schedule_itter);
        monthCheck = findViewById(R.id.monthcheck);
        monthCheck.setButtonDrawable(R.drawable.checbox_schedule_itter);
        yearCheck = findViewById(R.id.yearcheck);
        yearCheck.setButtonDrawable(R.drawable.checbox_schedule_itter);
        dayButton = findViewById(R.id.schedule_alarm_everydayiter);
        weekButton = findViewById(R.id.schedule_alarm_everyweekiter);
        monthButton = findViewById(R.id.schedule_alarm_everymonthiter);
        yearButton = findViewById(R.id.schedule_alarm_everyyeariter);
    }
}
