package com.example.smj.callback;

import androidx.annotation.NonNull;

import com.example.smj.data.entity.Document;

import java.util.ArrayList;

public interface ConvenienceGetLocal {
    void onRequestPermissionResult(int permsRequestCode, @NonNull String[] permission, @NonNull int[] grandResults);

    void clickSuccess(ArrayList<Document> list);
}
