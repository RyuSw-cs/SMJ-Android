package com.example.smj.ui.Boards.Transaction;

import java.io.Serializable;

public class TransactionPostData implements Serializable {
    private String category;
    private String title;
    private String contents;
    private String memberEmail;
    private String writer;
    private String[] date;
    private String profileImage;
    private String imageOne;
    private String imageTwo;
    private String imageThree;
    private int id;

    public TransactionPostData(String category, String title, String contents, String memberEmail, String writer, String[] date, String profileImage, String imageOne, String imageTwo, String imageThree, int id) {
        this.category = category;
        this.title = title;
        this.contents = contents;
        this.memberEmail = memberEmail;
        this.writer = writer;
        this.date = date;
        this.profileImage = profileImage;
        this.imageOne = imageOne;
        this.imageTwo = imageTwo;
        this.imageThree = imageThree;
        this.id = id;
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

    public String getMemberEmail() {
        return memberEmail;
    }

    public void setMemberEmail(String memberEmail) {
        this.memberEmail = memberEmail;
    }

    public String getWriter() {
        return writer;
    }

    public void setWriter(String writer) {
        this.writer = writer;
    }

    public String[] getDate() {
        return date;
    }

    public void setDate(String[] date) {
        this.date = date;
    }

    public String getProfileImage() {
        return profileImage;
    }

    public void setProfileImage(String profileImage) {
        this.profileImage = profileImage;
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

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }
}
