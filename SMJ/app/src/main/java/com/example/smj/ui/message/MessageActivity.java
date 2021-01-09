package com.example.smj.ui.message;

import android.os.Bundle;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.smj.R;

import java.util.ArrayList;

public class MessageActivity extends AppCompatActivity {

    ArrayList<ChatMessage> item = new ArrayList<>();

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_message);

        item.add(new ChatMessage("my","안녕하세요"));
        item.add(new ChatMessage("you","네 안녕하세요"));
        item.add(new ChatMessage("my","내일 거래 가능 하신가요?"));

        RecyclerView messageRecyclerView = findViewById(R.id.message_recycler_view);

        LinearLayoutManager manager
                = new LinearLayoutManager(this, LinearLayoutManager.VERTICAL,false);

        messageRecyclerView.setLayoutManager(manager);
        messageRecyclerView.setAdapter(new MessageItemAdapter(item));

    }
}
