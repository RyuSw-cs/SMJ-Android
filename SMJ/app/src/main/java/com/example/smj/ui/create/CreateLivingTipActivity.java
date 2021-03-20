package com.example.smj.ui.create;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.Spinner;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.smj.R;
import com.example.smj.ui.message.MessageManagementAdapter;

import java.util.ArrayList;


public class CreateLivingTipActivity extends AppCompatActivity {
    Spinner spinner;
    ImageButton gallerybtn;
    RecyclerView photolist;
    ArrayList<PhotoData> photoData = new ArrayList<>();

    private static int PICK_IMAGE_REQUEST = 1;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_create_living_tip);
        photolist = findViewById(R.id.photo_recyclerView);

        category();
        gallery();

        LinearLayoutManager manager = new LinearLayoutManager(this, LinearLayoutManager.HORIZONTAL,false);
        photolist.setLayoutManager(manager);
        photolist.setAdapter(new CreatephotoAdapter(this,photoData));

        photoData.add(new PhotoData(R.drawable.profileimage));
    }

    public void category(){
        spinner = findViewById(R.id.category_spinner);

        ArrayAdapter category = ArrayAdapter.createFromResource(this, R.array.category, android.R.layout.simple_spinner_dropdown_item);

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
                startActivityForResult(Intent.createChooser(intent, "Select Picture"), PICK_IMAGE_REQUEST);
            }
        });
    }
}
