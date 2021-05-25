package com.example.smj.ui.Comments.LivingTip;

import java.io.Serializable;

public class LivingTipCommentData implements Serializable {
    private String[] date;
    private String commenter;
    private String contents;
    private String email;
    private int commentId;

    public LivingTipCommentData(String[] date, String commenter, String contents, String email, int commentId) {
        this.date = date;
        this.commenter = commenter;
        this.contents = contents;
        this.email = email;
        this.commentId = commentId;
    }

    public String[] getDate() {
        return date;
    }

    public void setDate(String[] date) {
        this.date = date;
    }

    public String getCommenter() {
        return commenter;
    }

    public void setCommenter(String commenter) {
        this.commenter = commenter;
    }

    public String getContents() {
        return contents;
    }

    public void setContents(String contents) {
        this.contents = contents;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public int getCommentId() {
        return commentId;
    }

    public void setCommentId(int commentId) {
        this.commentId = commentId;
    }
}
