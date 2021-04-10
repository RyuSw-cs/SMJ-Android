package com.example.smj.data.entity.Member;

public class MemberPostData {
    private String nickname;
    private String image;

    public MemberPostData(String nickname, String image) {
        this.nickname = nickname;
        this.image = image;
    }

    public String getNickname() {
        return nickname;
    }

    public void setNickname(String nickname) {
        this.nickname = nickname;
    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }
}
