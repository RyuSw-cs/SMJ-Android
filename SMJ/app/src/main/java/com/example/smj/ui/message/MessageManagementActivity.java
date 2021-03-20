package com.example.smj.ui.message;

import android.content.Context;
import android.os.Bundle;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.smj.R;

import java.util.ArrayList;

public class MessageManagementActivity extends AppCompatActivity {

    ArrayList<MessageManagementData> item = new ArrayList<>();

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_message_management);

        item.add(new MessageManagementData("image","미당미당님","내일 점심","2시간전",1));
        item.add(new MessageManagementData("image","미당미당님2","내일 점심2","3시간전",2));
        RecyclerView messageRecyclerView = findViewById(R.id.message_management_list);

        LinearLayoutManager manager
                = new LinearLayoutManager(this, LinearLayoutManager.VERTICAL,false);

        messageRecyclerView.setLayoutManager(manager);
        messageRecyclerView.setAdapter(new MessageManagementAdapter(this, item));

    }
}
