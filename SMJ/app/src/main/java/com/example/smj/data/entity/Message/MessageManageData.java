package com.example.smj.data.entity.Message;

public class MessageManageData {
    private String nickName;
    private String[] createAt;
    private String content;
    private boolean check;

    public MessageManageData(String nickName, String[] createAt, String content, boolean check) {
        this.nickName = nickName;
        this.createAt = createAt;
        this.content = content;
        this.check = check;
    }

    public String getNickName() {
        return nickName;
    }

    public void setNickName(String nickName) {
        this.nickName = nickName;
    }

    public String[] getCreateAt() {
        return createAt;
    }

    public void setCreateAt(String[] createAt) {
        this.createAt = createAt;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public boolean isCheck() {
        return check;
    }

    public void setCheck(boolean check) {
        this.check = check;
    }
}
