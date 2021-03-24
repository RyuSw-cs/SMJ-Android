package com.example.smj.ui.create;

import android.content.Context;
import android.graphics.drawable.Drawable;
import android.net.Uri;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.smj.R;

import java.util.ArrayList;

public class CreatePhotoAdapter extends RecyclerView.Adapter<CreatePhotoAdapter.ViewHolder> {

    private ArrayList<Uri> photoData = null;
    private Context context;

    public CreatePhotoAdapter(Context context, ArrayList<Uri> photoData){
        this.context = context;
        this.photoData = photoData;
    }

    @NonNull
    @Override
    public CreatePhotoAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);

        View view = inflater.inflate(R.layout.activity_photolist_item,parent,false);
        CreatePhotoAdapter.ViewHolder vh = new CreatePhotoAdapter.ViewHolder(view);

        return vh;
    }

    @Override
    public void onBindViewHolder(@NonNull CreatePhotoAdapter.ViewHolder holder, int position) {
        holder.photo.setImageURI(photoData.get(position));
    }

    @Override
    public int getItemCount() {
        return photoData.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {

        private ImageView photo;
        private Button button;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);

            photo = itemView.findViewById(R.id.photo);
            button = itemView.findViewById(R.id.deletebutton);
        }
    }
}
