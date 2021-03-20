package com.example.smj.domain.usecase;

import android.util.Log;

import com.example.smj.callback.RetrofitOnSuccess;
import com.example.smj.data.repository.JWTApi;
import com.example.smj.ui.main.MainActivity;

public class JWTUseCase implements RetrofitOnSuccess {
    private MainActivity mainActivity;
    private JWTApi jwtApi;
    private String jwt;
    public JWTUseCase(MainActivity mainActivity){
        this.mainActivity = mainActivity;
        jwtApi = new JWTApi();
    }
    public void sendAT(String at){
        jwtApi.retrieveLocals(at,this);
    }
    @Override
    public void onSuccess(Object object) {
        if(object != null){
            jwt = (String)object;
            mainActivity.clickSuccess(jwt);
        }
    }
}
