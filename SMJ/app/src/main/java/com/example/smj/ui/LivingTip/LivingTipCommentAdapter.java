package com.example.smj.ui.LivingTip;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.smj.R;

import java.util.ArrayList;

public class LivingTipCommentAdapter extends RecyclerView.Adapter<LivingTipCommentAdapter.ViewHolder> {

    private ArrayList<LivingTipCommentData> commentData = null;

    public class ViewHolder extends RecyclerView.ViewHolder{
        private TextView date;
        private TextView commenter;
        private TextView contents;

        ViewHolder(View itemView){
            super(itemView);

            date = itemView.findViewById(R.id.comment_date);
            commenter = itemView.findViewById(R.id.commenter);
            contents = itemView.findViewById(R.id.comment_contents);
        }
    }

    public LivingTipCommentAdapter(ArrayList<LivingTipCommentData> data){
        commentData = data;
    }

    @NonNull
    @Override
    public LivingTipCommentAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        Context context = parent.getContext();
        LayoutInflater inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);

        View view = inflater.inflate(R.layout.comment_item,parent,false);
        LivingTipCommentAdapter.ViewHolder vh = new LivingTipCommentAdapter.ViewHolder(view);

        return vh;
    }

    @Override
    public void onBindViewHolder(@NonNull LivingTipCommentAdapter.ViewHolder holder, int position) {
        String date = commentData.get(position).getDate();
        String commenter = commentData.get(position).getCommenter();
        String contents = commentData.get(position).getContents();

        holder.date.setText(date);
        holder.commenter.setText(commenter);
        holder.contents.setText(contents);
    }

    @Override
    public int getItemCount() {
        return commentData.size();
    }
}

