package com.example.smj.domain.usecase;

import android.content.Context;

import com.example.smj.callback.BoardOnSuccess;
import com.example.smj.callback.RetrofitOnSuccess;
import com.example.smj.data.entity.Comments.CommentData;
import com.example.smj.data.entity.Comments.CommentsPostData;
import com.example.smj.data.entity.board.boardData;
import com.example.smj.data.entity.board.boardPostData;
import com.example.smj.data.repository.CommentsRepository;
import com.example.smj.ui.Comments.Transaction.TransactionCommentActivity;

import java.util.ArrayList;
import java.util.List;

public class CommentsUseCase implements RetrofitOnSuccess{

    private CommentsRepository commentsRepository;
    private TransactionCommentActivity transactionCommentActivity;
    private List<CommentData>list = new ArrayList<>();

    public CommentsUseCase(TransactionCommentActivity transactionCommentActivity){
        commentsRepository = new CommentsRepository();
        this.transactionCommentActivity = transactionCommentActivity;
    }

    //GET
    public void getData(String key, int id){
        commentsRepository.retrieveData(key, id, this);
    }

    //POST
    public void postData(CommentsPostData data, String key, int id, Context context){
        commentsRepository.postData(data, key, id, context,this);
    }

    //PUT
    public void putData(CommentsPostData data, String key, int id, Context context){
        commentsRepository.updateData(data, key, id, context,this);
    }

    //DELETE
    public void deleteData(String key, int id, Context context){
        commentsRepository.deleteData(key, id, context);
    }

    @Override
    public void onSuccess(Object object) {
        if(object != null){
            list.clear();
            list = (List<CommentData>)object;
            transactionCommentActivity.onSuccess(list);
        }
    }
    public void updateSuccess(){
        //transactionCommentActivity.onSuccess();
    }
}
