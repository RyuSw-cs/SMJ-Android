package com.example.smj.ui.Boards.LivingTip;

import android.content.ClipData;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.smj.Manager.JWTManager;
import com.example.smj.R;
import com.example.smj.data.entity.board.boardPostData;
import com.example.smj.domain.usecase.LivingTipUseCase;
import com.example.smj.ui.Boards.Transaction.Adapter.CreatePhotoAdapter;

import java.net.URI;
import java.util.ArrayList;

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
    private ArrayList<Uri> photoData = new ArrayList<>();
    private ImageButton galleryBtn;
    private static int PICK_IMAGE_REQUEST = 7;
    private RecyclerView photoList;
    private CreatePhotoAdapter adapter;
    private Uri uri;
    private Uri imageUri1;
    private Uri imageUri2;
    private Uri imageUri3;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_living_tip_modify);
        Intent getIntent = getIntent();

        category();
        gallery();

        photoList = findViewById(R.id.living_tip_photo_recyclerView);
        titleView = findViewById(R.id.living_tip_title);
        contentView = findViewById(R.id.living_tip_content);
        upload = findViewById(R.id.living_tip_modify_upload);

        id = getIntent.getExtras().getInt("id");
        title = getIntent.getExtras().getString("title");
        content = getIntent.getExtras().getString("content");
        imageUri1 = Uri.parse(getIntent.getExtras().getString("image1"));
        imageUri2 = Uri.parse(getIntent.getExtras().getString("image2"));
        imageUri3 = Uri.parse(getIntent.getExtras().getString("image3"));

        Log.d("수정 URI",imageUri1.toString() + " 2 : "   + imageUri2.toString() + " 3 : " + imageUri3.toString());

        if(!imageUri1.toString().equals("0")){
            photoData.add(imageUri1);
            photoList.setVisibility(View.VISIBLE);
        }
        if(!imageUri2.toString().equals("0")){
            photoData.add(imageUri2);
            photoList.setVisibility(View.VISIBLE);
        }
        if(!imageUri3.toString().equals("0")){
            photoData.add(imageUri3);
            photoList.setVisibility(View.VISIBLE);
        }

        Log.d("modifyLog",id+title+content);

        titleView.setText(title);
        contentView.setText(content);

        upload.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                livingTipUseCase = new LivingTipUseCase();
                Log.d("modifyLog2",selectSpinner + titleView.getText().toString() + contentView.getText().toString());

                Log.d("photoData size", String.valueOf(photoData.size()));

                int size = photoData.size();
                switch (size){
                    case 0: livingTipUseCase.putData(new boardPostData(selectSpinner,"LIVE",titleView.getText().toString(),contentView.getText().toString(),
                                    "0","0","0"),
                            JWTManager.getSharedPreference(getApplicationContext(),getString(R.string.saved_JWT)),id,getApplicationContext());
                        break;

                    case 1: livingTipUseCase.putData(new boardPostData(selectSpinner,"LIVE",titleView.getText().toString(),contentView.getText().toString(),
                                    photoData.get(0).toString(),"0","0"),
                            JWTManager.getSharedPreference(getApplicationContext(),getString(R.string.saved_JWT)),id,getApplicationContext());
                        break;

                    case 2: livingTipUseCase.putData(new boardPostData(selectSpinner,"LIVE",titleView.getText().toString(),contentView.getText().toString(),
                                    photoData.get(0).toString(),photoData.get(1).toString(),"0"),
                            JWTManager.getSharedPreference(getApplicationContext(),getString(R.string.saved_JWT)),id,getApplicationContext());
                        break;

                    default: livingTipUseCase.putData(new boardPostData(selectSpinner,"LIVE",titleView.getText().toString(),contentView.getText().toString(),
                                    photoData.get(0).toString(),photoData.get(1).toString(),photoData.get(2).toString()),
                            JWTManager.getSharedPreference(getApplicationContext(),getString(R.string.saved_JWT)),id,getApplicationContext());
                        break;
                }
                finish();
            }
        });

        LinearLayoutManager manager = new LinearLayoutManager(this, LinearLayoutManager.HORIZONTAL, false);
        photoList.setLayoutManager(manager);
        photoList.setHasFixedSize(true);
        adapter = new CreatePhotoAdapter(this, photoData);
        photoList.setAdapter(adapter);
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
                uri = data.getData();
                ClipData clipData = data.getClipData();

                //이미지 URI 를 이용하여 이미지뷰에 순서대로 세팅한다.
                if (clipData != null) { //여러개 선택
                    Log.d("getItemCount", Integer.toString(clipData.getItemCount()));
                    for (int i = 0; i < clipData.getItemCount(); i++) {
                        Uri urione = clipData.getItemAt(i).getUri();
                        photoData.add(urione);
                    }
                    photoList.setVisibility(View.VISIBLE);
                } else if (uri != null) { //1개씩 선택
                    photoData.add(uri);
                    photoList.setVisibility(View.VISIBLE);
                }
            }
        }
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
