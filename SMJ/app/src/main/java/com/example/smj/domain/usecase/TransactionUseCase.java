package com.example.smj.domain.usecase;

import android.content.Context;

import com.example.smj.callback.ModifyOnSuccess;
import com.example.smj.callback.MyBoardGetData;
import com.example.smj.callback.RetrofitOnSuccess;
import com.example.smj.data.entity.board.boardData;
import com.example.smj.data.entity.board.boardPostData;
import com.example.smj.data.repository.TransactionRepository;
import com.example.smj.ui.Boards.Transaction.TransactionFragment;
import com.example.smj.ui.Boards.Transaction.TransactionModifyActivity;
import com.example.smj.ui.Boards.Transaction.TransactionReadingActivity;

import java.util.ArrayList;
import java.util.List;

public class TransactionUseCase implements RetrofitOnSuccess, MyBoardGetData, ModifyOnSuccess {

    private TransactionRepository transactionRepository;
    private TransactionFragment transactionFragment;
    private TransactionReadingActivity transactionReadingActivity;
    private TransactionModifyActivity transactionModifyActivity;
    private List<boardData> list = new ArrayList<>();
    private ArrayList<Integer> idList = new ArrayList<>();

    public TransactionUseCase(TransactionFragment transactionFragment){
        transactionRepository = new TransactionRepository();
        this.transactionFragment = transactionFragment;
    }
    public TransactionUseCase(){
        transactionRepository = new TransactionRepository();
    }

    public TransactionUseCase(TransactionReadingActivity transactionReadingActivity){
        transactionRepository = new TransactionRepository();
        this.transactionReadingActivity = transactionReadingActivity;
    }

    public TransactionUseCase(TransactionModifyActivity transactionModifyActivity){
        transactionRepository = new TransactionRepository();
        this.transactionModifyActivity = transactionModifyActivity;
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
        transactionRepository.putData(data, key, id, context,this);
    }

    //DELETE
    public void deleteData(String key, int id, Context context){
        transactionRepository.deleteData(key, id, context);
    }

    //MY_DATA
    public void getMyData(String key){
        transactionRepository.getMyData(key, this);
    }

    @Override
    public void onSuccess(Object object) {
        if(object != null){
            list = (List<boardData>)object;
            transactionFragment.onSuccess(list);
        }
    }

    @Override
    public void onSuccessData(Object object) {
        if(object != null){
            //내 게시글 가져옴 -> 전처리 해야함. -> id값만 필요함
            list.clear();
            list = (List<boardData>)object;
            idList.clear();
            //idList에 id값 추가함
            int getListSize = list.size();
            for(int i = 0; i<getListSize; i++){
                idList.add(list.get(i).getId());
            }
            transactionReadingActivity.onSuccessMyData(idList);
        }
    }

    @Override
    public void onSuccess() {
        //수정완료 -> 액티비티 종료
        transactionModifyActivity.modifySuccess();
    }
}
