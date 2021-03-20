package com.example.smj.ui.transaction;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.Window;
import android.widget.Button;

import com.example.smj.R;

public class TransactionMoreInfo extends Activity {

    private Button modify, delete;
    private String id;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        setContentView(R.layout.reading_view_more);
        init();
    }
    private void init(){
        Intent intent = getIntent();
        id = intent.getStringExtra("id");
        modify = findViewById(R.id.reading_modified);
        delete = findViewById(R.id.reading_delete);

        //삭제, 수정

        modify.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getApplicationContext(),TransactionModifyPopupActivity.class);
                intent.putExtra("modifyId",id);
                startActivity(intent);
                finish();
            }
        });
        delete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getApplicationContext(),TransactionDeletePopupActivity.class);
                intent.putExtra("deleteId",id);
                startActivity(intent);
                finish();
            }
        });
    }
}
