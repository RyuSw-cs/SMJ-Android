package com.example.smj.ui.transaction;

public class TransactionPostData {
    private String category;
    private String title;
    private String contents;
    private String writer;
    private String date;
    private String profileImage;

    public TransactionPostData(String category, String title, String contents, String writer, String date, String profileImage) {
        this.category = category;
        this.title = title;
        this.contents = contents;
        this.writer = writer;
        this.date = date;
        this.profileImage = profileImage;
    }

    public String getCategory() {
        return category;
    }

    public void setCategory(String category) {
        this.category = category;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getContents() {
        return contents;
    }

    public void setContents(String contents) {
        this.contents = contents;
    }

    public String getWriter() {
        return writer;
    }

    public void setWriter(String writer) {
        this.writer = writer;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public String getProfileImage() {
        return profileImage;
    }

    public void setProfileImage(String profileImage) {
        this.profileImage = profileImage;
    }
}
