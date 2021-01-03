package com.example.smj.application;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.smj.R;

public class PostManageMainAdapter extends RecyclerView.Adapter<PostManageMainAdapter.MainHolder> {
    private String[] title, contents,date,comment;
    MainHolder mainHolder;

    public PostManageMainAdapter(String[] title, String[] contents, String[] date, String[] comment) {

        this.title = title;



        this.contents = contents;



        this.date = date;

        this.comment = comment;
    }

    public static class MainHolder extends RecyclerView.ViewHolder {


        public TextView title, contents,  date, comment;


        public MainHolder(View view) {

            super(view);

            this.title = view.findViewById(R.id.postmanage_item_textView1);

            this.contents = view.findViewById(R.id.postmanage_item_textView2);

            this.date = view.findViewById(R.id.postmanage_item_textView3);

            this.comment = view.findViewById(R.id.postmanage_item_textView4);

        }

    }

    @NonNull
    @Override
    public PostManageMainAdapter.MainHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {

        View holderView = LayoutInflater.from(parent.getContext()).inflate(R.layout.activity_postmanage_item, parent, false);

        mainHolder = new MainHolder(holderView);


        return mainHolder;

    }


    @Override

    public void onBindViewHolder(@NonNull MainHolder mainHolder, int i) {

        mainHolder.title.setText(this.title[i]);



        mainHolder.contents.setText(this.contents[i]);



        mainHolder.date.setText(this.date[i]);

        mainHolder.comment.setText(this.comment[i]);
    }

    @Override

    public int getItemCount() {

        return title.length;
    }
}

