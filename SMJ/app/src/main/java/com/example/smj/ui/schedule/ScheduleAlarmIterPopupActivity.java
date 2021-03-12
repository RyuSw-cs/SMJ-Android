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
    private Button dayButton, weekButton, monthButton, yearButton;
    private CheckBox dayCheck, weekCheck, monthCheck, yearCheck;
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
                        }
                        else{
                            setCheckFalseAll(dayCheck,weekCheck,monthCheck,yearCheck);
                            dayCheck.setChecked(true);
                        }
                        break;
                    case R.id.schedule_alarm_everyweekiter:
                        if(weekCheck.isChecked()){
                            setCheckFalseAll(dayCheck,weekCheck,monthCheck,yearCheck);
                        }
                        else{
                            setCheckFalseAll(dayCheck,weekCheck,monthCheck,yearCheck);
                            weekCheck.setChecked(true);
                        }
                        break;
                    case R.id.schedule_alarm_everymonthiter:
                        if(weekCheck.isChecked()){
                            setCheckFalseAll(dayCheck,weekCheck,monthCheck,yearCheck);
                        }
                        else{
                            setCheckFalseAll(dayCheck,weekCheck,monthCheck,yearCheck);
                            monthCheck.setChecked(true);
                        }
                        break;
                    case R.id.schedule_alarm_everyyeariter:
                        if(yearCheck.isChecked()){
                            setCheckFalseAll(dayCheck,weekCheck,monthCheck,yearCheck);
                        }
                        else{
                            setCheckFalseAll(dayCheck,weekCheck,monthCheck,yearCheck);
                            yearCheck.setChecked(true);
                        }
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
