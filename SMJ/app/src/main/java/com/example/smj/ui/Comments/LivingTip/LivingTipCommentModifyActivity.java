package com.example.smj.ui.Comments.LivingTip;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import com.example.smj.Manager.JWTManager;
import com.example.smj.R;
import com.example.smj.data.entity.Comments.CommentsPostData;
import com.example.smj.domain.usecase.LivingTipCommentsUseCase;

public class LivingTipCommentModifyActivity extends AppCompatActivity {
    LivingTipCommentData Data;
    EditText content;
    Button modifyBtn;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        Intent intent = getIntent();
        super.onCreate(savedInstanceState);
        setContentView(R.layout.comment_item_modify);
        content = findViewById(R.id.living_tip_comment_modify_text);
        modifyBtn = findViewById(R.id.living_tip_comment_modify_btn);
        Data = (LivingTipCommentData) intent.getSerializableExtra("Data");
        content.setText(Data.getContents());

        modifyBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                LivingTipCommentsUseCase livingTipCommentsUseCase = new LivingTipCommentsUseCase();

                livingTipCommentsUseCase.putData(new CommentsPostData(content.getText().toString()), JWTManager.getSharedPreference(getApplicationContext(),getString(R.string.saved_JWT)),
                        Data.getCommentId(),getApplicationContext());
                finish();
            }
        });
    }
}
