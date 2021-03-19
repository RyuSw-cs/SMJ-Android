package com.example.smj.ui.transaction;

import android.os.Bundle;
import android.view.Window;

import androidx.appcompat.app.AppCompatActivity;

import com.example.smj.R;

public class TransactionModifyActivity extends AppCompatActivity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        //레이아웃 수정해주세요!
        setContentView(R.layout.activity_board_delete_popup);
    }
}
