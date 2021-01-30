package com.example.smj.ui.main.fragment.Convenience;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.smj.R;

public class ConvenienceAdapter extends RecyclerView.Adapter<ConvenienceAdapter.MainHolder> {

    private MainHolder mainHolder;
    private Context context;
    private String[] list;
    private int selectedPosition = -1;
    private OnItemClickListener onItemClickListener;

    public ConvenienceAdapter(Context context, String[] list) {
        this.context = context;
        this.list = list;
    }

    @NonNull
    @Override
    public MainHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View holderView = LayoutInflater.from(parent.getContext()).inflate(R.layout.activity_convenience_item,parent,false);
        mainHolder = new MainHolder(holderView);
        return mainHolder;
    }

    public interface OnItemClickListener{
        public void onItemClick(View view, int position);
    }

    public void setOnItemClickListener(OnItemClickListener onItemClickListener){
        this.onItemClickListener = onItemClickListener;
    }

    @Override
    public void onBindViewHolder(@NonNull MainHolder holder, int position) {
        //배열을 만들어서 여기에 가져다 넣자.
        holder.textView.setText(list[position]);
        if(selectedPosition == position){
            holder.textView.setBackgroundResource(R.drawable.convenience_button_background_clicked);
        }
        else{
            holder.textView.setBackgroundResource(R.drawable.convenience_button_background_normal);
        }
    }

    @Override
    public int getItemCount() {
        return list.length;
    }

    public class MainHolder extends RecyclerView.ViewHolder{
        private TextView textView;
        public MainHolder(@NonNull View itemView) {
            super(itemView);
            textView = itemView.findViewById(R.id.convenience_item_button);
            textView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    int pos = getAdapterPosition();
                    if(pos != RecyclerView.NO_POSITION){
                        if(onItemClickListener != null){
                            onItemClickListener.onItemClick(v, pos);
                            selectedPosition = pos;
                            notifyDataSetChanged();
                        }
                    }
                }
            });
        }
    }
}
