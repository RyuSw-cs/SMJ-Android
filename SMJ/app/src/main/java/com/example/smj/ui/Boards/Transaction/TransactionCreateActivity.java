package com.example.smj.ui.Boards.Transaction;

import android.content.ClipData;
import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.os.Bundle;
import android.os.Environment;
import android.provider.MediaStore;
import android.util.Base64;
import android.util.Log;
import android.view.View;
import android.view.inputmethod.InputMethodManager;
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
import com.example.smj.ui.Boards.Transaction.Adapter.CreatePhotoAdapter;

import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;

public class TransactionCreateActivity extends AppCompatActivity {

    private Spinner spinner;
    private ImageButton galleryBtn;
    private RecyclerView photoList;
    private ImageView image;
    private ArrayList<Uri> photoData = new ArrayList<>(3);
    private CreatePhotoAdapter adapter;
    private AppCompatButton upload;
    private EditText title, content;
    private TransactionUseCase transactionUseCase;
    private int selectSpinner;
    private Uri uri;
    private String[] bitmapData;

    private static int PICK_IMAGE_REQUEST = 7;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_transaction_create);

        category();
        gallery();

        photoList = findViewById(R.id.photo_recyclerView);
        image = findViewById(R.id.photo);
        upload = findViewById(R.id.trade_upload);
        title = findViewById(R.id.trade_title);
        content = findViewById(R.id.trade_content);

        transactionUseCase = new TransactionUseCase(this);

        upload.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (title.getText().equals("") || content.getText().equals("")) {
                    //임시 토스트
                    Toast.makeText(getApplicationContext(), "제목이나 내용, 카테고리를 작성해주세요", Toast.LENGTH_LONG).show();
                } else {
                    try {
                        bitmapData = bitmapToString(photoData);
                        transactionUseCase.postData(new boardPostData
                                        (selectSpinner, "TRADE", title.getText().toString(), content.getText().toString(), bitmapData[0], bitmapData[1], bitmapData[2]),
                                JWTManager.getSharedPreference(getApplicationContext(), getString(R.string.saved_JWT)), getApplicationContext());
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                }
            }
        });

        LinearLayoutManager manager = new LinearLayoutManager(this, LinearLayoutManager.HORIZONTAL, false);
        photoList.setLayoutManager(manager);
        photoList.setHasFixedSize(true);
        adapter = new CreatePhotoAdapter(this, photoData);
        photoList.setAdapter(adapter);
    }

    public void category() {
        spinner = findViewById(R.id.category_spinner);

        ArrayAdapter<CharSequence> category = ArrayAdapter.createFromResource(this, R.array.transaction_category, android.R.layout.simple_spinner_dropdown_item);
        category.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinner.setAdapter(category);

        spinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                selectSpinner = position + 1;
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });
    }

    public void gallery() {
        galleryBtn = findViewById(R.id.gallerybtn);
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
                uri = data.getData();
                ClipData clipData = data.getClipData();

                //이미지 URI 를 이용하여 이미지뷰에 순서대로 세팅한다.
                if (clipData != null) {//다중선택
                    Log.d("getItemCount", Integer.toString(clipData.getItemCount()));
                    for (int i = 0; i < clipData.getItemCount(); i++) {
                        Uri urione = clipData.getItemAt(i).getUri();
                        photoData.add(urione);
                    }
                    photoList.setVisibility(View.VISIBLE);
                } else if (uri != null) {//단일선택
                    photoData.add(uri);
                    photoList.setVisibility(View.VISIBLE);
                }
            }
        }
    }
    public String[] bitmapToString(ArrayList<Uri>photoData) throws IOException {
        String[] data = {"","",""};
        int size = photoData.size();
        for(int i = 0; i<size; i++){
            Bitmap bitmap = MediaStore.Images.Media.getBitmap(getApplicationContext().getContentResolver(),photoData.get(i));
            ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
            bitmap.compress(Bitmap.CompressFormat.PNG,100,byteArrayOutputStream);
            byte[] bytes = byteArrayOutputStream.toByteArray();
            data[i] = Base64.encodeToString(bytes, Base64.DEFAULT);
        }
        return data;
    }
}
