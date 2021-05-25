package com.example.smj.data.repository;

import android.content.Context;
import android.util.Log;
import android.widget.Toast;

import com.example.smj.Manager.NetworkManager;
import com.example.smj.data.entity.Member.Entity_Member;
import com.example.smj.data.entity.Member.MemberData;
import com.example.smj.data.entity.Member.MemberPostData;
import com.example.smj.data.entity.Message.Entity_Message;
import com.example.smj.data.entity.Message.MessageData;
import com.example.smj.data.entity.Message.MessagePostData;
import com.example.smj.domain.usecase.MemberUseCase;
import com.example.smj.domain.usecase.MessageUseCase;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class MessageRepository {
    private Entity_Message entityMessage;

    public MessageRepository() {
        this.entityMessage = NetworkManager.getInstance().getRetrofit().create(Entity_Message.class);;
    }

    public void retrieveData(String key, MessageUseCase messageUseCase){
        Call <List<MessageData>> call = entityMessage.getData(key);
        call.enqueue(new Callback<List<MessageData>>() {
            @Override
            public void onResponse(Call<List<MessageData>> call, Response<List<MessageData>> response) {
                if(response.isSuccessful()){
                    Log.d("메시지 데이터 받아오기 성공","성공");
                    messageUseCase.messageRetrieveSuccess(response.body());
                }
                else{
                    Log.d("메시지 데이터 받아오기 실패","실패");
                }
            }

            @Override
            public void onFailure(Call<List<MessageData>> call, Throwable t) {
                Log.d("메시지 데이터 받아오기 실패","실패");
            }
        });
    }

    public void deleteData(String key, int id, MessageUseCase messageUseCase){
        Call<Void> call = entityMessage.deleteData(key,id);
        call.enqueue(new Callback<Void>() {
            @Override
            public void onResponse(Call<Void> call, Response<Void> response) {
                if(response.isSuccessful()){
                    Log.d("메시지 데이터 삭제 성공","성공");
                    messageUseCase.messageDeleteSuccess();
                }
                else{
                    Log.d("메시지 데이터 삭제 실패","실패");
                }
            }

            @Override
            public void onFailure(Call<Void> call, Throwable t) {
                Log.d("메시지 데이터 삭제 실패","실패");
            }
        });
    }

    public void postData(MessagePostData data, String key, MessageUseCase messageUseCase){
        Call<MessageData>call = entityMessage.postData(key, data);
        call.enqueue(new Callback<MessageData>() {
            @Override
            public void onResponse(Call<MessageData> call, Response<MessageData> response) {
                if(response.isSuccessful()){
                    Log.d("메시지 데이터 보내기 성공","성공");
                    messageUseCase.messagePostSuccess();
                }
                else{
                    Log.d("메시지 데이터 보내기 실패","실패");
                }
            }

            @Override
            public void onFailure(Call<MessageData> call, Throwable t) {
                Log.d("메시지 데이터 보내기 실패","실패");
            }
        });
    }
}
