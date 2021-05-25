package com.example.smj.data.entity.Message;

public class MessagePostData {
    private String content;
    private String receiver;

    public MessagePostData(String content, String receiver) {
        this.content = content;
        this.receiver = receiver;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public String getReceiver() {
        return receiver;
    }

    public void setReceiver(String receiver) {
        this.receiver = receiver;
    }
}
