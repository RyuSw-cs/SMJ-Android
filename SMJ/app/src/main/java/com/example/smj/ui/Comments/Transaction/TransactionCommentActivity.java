package com.example.smj.ui.Comments.Transaction;

import android.os.Bundle;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.smj.R;

import java.util.ArrayList;

public class TransactionCommentActivity extends AppCompatActivity {
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_transaction_comment);

        ArrayList<TransactionCommentData> data = new ArrayList<>();
        data.add(new TransactionCommentData("날짜", "작성자", "내용"));
        data.add(new TransactionCommentData("날짜2", "작성자2", "내용2"));

        RecyclerView recyclerView = findViewById(R.id.transaction_comment_list);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));

        TransactionCommentAdapter adapter = new TransactionCommentAdapter(data);
        recyclerView.setAdapter(adapter);
    }
}
