package com.example.smj.ui.Comments.Transaction;

public class TransactionCommentData {
    private String[] date;
    private String commenter;
    private String contents;
    private int commentId;

    public TransactionCommentData(String[] date, String commenter, String contents, int commentId) {
        this.date = date;
        this.commenter = commenter;
        this.contents = contents;
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

    public int getCommentId() {
        return commentId;
    }

    public void setCommentId(int commentId) {
        this.commentId = commentId;
    }
}
