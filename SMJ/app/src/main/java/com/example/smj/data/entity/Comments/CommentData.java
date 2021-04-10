package com.example.smj.data.entity.Comments;

import com.example.smj.data.entity.Member.MemberData;

public class CommentData {
    private int id;
    private String content;
    private MemberData member;
    private String[] createdAt;

    public CommentData(int id, String content, MemberData member, String[] createdAt) {
        this.id = id;
        this.content = content;
        this.member = member;
        this.createdAt = createdAt;
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

    public MemberData getMember() {
        return member;
    }

    public void setMember(MemberData member) {
        this.member = member;
    }

    public String[] getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(String[] createdAt) {
        this.createdAt = createdAt;
    }
}
