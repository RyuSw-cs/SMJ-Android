package com.example.smj.ui.main.fragment;

import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CalendarView;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;

import com.example.smj.R;
import com.example.smj.ui.schedule.ScheduleAlarmlistPopupActivity;

public class ScheduleFragment extends Fragment {
    CalendarView calendarView;
    public ScheduleFragment(){

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle saveInstanceState){
        //스케쥴 액티비티 나올 시 교체
        View view = inflater.inflate(R.layout.activity_schedule,container,false);
        init(view);
        CalendarViewEvent(view);
        return view;
    }
    protected void init(View view){
        calendarView = view.findViewById(R.id.schedule_calendarview);
    }
    protected void CalendarViewEvent(View v){
        calendarView.setOnDateChangeListener(new CalendarView.OnDateChangeListener() {
            @Override
            public void onSelectedDayChange(@NonNull CalendarView view, int year, int month, int dayOfMonth) {
                Intent intent = new Intent(getActivity(), ScheduleAlarmlistPopupActivity.class);
                intent.putExtra("data", "Test Popup");
                startActivityForResult(intent, 1);
            }
        });
    }
}
