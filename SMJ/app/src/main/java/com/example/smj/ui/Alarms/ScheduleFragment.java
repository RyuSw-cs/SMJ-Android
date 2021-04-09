package com.example.smj.ui.Alarms;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;

import com.example.smj.Manager.JWTManager;
import com.example.smj.R;
import com.example.smj.callback.ScheduleGetData;
import com.example.smj.data.entity.Schedule.Alarm;
import com.example.smj.domain.usecase.ScheduleUseCase;
import com.example.smj.ui.Alarms.ScheduleAlarmListPopupActivity;
import com.prolificinteractive.materialcalendarview.CalendarDay;
import com.prolificinteractive.materialcalendarview.MaterialCalendarView;
import com.prolificinteractive.materialcalendarview.OnDateSelectedListener;
import com.prolificinteractive.materialcalendarview.CalendarMode;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.List;

public class ScheduleFragment extends Fragment implements ScheduleGetData {
    private MaterialCalendarView calendarView;
    private final String spotColor = "#e86328";;
    private ArrayList<Alarm> alarmList = new ArrayList<>();
    private ScheduleUseCase scheduleUseCase;

    SharedPreferences pref;
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle saveInstanceState){
        //스케쥴 액티비티 나올 시 교체
        View view = inflater.inflate(R.layout.activity_schedule,container,false);
        init(view);
        CalendarViewEvent(view);
        return view;
    }

    protected void init(View view)  {

        //여기에 서버통신 준비
        alarmList.clear();
        scheduleUseCase = new ScheduleUseCase(this);
        scheduleUseCase.sendKey(JWTManager.getSharedPreference(getActivity(),getString(R.string.saved_JWT)));
        //이러면 현재 사용자의 알람 리스트들을 받아온것!
        /* jwt 값을 너무 늦게받음! 인터페이스 해줘야함 */
        /* 밑의 코드는 순서대로 GET, PUT, DELETE, POST */
        //scheduleUseCase.sendKey(JWTManager.getSharedPreference(getActivity(),getString(R.string.saved_JWT)));
        //scheduleUseCase.changeData(new Alarm("테스트입니다","2021-03-12","13:00:00",0,"hourly","15:05:00","테스트"),
        //        "eyJhbGciOiJIUzI1NiJ9.eyJzdWIiOiJyc3cxNDUyQG5hdmVyLmNvbSIsImlhdCI6MTYxNTYxMDIyNSwiZXhwIjoxNjE1NjEyMDI1fQ.U3ksEMY3PFLnf8oNyeevV0X-XUZpkOD_M-2vPXQNEnY","3");
        //scheduleUseCase.deleteData("eyJhbGciOiJIUzI1NiJ9.eyJzdWIiOiJyc3cxNDUyQG5hdmVyLmNvbSIsImlhdCI6MTYxNTYxMDIyNSwiZXhwIjoxNjE1NjEyMDI1fQ.U3ksEMY3PFLnf8oNyeevV0X-XUZpkOD_M-2vPXQNEnY","2");
        //scheduleUseCase.sendData(new Alarm("테스트입니다","2021-03-12","13:00:00",0,"hourly","15:05:00","테스트"),
        // "eyJhbGciOiJIUzI1NiJ9.eyJzdWIiOiJyc3cxNDUyQG5hdmVyLmNvbSIsImlhdCI6MTYxNTYxMDIyNSwiZXhwIjoxNjE1NjEyMDI1fQ.U3ksEMY3PFLnf8oNyeevV0X-XUZpkOD_M-2vPXQNEnY");


        calendarView = view.findViewById(R.id.schedule_calendarView);
        calendarView.setSelectedDate(CalendarDay.today());
        Calendar startTimeCalendar = Calendar.getInstance();
        Calendar endTimeCalendar = Calendar.getInstance();

        int currentYear = startTimeCalendar.get(Calendar.YEAR);
        int currentMonth = startTimeCalendar.get(Calendar.MONTH);
        int currentDate = startTimeCalendar.get(Calendar.DATE);
        endTimeCalendar.set(Calendar.MONTH, currentMonth+3);

        calendarView.state().edit()
                .setFirstDayOfWeek(Calendar.SUNDAY)
                .setMinimumDate(CalendarDay.from(currentYear, currentMonth, 1))
                .setMaximumDate(CalendarDay.from(currentYear, currentMonth+11, endTimeCalendar.getActualMaximum(Calendar.DAY_OF_MONTH)))
                .setCalendarDisplayMode(CalendarMode.MONTHS)
                .commit();
    }
    protected void CalendarViewEvent(View v) {
        calendarView.setOnDateChangedListener(new OnDateSelectedListener() {
            @Override
            public void onDateSelected(@NonNull MaterialCalendarView widget, @NonNull CalendarDay date, boolean selected) {
                Intent intent = new Intent(getActivity(), ScheduleAlarmListPopupActivity.class);

                int year = date.getYear();
                int month = date.getMonth();
                int day = date.getDay();
                String checkDate = String.valueOf(year) + "년 " + String.valueOf(month) + "월 " + String.valueOf(day) + "일";
                Log.d("날짜",checkDate);
                intent.putExtra("data", checkDate);
                startActivityForResult(intent, 1);
            }
        });
    }
    public void setCalendarSpotSpread(){
        ArrayList<CalendarDay> dayList = new ArrayList<>();
        for(Alarm a : alarmList){
            Calendar cal = new GregorianCalendar(); //그레고리력 달력 생성
            String strArr[] =  a.getDay().split("-");
            int i = 0;
            for(String s: strArr){
                if(i == 0) cal.set(Calendar.YEAR,Integer.parseInt(s));
                else if(i == 1) cal.set(Calendar.MONTH,Integer.parseInt(s)-1);
                else cal.set(Calendar.DATE,Integer.parseInt(s));
                i++;
            }
            CalendarDay day = CalendarDay.from(cal);
            dayList.add(day);
        }
        calendarView.addDecorator(new EventDecorator(Color.parseColor(spotColor),dayList));
    }
    @Override
    public void clickSuccess(List<Alarm> list) {
        alarmList.addAll(list);
        setCalendarSpotSpread();
    }
}
