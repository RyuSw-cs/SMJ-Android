package com.example.smj.ui.schedule;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.Window;

import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.smj.R;
import com.google.android.material.floatingactionbutton.FloatingActionButton;

public class ScheduleAlarmlistPopupActivity extends Activity {
    RecyclerView recyclerView;
    RecyclerView.LayoutManager layoutManager;
    RecyclerView.Adapter adapter;
    FloatingActionButton fab;
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
        String[] title =  {"제목","제목","제목","제목"};
        String[] date =  {"날짜","날짜","날짜","날짜"};
        adapter = new ScheduleAlarmlistAdapter(title,date);
        recyclerView.setAdapter(adapter);
    }
    protected void getData(){
        Intent intent = getIntent();
        String data = intent.getStringExtra("data");
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
