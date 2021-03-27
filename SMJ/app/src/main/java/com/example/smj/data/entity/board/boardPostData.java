package com.example.smj.data.entity.board;

import com.google.gson.annotations.SerializedName;

public class boardPostData {
    @SerializedName("categoryId")
    private int categoryId;

    @SerializedName("type")
    private String type;

    @SerializedName("title")
    private String title;

    @SerializedName("content")
    private String content;

    public boardPostData(int categoryId, String type, String title, String content) {
        this.categoryId = categoryId;
        this.type = type;
        this.title = title;
        this.content = content;
    }

    public int getCategoryId() {
        return categoryId;
    }

    public void setCategoryId(int categoryId) {
        this.categoryId = categoryId;
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
}
