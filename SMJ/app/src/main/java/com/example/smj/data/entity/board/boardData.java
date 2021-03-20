package com.example.smj.data.entity.board;

import com.google.gson.annotations.SerializedName;

public class boardData {

    @SerializedName("category")
    private boardCategory category;

    @SerializedName("content")
    private String content;

    @SerializedName("createAt")
    private String createAt;

    @SerializedName("id")
    private int id;

    @SerializedName("title")
    private String title;

    @SerializedName("type")
    private String type;

    public boardData(boardCategory category, String content, String createAt, int id, String title, String type) {
        this.category = category;
        this.content = content;
        this.createAt = createAt;
        this.id = id;
        this.title = title;
        this.type = type;
    }

    public boardCategory getCategory() {
        return category;
    }

    public void setCategory(boardCategory category) {
        this.category = category;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public String getCreateAt() {
        return createAt;
    }

    public void setCreateAt(String createAt) {
        this.createAt = createAt;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }
}

