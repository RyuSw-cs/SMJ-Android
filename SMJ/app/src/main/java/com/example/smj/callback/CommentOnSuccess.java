package com.example.smj.callback;

import com.example.smj.data.entity.Comments.CommentData;

import java.util.List;

public interface CommentOnSuccess {
    void onSuccess(List<CommentData> list);
    void dataChangeSuccess();
}
