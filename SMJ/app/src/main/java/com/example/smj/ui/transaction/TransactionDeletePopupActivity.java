package com.example.smj.ui.transaction;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.Window;
import android.widget.Button;

import com.example.smj.R;
import com.example.smj.domain.usecase.TransactionUseCase;

public class TransactionDeletePopupActivity extends Activity {

    private Button cancel, check;
    private TransactionUseCase transactionUseCase;
    private String id;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        setContentView(R.layout.activity_board_delete_popup);
        init();
    }

    private void init(){
        Intent intent = getIntent();
        id = intent.getStringExtra("deleteId");
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
                //글 삭제 통신
                //삭제 완료 후 토스트 메시지
                //삭제 시 500오류 나옴 id값이 어떻게 가는지 모르겠음
                transactionUseCase = new TransactionUseCase();
                transactionUseCase.deleteData("eyJhbGciOiJIUzI1NiJ9.eyJzdWIiOiJyc3cxNDUyQG5hdmVyLmNvbSIsImlhdCI6MTYxNjA5NzI4MiwiZXhwIjoxNjE2MDk5MDgyfQ.iUtb8FI96ioOFjLSClCwnpS7m-tgf2KT6xJL-LwhzqA"
                        ,id,getApplicationContext());
                finish();
            }
        });
    }
}
