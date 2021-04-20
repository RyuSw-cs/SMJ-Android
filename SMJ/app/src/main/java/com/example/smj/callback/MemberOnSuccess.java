package com.example.smj.callback;

import com.example.smj.data.entity.Member.MemberData;

import java.util.List;

public interface MemberOnSuccess {
    void onDataSuccess(List<MemberData> body);
}
