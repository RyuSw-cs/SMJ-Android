package com.example.smj.domain.usecase;

import android.content.Context;

import com.example.smj.data.entity.Comments.CommentData;
import com.example.smj.data.entity.Comments.CommentsPostData;
import com.example.smj.data.entity.Member.MemberData;
import com.example.smj.data.repository.CommentsRepository;
import com.example.smj.data.repository.MemberRepository;
import com.example.smj.ui.Comments.Transaction.TransactionCommentActivity;

import java.util.List;


public class MemberUseCase {
    private MemberRepository memberRepository;
    private TransactionCommentActivity transactionCommentActivity;

    public MemberUseCase(TransactionCommentActivity transactionCommentActivity){
        memberRepository = new MemberRepository();
        this.transactionCommentActivity = transactionCommentActivity;
    }

    public MemberUseCase(){
        memberRepository = new MemberRepository();
    }

    //GET
    public void getData(String key){
        memberRepository.retrieveData(key,this);
    }

    //PUT
    public void putData(CommentsPostData data, String key, int id, Context context){
        //memberRepository.updateData(data, key, id, context,this);
    }

    //DELETE
    public void deleteData(String key, int id, Context context){
        memberRepository.deleteData(key, id, context);
    }

    public void onSuccess(List<MemberData> body) {
        transactionCommentActivity.onDataSuccess(body);
    }
}
