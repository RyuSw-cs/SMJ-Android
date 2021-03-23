package com.example.smj.ui.create;

import android.content.ClipData;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.Spinner;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.content.ContextCompat;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.smj.R;

import java.io.InputStream;
import java.util.ArrayList;

public class CreateTradeActivity extends AppCompatActivity {
    Spinner spinner;
    ImageButton gallerybtn;
    RecyclerView photolist;
    ImageView image;
    ArrayList<PhotoData> photoData = new ArrayList<>();
    private static int PICK_IMAGE_REQUEST = 7;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_create_trade);

        category();
        gallery();

        photolist = findViewById(R.id.photo_recyclerView);
        image = findViewById(R.id.photo);

        LinearLayoutManager manager = new LinearLayoutManager(this, LinearLayoutManager.HORIZONTAL,false);
        photolist.setLayoutManager(manager);
        photolist.setAdapter(new CreatephotoAdapter(this));
    }

    public void category(){
        spinner = findViewById(R.id.category_spinner);

        ArrayAdapter<CharSequence> category = ArrayAdapter.createFromResource(this, R.array.category, android.R.layout.simple_spinner_dropdown_item);

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

    public void gallery(){
        gallerybtn = findViewById(R.id.gallerybtn);
        gallerybtn.setOnClickListener(new View.OnClickListener() {
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
            if(data == null){
                Toast.makeText(this,"다중선택이 불가한 기기입니다.",Toast.LENGTH_LONG).show();
            }
            else{
                //ClipData 또는 Uri를 가져온다
                Uri uri = data.getData();
                ClipData clipData = data.getClipData();

                //이미지 URI 를 이용하여 이미지뷰에 순서대로 세팅한다.
                if(clipData!=null)
                {
                    for(int i = 0; i < 7; i++)
                    {
                        if(i<clipData.getItemCount()){
                            Uri urione =  clipData.getItemAt(i).getUri();
                            switch (i){
                                case 0:
                                    image.setImageURI(urione);
                                    break;
                                case 1:
                                    image.setImageURI(urione);
                                    break;
                                case 2:
                                    image.setImageURI(urione);
                                    break;
                            }
                        }
                    }
                    photolist.setVisibility(View.VISIBLE);
                }
                else if(uri != null)
                {
                    image.setImageURI(uri);
                }
            }
        }
    }
}
