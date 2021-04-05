package com.example.smj.ui.Comments.LivingTip.Transaction;

public class TransactionCommentData {
    String date;
    String commenter;
    String contents;

    public TransactionCommentData(String date, String commenter, String contents) {
        this.date = date;
        this.commenter = commenter;
        this.contents = contents;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
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
}
