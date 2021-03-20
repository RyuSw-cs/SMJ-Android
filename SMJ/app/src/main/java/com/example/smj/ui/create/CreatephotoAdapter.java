package com.example.smj.ui.create;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import androidx.annotation.NonNull;
import androidx.appcompat.view.menu.MenuView;
import androidx.recyclerview.widget.RecyclerView;

import com.example.smj.R;
import com.example.smj.ui.LivingTip.LivingTipPostAdapter;
import com.example.smj.ui.LivingTip.LivingTipPostData;
import com.example.smj.ui.message.MessageManagementAdapter;
import com.example.smj.ui.message.MessageManagementData;

import java.util.ArrayList;

public class CreatephotoAdapter extends RecyclerView.Adapter<CreatephotoAdapter.ViewHolder>{

    private Context context;
    private ArrayList<PhotoData> PhotoData = null;

    public CreatephotoAdapter(Context context, ArrayList<PhotoData> data){
        this.context = context;
        PhotoData = data;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        Context context = parent.getContext();
        LayoutInflater inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);

        View view = inflater.inflate(R.layout.activity_photolist_item,parent,false);
        CreatephotoAdapter.ViewHolder viewHolder = new CreatephotoAdapter.ViewHolder(view);

        return viewHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {

        int photo = PhotoData.get(position).getPhoto();

    }

    @Override
    public int getItemCount() {
        return 0;
    }

    public class ViewHolder extends RecyclerView.ViewHolder {

        private ImageView photo;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            photo = itemView.findViewById(R.id.photo);
        }
    }
}
