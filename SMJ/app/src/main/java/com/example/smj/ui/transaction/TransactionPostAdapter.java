package com.example.smj.ui.transaction;

import android.content.Context;
import android.content.Intent;
import android.os.Parcelable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.core.content.ContextCompat;
import androidx.recyclerview.widget.RecyclerView;

import com.example.smj.R;
import com.example.smj.data.repository.TransactionApi;
import com.example.smj.ui.LivingTip.LivingTipPostData;

import java.util.ArrayList;
import java.util.List;

public class TransactionPostAdapter extends RecyclerView.Adapter<TransactionPostAdapter.ViewHolder> {

    private Context context;
    private ArrayList<TransactionPostData> postData = null;

    public class ViewHolder extends RecyclerView.ViewHolder{
        private TextView category;
        private TextView title;
        private TextView contents;
        private TextView writer;
        private TextView date;
        private ImageView profileImage;

        ViewHolder(View itemView){
            super(itemView);
            category = itemView.findViewById(R.id.post_item_category);
            title = itemView.findViewById(R.id.post_item_title);
            contents = itemView.findViewById(R.id.post_item_contents);
            writer = itemView.findViewById(R.id.post_item_writer);
            date = itemView.findViewById(R.id.post_item_date);
            profileImage = itemView.findViewById(R.id.profileImage);
        }
    }

    public TransactionPostAdapter(Context context, List<TransactionPostData> data){
        this.context = context;
        postData = (ArrayList) data;
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

        String date = getDate[0]+"년 "+getDate[1]+"월 "+getDate[2] + "일";
        //String profileImage = postData.get(position).getProfileImage();

        holder.category.setText(category);
        holder.title.setText(title);
        holder.contents.setText(contents);
        holder.writer.setText(writer);
        holder.date.setText(date);
        //holder.category.setText(profileImage);
        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //리사이클러뷰 해당 아이템의 값 전달
                Context context = v.getContext();
                Intent intent = new Intent(context,TransactionReadingActivity.class);
                intent.putExtra("data", postData.get(position));
                context.startActivity(intent);
            }
        });
    }

    @Override
    public int getItemCount() {
        return postData.size();
    }
}