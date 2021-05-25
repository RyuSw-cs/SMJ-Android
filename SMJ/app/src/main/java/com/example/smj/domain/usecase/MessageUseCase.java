package com.example.smj.domain.usecase;

import android.content.Context;

import com.example.smj.callback.MessageOnSuccess;
import com.example.smj.data.entity.Message.MessageData;
import com.example.smj.data.entity.Message.MessageManageData;
import com.example.smj.data.entity.Message.MessagePostData;
import com.example.smj.data.repository.MessageRepository;
import com.example.smj.ui.message.MessageManagementActivity;

import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.List;

public class MessageUseCase implements MessageOnSuccess {
    private MessageRepository messageRepository;
    private MessageManagementActivity messageManagementActivity;
    private List<MessageManageData> messageManageData = new ArrayList<>();

    public MessageUseCase(){
        messageRepository = new MessageRepository();
    }
    public MessageUseCase(MessageManagementActivity messageManagementActivity){
        this.messageManagementActivity = messageManagementActivity;
        messageRepository = new MessageRepository();
    }

    //GET
    public void getData(String key){
        messageRepository.retrieveData(key, this);
    }

    //POST
    public void putData(MessagePostData data, String key){
        messageRepository.postData(data, key, this);
    }

    //DELETE
    public void deleteData(String key, int id, Context context){
        messageRepository.deleteData(key, id, this);
    }

    @Override
    public void messageRetrieveSuccess(List<MessageData>messageData) {
        if(messageData != null){
            int getListSize = messageData.size();
            for(int i = 0; i<getListSize; i++){
                //표시할 데이터 전처리
                messageManageData.add(new MessageManageData(
                        messageData.get(i).getSender(),
                        messageData.get(i).getCreateAt(),
                        messageData.get(i).getContent(),
                        messageData.get(i).isCheck()));
            }
            messageManagementActivity.onSuccess(messageManageData);
        }
    }

    @Override
    public void messagePostSuccess() {
        //메시지 전송 성공
    }

    @Override
    public void messageDeleteSuccess() {
        //메시지 삭제 성공
    }
}
