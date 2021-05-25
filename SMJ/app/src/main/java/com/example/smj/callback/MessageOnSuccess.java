package com.example.smj.callback;

import com.example.smj.data.entity.Message.MessageData;

import java.util.List;

public interface MessageOnSuccess {
    void messageRetrieveSuccess(List<MessageData>messageData);
    void messagePostSuccess();
    void messageDeleteSuccess();
}
