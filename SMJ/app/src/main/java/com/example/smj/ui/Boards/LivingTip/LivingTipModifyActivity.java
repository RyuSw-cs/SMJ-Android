package com.example.smj.ui.Boards.LivingTip;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import com.example.smj.Manager.JWTManager;
import com.example.smj.R;
import com.example.smj.data.entity.board.boardPostData;
import com.example.smj.domain.usecase.LivingTipUseCase;

public class LivingTipModifyActivity extends AppCompatActivity {

    private int id;
    private String title;
    private String content;
    private Spinner categorySpinner;
    private EditText titleView;
    private EditText contentView;
    private int selectSpinner;
    private Button upload;
    LivingTipUseCase livingTipUseCase;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_living_tip_modify);
        Intent getIntent = getIntent();

        titleView = findViewById(R.id.living_tip_title);
        contentView = findViewById(R.id.living_tip_content);
        upload = findViewById(R.id.living_tip_modify_upload);

        id = getIntent.getExtras().getInt("id");
        title = getIntent.getExtras().getString("title");
        content = getIntent.getExtras().getString("content");

        Log.d("modifyLog",id+title+content);
        category();
        titleView.setText(title);
        contentView.setText(content);

        upload.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                livingTipUseCase = new LivingTipUseCase();
                Log.d("modifyLog",selectSpinner + titleView.getText().toString() + contentView.getText().toString());
                livingTipUseCase.putData(new boardPostData(selectSpinner,"LIVE",titleView.getText().toString(),contentView.getText().toString(),"123","123", "123"),
                        JWTManager.getSharedPreference(getApplicationContext(),getString(R.string.saved_JWT)),id,getApplicationContext());
                finish();
                
            }
        });

    }

    public void category() {
        categorySpinner = findViewById(R.id.living_tip_category);

        ArrayAdapter<CharSequence> category = ArrayAdapter.createFromResource(this, R.array.transaction_category, android.R.layout.simple_spinner_dropdown_item);
        category.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        categorySpinner.setAdapter(category);

        categorySpinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                selectSpinner = position + 1;
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });
    }
}
