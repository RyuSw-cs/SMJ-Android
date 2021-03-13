package com.example.smj.data.entity;

import com.google.gson.annotations.SerializedName;

public class LivingTipDTO {

    @SerializedName("category")
    private LivingTipCategory category;

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
}
