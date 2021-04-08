package com.example.smj.data.entity.board;

import com.example.smj.data.entity.Member.MemberData;
import com.google.gson.annotations.SerializedName;

import java.io.Serializable;
import java.lang.reflect.Member;

public class boardData implements Serializable {

    @SerializedName("id")
    private int id;

    @SerializedName("member")
    private MemberData member;

    @SerializedName("category")
    private boardCategory category;

    @SerializedName("type")
    private String type;

    @SerializedName("title")
    private String title;

    @SerializedName("content")
    private String content;

    @SerializedName("imageOne")
    private String imageOne;

    @SerializedName("imageTwo")
    private String imageTwo;

    @SerializedName("imageThree")
    private String imageThree;

    @SerializedName("createdAt")
    private String[] createdAt;

    public boardData(int id, MemberData member, boardCategory category, String type, String title, String content, String imageOne, String imageTwo, String imageThree, String[] createdAt) {
        this.id = id;
        this.member = member;
        this.category = category;
        this.type = type;
        this.title = title;
        this.content = content;
        this.imageOne = imageOne;
        this.imageTwo = imageTwo;
        this.imageThree = imageThree;
        this.createdAt = createdAt;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public MemberData getMember() {
        return member;
    }

    public void setMember(MemberData member) {
        this.member = member;
    }

    public boardCategory getCategory() {
        return category;
    }

    public void setCategory(boardCategory category) {
        this.category = category;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public String getImageOne() {
        return imageOne;
    }

    public void setImageOne(String imageOne) {
        this.imageOne = imageOne;
    }

    public String getImageTwo() {
        return imageTwo;
    }

    public void setImageTwo(String imageTwo) {
        this.imageTwo = imageTwo;
    }

    public String getImageThree() {
        return imageThree;
    }

    public void setImageThree(String imageThree) {
        this.imageThree = imageThree;
    }

    public String[] getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(String[] createdAt) {
        this.createdAt = createdAt;
    }
}

