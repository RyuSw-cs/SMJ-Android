package com.example.smj.data.entity.Message;

public class MessageData {
    private int id;
    private String content;
    private String sender;
    private String receiver;
    private String[] createAt;
    private boolean check;

    public MessageData(int id, String content, String sender, String receiver, String[] createAt, boolean check) {
        this.id = id;
        this.content = content;
        this.sender = sender;
        this.receiver = receiver;
        this.createAt = createAt;
        this.check = check;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public String getSender() {
        return sender;
    }

    public void setSender(String sender) {
        this.sender = sender;
    }

    public String getReceiver() {
        return receiver;
    }

    public void setReceiver(String receiver) {
        this.receiver = receiver;
    }

    public String[] getCreateAt() {
        return createAt;
    }

    public void setCreateAt(String[] createAt) {
        this.createAt = createAt;
    }

    public boolean isCheck() {
        return check;
    }

    public void setCheck(boolean check) {
        this.check = check;
    }
}
