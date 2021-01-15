package com.example.smj.ui.schedule;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.smj.R;

public class ScheduleAlarmlistAdapter extends RecyclerView.Adapter<ScheduleAlarmlistAdapter.MainHolder> {
    private String[] title, date;
    ScheduleAlarmlistAdapter.MainHolder mainHolder;

    public ScheduleAlarmlistAdapter(String[] title,String[] date) {

        this.title = title;

        this.date = date;

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
    public ScheduleAlarmlistAdapter.MainHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {

        View holderView = LayoutInflater.from(parent.getContext()).inflate(R.layout.activity_schedule_alarmlist_popup_item, parent, false);

        mainHolder = new ScheduleAlarmlistAdapter.MainHolder(holderView);

        return mainHolder;

    }

    @Override
    public void onBindViewHolder(@NonNull ScheduleAlarmlistAdapter.MainHolder mainHolder, int i) {

        mainHolder.title.setText(this.title[i]);

        mainHolder.date.setText(this.date[i]);
    }

    @Override

    public int getItemCount() {

        return title.length;
    }
}
