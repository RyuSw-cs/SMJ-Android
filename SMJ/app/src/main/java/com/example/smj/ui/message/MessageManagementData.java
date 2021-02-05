package com.example.smj.ui.message;

public class MessageManagementData {
    String image;
    String name;
    String message;
    String ago;
    int check;

    public MessageManagementData(String image, String name, String message, String ago, int check) {
        this.image = image;
        this.name = name;
        this.message = message;
        this.ago = ago;
        this.check = check;
    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public String getAgo() {
        return ago;
    }

    public void setAgo(String ago) {
        this.ago = ago;
    }

    public int getCheck() {
        return check;
    }

    public void setCheck(int check) {
        this.check = check;
    }
}
