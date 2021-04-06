package com.example.smj.domain.usecase;

import com.example.smj.callback.RetrofitOnSuccess;
import com.example.smj.data.repository.JwtRepository;
import com.example.smj.ui.main.MainActivity;

public class JWTUseCase implements RetrofitOnSuccess {
    private MainActivity mainActivity;
    private JwtRepository jwtRepository;
    private String jwt;
    public JWTUseCase(MainActivity mainActivity){
        this.mainActivity = mainActivity;
        jwtRepository = new JwtRepository();
    }
    public void sendAT(String at){
        jwtRepository.retrieveLocals(at,this);
    }
    @Override
    public void onSuccess(Object object) {
        if(object != null){
            jwt = (String)object;
            mainActivity.clickSuccess(jwt);
        }
    }

}
