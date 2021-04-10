package com.example.smj.ui.Alarms;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.smj.Manager.JWTManager;
import com.example.smj.R;
import com.example.smj.data.entity.Schedule.Alarm;
import com.example.smj.domain.usecase.ScheduleUseCase;

import java.util.ArrayList;
import java.util.List;

public class ScheduleAlarmListAdapter extends RecyclerView.Adapter<ScheduleAlarmListAdapter.MainHolder> {
    private List<Alarm> getList = new ArrayList<>();

    ScheduleAlarmListAdapter.MainHolder mainHolder;

    public ScheduleAlarmListAdapter(List<Alarm>data) {

        this.getList = data;


    }

    public static class MainHolder extends RecyclerView.ViewHolder {
        public TextView title, date;
        public MainHolder(View view) {
            super(view);
            this.title = view.findViewById(R.id.schedule_alarmlist_popup_title);
            this.date = view.findViewById(R.id.schedule_alarmlist_popup_date);
        }
    }

    @NonNull
    @Override
    public ScheduleAlarmListAdapter.MainHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View holderView = LayoutInflater.from(parent.getContext()).inflate(R.layout.activity_schedule_alarmlist_popup_item, parent, false);
        mainHolder = new ScheduleAlarmListAdapter.MainHolder(holderView);
        return mainHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull ScheduleAlarmListAdapter.MainHolder mainHolder, int i) {
        mainHolder.title.setText(getList.get(i).getTitle());
        mainHolder.date.setText(getList.get(i).getstartDate());
    }

    @Override

    public int getItemCount() {
        return getList.size();
    }




}
