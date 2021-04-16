package com.example.smj.ui.Boards.Transaction;

import android.app.Activity;
import android.content.ClipData;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.os.Handler;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.Spinner;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.AppCompatButton;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.smj.Manager.JWTManager;
import com.example.smj.R;
import com.example.smj.data.entity.board.boardPostData;
import com.example.smj.domain.usecase.TransactionUseCase;

import java.util.ArrayList;

public class TransactionModifyActivity extends AppCompatActivity {

    private Spinner spinner;
    private ImageButton galleryBtn;
    private RecyclerView photoList;
    private ImageView image;
    private ArrayList<Uri> photoData = new ArrayList<>();
    private CreatePhotoAdapter adapter;
    private AppCompatButton upload;
    private EditText title, content;
    private TransactionUseCase transactionUseCase;
    private TransactionPostData transactionPostData;
    private String spinnerItem;
    private String[] item;
    private int selectSpinner, getItemCount;


    private static int PICK_IMAGE_REQUEST = 7;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_transaction_modify);

        category();
        gallery();

        TransactionReadingActivity.activityStack.add(this);

        photoList = findViewById(R.id.trade_photo_recyclerView);
        image = findViewById(R.id.photo);
        upload = findViewById(R.id.trade_modify_upload);
        title = findViewById(R.id.trade_title);
        content = findViewById(R.id.trade_content);

        Intent intent = getIntent();
        transactionUseCase = new TransactionUseCase(this);
        transactionPostData = (TransactionPostData)intent.getSerializableExtra("modifyData");
        spinnerItem = transactionPostData.getCategory();

        title.setText(transactionPostData.getTitle());
        content.setText(transactionPostData.getContents());

        item = getResources().getStringArray(R.array.transaction_category);

        getItemCount = item.length;

        for(int i = 0; i<getItemCount; i++){
            if(item[i].contains(spinnerItem)){
                selectSpinner = i;
                break;
            }
        }

        spinner.setSelection(selectSpinner);

        upload.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(title.getText().equals("")||content.getText().equals("")){
                    //임시 토스트
                    Toast.makeText(getApplicationContext(),"제목이나 내용,카테고리를 작성해주세요",Toast.LENGTH_LONG).show();
                }
                else{
                    //이미지 전송 변경해야함.
                    transactionUseCase.putData(new boardPostData
                                    (selectSpinner,"TRADE",title.getText().toString(),content.getText().toString(),"123","123","123"),
                            JWTManager.getSharedPreference(getApplicationContext(),getString(R.string.saved_JWT)),transactionPostData.getId(),getApplicationContext());
                }
            }
        });

        LinearLayoutManager manager = new LinearLayoutManager(this, LinearLayoutManager.HORIZONTAL, false);
        photoList.setLayoutManager(manager);
        photoList.setHasFixedSize(true);
        adapter = new CreatePhotoAdapter(this, photoData);
        photoList.setAdapter(adapter);
    }

    public void modifySuccess(){
        int getActivitySize = TransactionReadingActivity.activityStack.size();
        for(int i = 0; i<getActivitySize; i++){
            TransactionReadingActivity.activityStack.get(i).finish();
        }
    }

    public void category() {
        spinner = findViewById(R.id.trade_category);

        ArrayAdapter<CharSequence> category = ArrayAdapter.createFromResource(this, R.array.transaction_category, android.R.layout.simple_spinner_dropdown_item);
        category.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinner.setAdapter(category);

        spinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {

            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });
    }

    public void gallery() {
        galleryBtn = findViewById(R.id.trade_galleryBtn);
        galleryBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(Intent.ACTION_GET_CONTENT);
                intent.setType("image/*");
                intent.putExtra(Intent.EXTRA_ALLOW_MULTIPLE, true);
                startActivityForResult(Intent.createChooser(intent, "Select Picture"), PICK_IMAGE_REQUEST);
            }
        });
    }

    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        // Check which request we're responding to
        super.onActivityResult(requestCode, resultCode, data);

        if (requestCode == PICK_IMAGE_REQUEST) {
            if (data == null) {
                Toast.makeText(this, "다중선택이 불가한 기기입니다.", Toast.LENGTH_LONG).show();
            } else {
                //ClipData 또는 Uri를 가져온다
                Uri uri = data.getData();
                ClipData clipData = data.getClipData();

                //이미지 URI 를 이용하여 이미지뷰에 순서대로 세팅한다.
                if (clipData != null) {
                    Log.d("getItemCount", Integer.toString(clipData.getItemCount()));
                    for (int i = 0; i < clipData.getItemCount(); i++) {
                        Uri urione = clipData.getItemAt(i).getUri();
                        photoData.add(urione);
                    }
                    photoList.setVisibility(View.VISIBLE);
                } else if (uri != null) {
                    photoData.add(uri);
                    photoList.setVisibility(View.VISIBLE);
                }
            }
        }
    }
}
