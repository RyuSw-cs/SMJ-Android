package com.example.smj.ui.Alarms;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.Window;
import android.widget.TextView;

import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.smj.R;
import com.example.smj.data.entity.Schedule.Alarm;
import com.google.android.material.floatingactionbutton.FloatingActionButton;

import java.util.ArrayList;
import java.util.List;

public class ScheduleAlarmListPopupActivity extends Activity {
    private RecyclerView recyclerView;
    private RecyclerView.LayoutManager layoutManager;
    private RecyclerView.Adapter adapter;
    private FloatingActionButton fab;
    private TextView main;
    private List<Alarm>list = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        setContentView(R.layout.activity_schedule_alarmlist_popup);
        init();
        FabEvent();
        setRecyclerView();
        getData();
    }
    protected void init(){
        fab = findViewById(R.id.schedule_popup_fab);
        recyclerView = findViewById(R.id.schedule_popup_recyclerView);
        main = findViewById(R.id.schedule_popup_date);
    }
    protected void FabEvent(){
         fab.setOnClickListener((view) ->{
             Intent intent = new Intent(this, ScheduleAlarmPageActivity.class);
             intent.putExtra("data", "Test Popup");
             startActivityForResult(intent, 1);
         });
    }
    protected void setRecyclerView(){
        layoutManager = new LinearLayoutManager(this);
        recyclerView.setLayoutManager(layoutManager);
        adapter = new ScheduleAlarmListAdapter(list);
        recyclerView.setAdapter(adapter);
    }
    protected void getData(){
        Intent intent = getIntent();
        String data = intent.getStringExtra("data");
        main.setText(data);
    }
    protected void setData(){
        Intent intent = new Intent();
        intent.putExtra("result", "Close Popup");
        setResult(RESULT_OK, intent);
    }
    //확인 버튼 클릭
    public void mOnClose(View v){
        setData();
        finish();
    }

}
