package com.example.smj.ui.Boards.Transaction.Adapter;

import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.util.Base64;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.smj.R;
import com.example.smj.domain.usecase.TransactionUseCase;
import com.example.smj.ui.Boards.Transaction.CreatePhotoData;
import com.example.smj.ui.Boards.Transaction.TransactionPostData;
import com.example.smj.ui.Boards.Transaction.TransactionReadingActivity;

import java.util.ArrayList;
import java.util.List;

public class TransactionPostAdapter extends RecyclerView.Adapter<TransactionPostAdapter.ViewHolder> {

    private Context context;
    private ArrayList<TransactionPostData> postData = null;
    private TransactionUseCase transactionUseCase;

    public class ViewHolder extends RecyclerView.ViewHolder{
        private TextView category;
        private TextView title;
        private TextView contents;
        private TextView writer;
        private TextView date;
        private ImageView mainImage;

        ViewHolder(View itemView){
            super(itemView);
            category = itemView.findViewById(R.id.post_item_category);
            title = itemView.findViewById(R.id.post_item_title);
            contents = itemView.findViewById(R.id.post_item_contents);
            writer = itemView.findViewById(R.id.post_item_writer);
            date = itemView.findViewById(R.id.post_item_date);
            mainImage = itemView.findViewById(R.id.post_item_profile_image);
        }
    }

    public TransactionPostAdapter(Context context, List<TransactionPostData> data, TransactionUseCase transactionUseCase){
        this.context = context;
        postData = (ArrayList) data;
        this.transactionUseCase = transactionUseCase;
    }

    @NonNull
    @Override
    public TransactionPostAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.post_item,parent,false);
        TransactionPostAdapter.ViewHolder vh = new TransactionPostAdapter.ViewHolder(view);
        return vh;
    }

    @Override
    public void onBindViewHolder(@NonNull TransactionPostAdapter.ViewHolder holder, int position) {
        String category = postData.get(position).getCategory();
        String title = postData.get(position).getTitle();
        String contents = postData.get(position).getContents();
        String writer = postData.get(position).getWriter();

        String []getDate = postData.get(position).getDate();

        String date = getDate[0]+"-"+getDate[1]+"-"+getDate[2] + " " + getDate[3]+":"+getDate[4];
        //보드 메인페이지 첫번째 사진 설정

        if(!postData.get(position).getImageOne().equals("")){
            Bitmap mainImage = stringToBitmap(postData.get(position).getImageOne());
            holder.mainImage.setImageBitmap(mainImage);
        }

        holder.category.setText(category);
        holder.title.setText(title);
        holder.contents.setText(contents);
        holder.writer.setText(writer);
        holder.date.setText(date);

        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //리사이클러뷰 해당 아이템의 값 전달
                Context context = v.getContext();
                Intent intent = new Intent(context, TransactionReadingActivity.class);
                intent.putExtra("data", postData.get(position));
                context.startActivity(intent);
            }
        });
    }

    @Override
    public int getItemCount() {
        return postData.size();
    }

    public void adapterRefresh(){
        notifyDataSetChanged();
    }

    public Bitmap stringToBitmap(String data){
        Bitmap bitmap = null;
        try{
            byte [] encodeByte= Base64.decode(data,Base64.DEFAULT);
            bitmap= BitmapFactory.decodeByteArray(encodeByte, 0, encodeByte.length);
        }catch (Exception e){
            e.printStackTrace();
        }
        return bitmap;
    }
}