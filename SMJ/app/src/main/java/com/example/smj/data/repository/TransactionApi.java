package com.example.smj.data.repository;

import android.content.Context;
import android.util.Log;
import android.widget.Toast;

import com.example.smj.data.datasource.SMJRemoteDataSource;
import com.example.smj.data.entity.board.Entity_board;
import com.example.smj.data.entity.board.boardData;
import com.example.smj.domain.usecase.LivingTipUseCase;
import com.example.smj.domain.usecase.TransactionUseCase;
import com.example.smj.ui.main.fragment.LivingTipFragment;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class TransactionApi {
    private Entity_board entityBoard;

    public void getData(String key, TransactionUseCase transactionUseCase){
        entityBoard = SMJRemoteDataSource.getInstance().create(Entity_board.class);
        Call <List<boardData>> call = entityBoard.getLivingTIp(key);
        call.enqueue(new Callback<List<boardData>>() {
            @Override
            public void onResponse(Call<List<boardData>> call, Response<List<boardData>> response) {
                if(response.isSuccessful()){
                    Log.d("살림 팁 게시판 데이터 GET 성공","살림 팁 게시판 데이터 GET 성공");
                    transactionUseCase.onSuccess(response.body());
                }
            }

            @Override
            public void onFailure(Call<List<boardData>> call, Throwable t) {
                Log.d("살림 팁 게시판 데이터 GET 실패","살림 팁 게시판 데이터 GET 실패");
            }
        });
    }

    public void deleteData(String key, String id, Context context){
        entityBoard = SMJRemoteDataSource.getInstance().create(Entity_board.class);
        Call<Void> call = entityBoard.deleteLivingTip(key,id);
        call.enqueue(new Callback<Void>() {
            @Override
            public void onResponse(Call<Void> call, Response<Void> response) {
                Log.d("데이터 전송 성공","성공");
                Toast.makeText(context,"게시글이 삭제됐습니다.",Toast.LENGTH_LONG).show();
            }

            @Override
            public void onFailure(Call<Void> call, Throwable t) {
                Log.d("데이터 전송 실패","실패");
            }
        });
    }

    public void putData(boardData data, String key, String id){
        entityBoard = SMJRemoteDataSource.getInstance().create(Entity_board.class);
        Call<boardData>call = entityBoard.putLivingTip(key, data, id);
        call.enqueue(new Callback<boardData>() {
            @Override
            public void onResponse(Call<boardData> call, Response<boardData> response) {
                Log.d("데이터 전송 성공","성공");
            }

            @Override
            public void onFailure(Call<boardData> call, Throwable t) {
                Log.d("데이터 전송 실패","실패");
            }
        });
    }

    public void postData(boardData data, String key){
        entityBoard = SMJRemoteDataSource.getInstance().create(Entity_board.class);
        Call<boardData>call = entityBoard.postLivingTip(key, data);
        call.enqueue(new Callback<boardData>() {
            @Override
            public void onResponse(Call<boardData> call, Response<boardData> response) {
                Log.d("데이터 전송 성공","성공");
            }

            @Override
            public void onFailure(Call<boardData> call, Throwable t) {
                Log.d("데이터 전송 실패","실패");
            }
        });
    }
}
