package com.example.smj.data.entity.board;

import com.google.gson.annotations.SerializedName;

import java.io.Serializable;

public class boardData implements Serializable {

    @SerializedName("id")
    private int id;

    @SerializedName("writer")
    private String writer;

    @SerializedName("type")
    private String type;

    @SerializedName("title")
    private String title;

    @SerializedName("content")
    private String content;

    @SerializedName("category")
    private boardCategory category;

    @SerializedName("createdAt")
    private String[] createdAt;

    public boardData(int id, String writer, String type, String title, String content, boardCategory category, String[] createdAt) {
        this.id = id;
        this.writer = writer;
        this.type = type;
        this.title = title;
        this.content = content;
        this.category = category;
        this.createdAt = createdAt;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getWriter() {
        return writer;
    }

    public void setWriter(String writer) {
        this.writer = writer;
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

    public boardCategory getCategory() {
        return category;
    }

    public void setCategory(boardCategory category) {
        this.category = category;
    }

    public String[] getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(String[] createdAt) {
        this.createdAt = createdAt;
    }
}

