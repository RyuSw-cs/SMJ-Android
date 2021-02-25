package com.example.smj.ui.main.fragment;

import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import androidx.fragment.app.Fragment;

import com.example.smj.R;
import com.example.smj.ui.main.fragment.mypage.AppSetting;
import com.example.smj.ui.manage.PostManageActivity;
import com.example.smj.ui.message.MessageActivity;
import com.example.smj.ui.message.MessageManagementActivity;

public class MyPageFragment extends Fragment {

    Button letterbtn;
    Button settingappbtn;
    Button memberoutbtn;
    Button logoutbtn;
    Button mypostbtn;

    public MyPageFragment(){
    }
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle saveInstanceState){
        View view = inflater.inflate(R.layout.activity_mypage,container,false);
        letterbtn = view.findViewById(R.id.myLetter);
        letterbtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(view.getContext(), MessageManagementActivity.class);//쪽지리스트액티비티로 연결
                startActivityForResult(intent,1);
            }
        });

        settingappbtn = view.findViewById(R.id.settingApp);
        settingappbtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(view.getContext(), AppSetting.class); //앱관리액티비티로 연결
                startActivityForResult(intent,2);
            }
        });

        memberoutbtn = view.findViewById(R.id.memberOut);
        memberoutbtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //회원탈퇴 관련
            }
        });

        logoutbtn = view.findViewById(R.id.logout);
        logoutbtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //로그아웃 관련
            }
        });

        mypostbtn = view.findViewById(R.id.myPost);
        mypostbtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //내 게시글 관련
                
                Intent intent = new Intent(view.getContext(), PostManageActivity.class); //게시글관리액티비티로 연결
                startActivityForResult(intent,3);
            }
        });

        return view;
    }
}
