package com.example.smj.ui.Comments.Transaction.Adapter;

import android.app.Dialog;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.smj.R;
import com.example.smj.data.entity.Comments.CommentsPostData;
import com.example.smj.data.entity.Member.MemberData;
import com.example.smj.domain.usecase.CommentsUseCase;
import com.example.smj.ui.Comments.Transaction.TransactionCommentData;

import java.util.List;

public class TransactionCommentAdapter extends RecyclerView.Adapter<TransactionCommentAdapter.ViewHolder> {

    private List<TransactionCommentData> commentData = null;
    private List<MemberData> memberData = null;
    private Dialog moreView;
    private Context context;
    private int getListSize;
    private CommentsUseCase commentsUseCase;
    private String token;
    private int id;

    public class ViewHolder extends RecyclerView.ViewHolder{
        private TextView date, commenter, contents;
        private ImageButton moreBtn;
        private Button modifyBtn, deleteBtn;

        ViewHolder(View itemView){
            super(itemView);
            date = itemView.findViewById(R.id.comment_date);
            commenter = itemView.findViewById(R.id.commenter);
            contents = itemView.findViewById(R.id.comment_contents);
            moreBtn = itemView.findViewById(R.id.comment_more_btn);

            moreView = new Dialog(context);
            moreView.requestWindowFeature(Window.FEATURE_NO_TITLE);
            moreView.setContentView(R.layout.comment_view_more);

            /* 코드 수정 필요! */
            modifyBtn = (Button)moreBtn.findViewById(R.id.modify_comment);
            deleteBtn = (Button)moreBtn.findViewById(R.id.delete_comment);

            moreBtn.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    moreView.show();
                }
            });

            modifyBtn.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    commentsUseCase.putData(new CommentsPostData("수정된 데이터"),token, id, context);
                }
            });

            deleteBtn.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    //삭제 통신
                    commentsUseCase.deleteData(token, id, context);
                }
            });
        }
    }

    public TransactionCommentAdapter(List<TransactionCommentData> commentData, List<MemberData> memberData, Context context, CommentsUseCase commentsUseCase, String token, int id){
        this.commentData = commentData;
        this.memberData = memberData;
        this.context = context;
        this.commentsUseCase = commentsUseCase;
        this.getListSize = memberData.size();
        this.token = token;
        this.id = id;
    }

    @NonNull
    @Override
    public TransactionCommentAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        Context context = parent.getContext();
        LayoutInflater inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);

        View view = inflater.inflate(R.layout.comment_item,parent,false);
        TransactionCommentAdapter.ViewHolder vh = new TransactionCommentAdapter.ViewHolder(view);

        return vh;
    }

    @Override
    public void onBindViewHolder(@NonNull TransactionCommentAdapter.ViewHolder holder, int position) {
        String[] getDate = commentData.get(position).getDate();
        String getCommenter = commentData.get(position).getCommenter();
        String getContents = commentData.get(position).getContents();

        String date = getDate[0]+"-"+getDate[1]+"-"+getDate[2] + " " + getDate[3]+":"+getDate[4];

        holder.date.setText(date);
        holder.commenter.setText(getCommenter);
        holder.contents.setText(getContents);


        for(int i = 0; i<getListSize; i++){
            if(holder.commenter.getText().toString().equals(memberData.get(i).getNickName())){
                holder.moreBtn.setVisibility(View.VISIBLE);
                holder.moreBtn.setEnabled(true);
            }
        }
    }

    @Override
    public int getItemCount() {
        return commentData.size();
    }
    
    public void refreshAdapter(){
        notifyDataSetChanged();
    }
}
