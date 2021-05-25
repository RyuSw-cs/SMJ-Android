package com.example.smj.domain.usecase;

import android.content.Context;

import com.example.smj.callback.MemberOnSuccess;
import com.example.smj.data.entity.Comments.CommentsPostData;
import com.example.smj.data.entity.Member.MemberData;
import com.example.smj.data.repository.LivingTipMemberRepository;
import com.example.smj.data.repository.MyPageMemberRepository;
import com.example.smj.ui.Comments.LivingTip.LivingTipCommentActivity;
import com.example.smj.ui.Member.MyPageFragment;

import java.util.List;

import retrofit2.Retrofit;

public class MyPageMemberUseCase implements MemberOnSuccess {
    private MyPageMemberRepository myPageMemberRepository;
    private MyPageFragment myPageFragment;

    public MyPageMemberUseCase(MyPageFragment myPageFragment){
        myPageMemberRepository = new MyPageMemberRepository();
        this.myPageFragment = myPageFragment;
    }

    //GET
    public void getData(String key){
        myPageMemberRepository.retrieveData(key,this);
    }

    //PUT
    public void putData(CommentsPostData data, String key, int id, Context context){
        //myPageMemberRepository.updateData(data, key, id, context,this);
    }

    //DELETE
    public void deleteData(String key, int id, Context context){
        myPageMemberRepository.deleteData(key, id, context);
    }

    @Override
    public void onDataSuccess(List<MemberData> body) {
        myPageFragment.onDataSuccess(body);
    }
}

