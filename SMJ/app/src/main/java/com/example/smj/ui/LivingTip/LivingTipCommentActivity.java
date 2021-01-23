package com.example.smj.ui.LivingTip;

import android.os.Bundle;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.smj.R;

import java.util.ArrayList;

public class LivingTipCommentActivity extends AppCompatActivity {

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_living_tip_comment);

        ArrayList<LivingTipCommentData> data = new ArrayList<>();
        data.add(new LivingTipCommentData("날짜","작성자","내용"));
        data.add(new LivingTipCommentData("날짜2","작성자2","내용2"));

        RecyclerView recyclerView = findViewById(R.id.living_tip_comment_list);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));

        LivingTipCommentAdapter adapter = new LivingTipCommentAdapter(data);
        recyclerView.setAdapter(adapter);

    }
}
