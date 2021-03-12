package com.example.smj.domain.usecase;

import android.util.Log;

import com.example.smj.callback.RetrofitOnSuccess;
import com.example.smj.data.entity.Document;
import com.example.smj.data.repository.ConvenienceMarkerApi;
import com.example.smj.data.repository.JWTApi;
import com.example.smj.ui.main.MainActivity;
import com.example.smj.ui.main.fragment.Convenience.ConvenienceFragment;

import java.util.ArrayList;

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
