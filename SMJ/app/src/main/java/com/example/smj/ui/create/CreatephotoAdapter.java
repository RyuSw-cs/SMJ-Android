package com.example.smj.ui.create;

import android.content.Context;
import android.content.res.Resources;
import android.graphics.drawable.Drawable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.Button;
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

public class CreatephotoAdapter extends RecyclerView.Adapter<CreatephotoAdapter.ViewHolder> {

    private ArrayList<Drawable> photoData = null;
    private Context context;
    private ImageView imageView;

    public CreatephotoAdapter(Context context, ImageView imageView){
        this.context = context;
        this.imageView = imageView;
    }

    @NonNull
    @Override
    public CreatephotoAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);

        View view = inflater.inflate(R.layout.activity_photolist_item,parent,false);
        CreatephotoAdapter.ViewHolder vh = new CreatephotoAdapter.ViewHolder(view);

        return vh;
    }

    @Override
    public void onBindViewHolder(@NonNull CreatephotoAdapter.ViewHolder holder, int position) {
        holder.photo.setImageDrawable(context.getDrawable(R.drawable.profileimage));
    }

    @Override
    public int getItemCount() {
        return 7;
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
