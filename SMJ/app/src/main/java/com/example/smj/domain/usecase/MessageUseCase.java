package com.example.smj.domain.usecase;

import android.content.Context;

import com.example.smj.callback.MessageOnSuccess;
import com.example.smj.data.entity.Message.MessageData;
import com.example.smj.data.entity.Message.MessagePostData;
import com.example.smj.data.repository.MessageRepository;
import com.example.smj.ui.message.MessageManagementActivity;

import java.util.List;

public class MessageUseCase implements MessageOnSuccess {
    private MessageRepository messageRepository;
    private MessageManagementActivity messageManagementActivity;

    public MessageUseCase(){
        messageRepository = new MessageRepository();
    }
    public MessageUseCase(MessageManagementActivity messageManagementActivity){
        this.messageManagementActivity = messageManagementActivity;
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
            //받아온 채팅 데이터가 존재
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
