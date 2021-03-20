package com.example.smj.ui.transaction;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.Window;
import android.widget.Button;

import com.example.smj.R;

public class TransactionModifyPopupActivity extends Activity {

    private Button cancel, check;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        setContentView(R.layout.activity_board_modify_popup);
        init();
    }
    private void init(){
        cancel = findViewById(R.id.popup_delete_cancel);
        check = findViewById(R.id.popup_delete);
        cancel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
        check.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //미완성
                Intent intent = new Intent(getApplicationContext(), TransactionModifyActivity.class);
                startActivity(intent);
            }
        });
    }
}
