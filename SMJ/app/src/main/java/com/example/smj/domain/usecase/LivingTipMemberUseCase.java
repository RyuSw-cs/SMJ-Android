package com.example.smj.domain.usecase;

import android.content.Context;

import com.example.smj.callback.MemberOnSuccess;
import com.example.smj.data.entity.Comments.CommentsPostData;
import com.example.smj.data.entity.Member.MemberData;
import com.example.smj.data.repository.LivingTipMemberRepository;
import com.example.smj.data.repository.MemberRepository;
import com.example.smj.ui.Comments.LivingTip.LivingTipCommentActivity;

import java.util.List;

public class LivingTipMemberUseCase implements MemberOnSuccess {
    private LivingTipMemberRepository livingTipMemberRepository;
    private LivingTipCommentActivity livingTipCommentActivity;

    public LivingTipMemberUseCase(LivingTipCommentActivity livingTipCommentActivity){
        livingTipMemberRepository = new LivingTipMemberRepository();
        this.livingTipCommentActivity = livingTipCommentActivity;
    }

    public LivingTipMemberUseCase(){
        livingTipMemberRepository = new LivingTipMemberRepository();
    }

    //GET
    public void getData(String key){
        livingTipMemberRepository.retrieveData(key,this);
    }

    //PUT
    public void putData(CommentsPostData data, String key, int id, Context context){
        //memberRepository.updateData(data, key, id, context,this);
    }

    //DELETE
    public void deleteData(String key, int id, Context context){
        livingTipMemberRepository.deleteData(key, id, context);
    }

    @Override
    public void onDataSuccess(List<MemberData> body) {
        livingTipCommentActivity.onDataSuccess(body);
    }
}
