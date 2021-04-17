package com.example.smj.ui.Boards.Transaction.Adapter;

import android.content.Context;
import android.net.Uri;
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

        //글쓰기 할때 이미지 리스트 나오게 하는 리사이클러뷰
        View view = inflater.inflate(R.layout.activity_photolist_item,parent,false);
        CreatePhotoAdapter.ViewHolder vh = new CreatePhotoAdapter.ViewHolder(view);

        return vh;
    }

    @Override
    public void onBindViewHolder(@NonNull CreatePhotoAdapter.ViewHolder holder, int position) {
        //메인 이미지 설정(게시글 첫번째 사진으로)
        holder.photo.setImageURI(photoData.get(position));
        holder.button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                photoData.remove(position);
                notifyDataSetChanged();
            }
        });
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
