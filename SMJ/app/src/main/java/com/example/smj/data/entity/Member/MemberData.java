package com.example.smj.data.entity.Member;

import com.google.gson.annotations.SerializedName;

public class MemberData {

    @SerializedName("id")
    private int id;

    @SerializedName("email")
    private String email;

    @SerializedName("nickname")
    private String nickName;

    @SerializedName("image")
    private String image;

    @SerializedName("createAt")
    private String[] createAt;

    public MemberData(int id, String email, String nickName, String image, String[] createAt) {
        this.id = id;
        this.email = email;
        this.nickName = nickName;
        this.image = image;
        this.createAt = createAt;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getNickName() {
        return nickName;
    }

    public void setNickName(String nickName) {
        this.nickName = nickName;
    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }

    public String[] getCreateAt() {
        return createAt;
    }

    public void setCreateAt(String[] createAt) {
        this.createAt = createAt;
    }
}
