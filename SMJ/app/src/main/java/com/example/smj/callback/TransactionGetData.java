package com.example.smj.callback;

import com.example.smj.data.entity.board.boardCategory;
import com.example.smj.data.entity.board.boardData;

import java.util.List;

public interface TransactionGetData {
    void onSuccess(List<boardData>list);
}
