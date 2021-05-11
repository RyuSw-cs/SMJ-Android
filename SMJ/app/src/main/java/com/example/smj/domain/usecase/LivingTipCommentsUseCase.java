package com.example.smj.domain.usecase;

import android.content.Context;
import android.util.Log;

import com.example.smj.callback.RetrofitOnSuccess;
import com.example.smj.data.entity.Comments.CommentData;
import com.example.smj.data.entity.Comments.CommentsPostData;
import com.example.smj.data.repository.CommentsRepository;
import com.example.smj.data.repository.LivingTipCommentsRepository;
import com.example.smj.ui.Comments.LivingTip.LivingTipCommentActivity;
import com.example.smj.ui.Comments.Transaction.TransactionCommentActivity;

import java.util.ArrayList;
import java.util.List;

public class LivingTipCommentsUseCase implements RetrofitOnSuccess {
    private LivingTipCommentsRepository livingTipCommentsRepository;
    private LivingTipCommentActivity livingTipCommentActivity;
    private List<CommentData> list = new ArrayList<>();

    public LivingTipCommentsUseCase(LivingTipCommentActivity livingTipCommentActivity){
        livingTipCommentsRepository = new LivingTipCommentsRepository();
        this.livingTipCommentActivity = livingTipCommentActivity;
    }

    //GET
    public void getData(String key, int id){
        livingTipCommentsRepository.retrieveData(key, id, this);
    }

    //POST
    public void postData(CommentsPostData data, String key, int id, Context context){
        Log.d("댓글 post","post");
        livingTipCommentsRepository.postData(data, key, id, context,this);
    }

    //PUT
    public void putData(CommentsPostData data, String key, int id, Context context){
        livingTipCommentsRepository.updateData(data, key, id, context,this);
    }

    //DELETE
    public void deleteData(String key, int id, Context context){
        livingTipCommentsRepository.deleteData(key, id, context);
    }

    @Override
    public void onSuccess(Object object) {
        if(object != null){
            list = (List<CommentData>)object;
            livingTipCommentActivity.onSuccess(list);
        }
    }

    public void updateSuccess(){
        livingTipCommentActivity.dataChangeSuccess();
    }
}
