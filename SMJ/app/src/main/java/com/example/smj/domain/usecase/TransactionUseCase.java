package com.example.smj.domain.usecase;

import android.content.Context;

import com.example.smj.callback.RetrofitOnSuccess;
import com.example.smj.data.entity.board.boardData;
import com.example.smj.data.entity.board.boardPostData;
import com.example.smj.data.repository.TransactionRepository;
import com.example.smj.ui.Boards.Transaction.TransactionFragment;

import java.util.ArrayList;
import java.util.List;

public class TransactionUseCase implements RetrofitOnSuccess {

    private TransactionRepository transactionRepository;
    private TransactionFragment transactionFragment;
    private List<boardData> list = new ArrayList<>();

    public TransactionUseCase(TransactionFragment transactionFragment){
        transactionRepository = new TransactionRepository();
        this.transactionFragment = transactionFragment;
    }
    public TransactionUseCase(){
        transactionRepository = new TransactionRepository();
    }

    //GET
    public void getData(String key){
        transactionRepository.getData(key, this);
    }

    //POST
    public void postData(boardPostData data, String key, Context context){
        transactionRepository.postData(data, key, context,this);
    }

    //PUT
    public void putData(boardPostData data, String key, int id, Context context){
        transactionRepository.putData(data, key, id, context);
    }

    //DELETE
    public void deleteData(String key, int id, Context context){
        transactionRepository.deleteData(key, id, context);
    }

    @Override
    public void onSuccess(Object object) {
        if(object != null){
            list = (List<boardData>)object;
            transactionFragment.onSuccess(list);
        }
    }
}
