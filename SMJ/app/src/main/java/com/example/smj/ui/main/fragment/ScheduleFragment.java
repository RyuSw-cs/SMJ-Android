package com.example.smj.ui.main.fragment;

import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CalendarView;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;

import com.example.smj.R;
import com.example.smj.ui.schedule.EventDecorator;
import com.example.smj.ui.schedule.ScheduleAlarmlistPopupActivity;
import com.prolificinteractive.materialcalendarview.CalendarDay;
import com.prolificinteractive.materialcalendarview.MaterialCalendarView;
import com.prolificinteractive.materialcalendarview.OnDateSelectedListener;
import com.google.android.material.datepicker.MaterialCalendar;
import com.prolificinteractive.materialcalendarview.CalendarDay;
import com.prolificinteractive.materialcalendarview.CalendarMode;
import com.prolificinteractive.materialcalendarview.MaterialCalendarView;
import com.prolificinteractive.materialcalendarview.OnDateSelectedListener;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

public class ScheduleFragment extends Fragment {
    MaterialCalendarView calendarView;
    String spotColor;
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
        spotColor = "#e86328";
        calendarView = view.findViewById(R.id.schedule_calendarView);
        calendarView.setSelectedDate(CalendarDay.today());
        Calendar startTimeCalendar = Calendar.getInstance();
        Calendar endTimeCalendar = Calendar.getInstance();

        int currentYear = startTimeCalendar.get(Calendar.YEAR);
        int currentMonth = startTimeCalendar.get(Calendar.MONTH);
        int currentDate = startTimeCalendar.get(Calendar.DATE);
        endTimeCalendar.set(Calendar.MONTH, currentMonth+3);

        //점찍기 코드
        Calendar calendar = Calendar.getInstance();
        calendar.add(Calendar.MONTH,-2);
        ArrayList<CalendarDay> dayList = new ArrayList<>();
        for(int i = 0; i <30; i++){
            CalendarDay day = CalendarDay.from(calendar);
            dayList.add(day);
            calendar.add(Calendar.DATE,5);
        }
        calendarView.state().edit()
                .setFirstDayOfWeek(Calendar.SUNDAY)
                .setMinimumDate(CalendarDay.from(currentYear, currentMonth, 1))
                .setMaximumDate(CalendarDay.from(currentYear, currentMonth+11, endTimeCalendar.getActualMaximum(Calendar.DAY_OF_MONTH)))
                .setCalendarDisplayMode(CalendarMode.MONTHS)
                .commit();
        calendarView.addDecorator(new EventDecorator(Color.parseColor(spotColor), dayList));
    }
    protected void CalendarViewEvent(View v) {
        calendarView.setOnDateChangedListener(new OnDateSelectedListener() {
            @Override
            public void onDateSelected(@NonNull MaterialCalendarView widget, @NonNull CalendarDay date, boolean selected) {
                Intent intent = new Intent(getActivity(), ScheduleAlarmlistPopupActivity.class);
                intent.putExtra("data", "Test Popup");
                startActivityForResult(intent, 1);
            }
        });
    }

}
