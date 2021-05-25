package com.example.smj.ui.Boards.Transaction;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.smj.Manager.JWTManager;
import com.example.smj.R;
import com.example.smj.callback.TransactionGetData;
import com.example.smj.data.entity.board.boardData;
import com.example.smj.domain.usecase.TransactionUseCase;
import com.example.smj.ui.Boards.Transaction.Adapter.TransactionPostAdapter;

import java.util.ArrayList;
import java.util.List;

public class TransactionFragment extends Fragment implements TransactionGetData {
    private List<TransactionPostData> data = new ArrayList<>();
    private List<TransactionPostData> searchList = new ArrayList<>();
    private RecyclerView recyclerView;
    private TransactionPostAdapter adapter;
    private TransactionUseCase transactionUseCase;
    private Button writeButton;
    private String token;
    private EditText search;
    private Uri mainImage;

    public TransactionFragment(){

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle saveInstanceState){
        transactionUseCase = new TransactionUseCase(this);

        View view = inflater.inflate(R.layout.activity_transaction_main,container,false);

        recyclerView = (RecyclerView) view.findViewById(R.id.transaction_post_list);
        search = view.findViewById(R.id.trade_search_text);
        recyclerView.setHasFixedSize(true);

        search.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {
                //입력하기 전
            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                // 입력 시 변화가 있을 때
                // 서버에서 받아온 값을 리스트에 이미 저장됨 -> 리사이클러뷰만 다시 설정
                // 검색 된 키워드를 data에서 찾아옴 -> list를 하나 더 생성함
                searchList.clear();
                int getListSize = data.size();
                for(int i = 0; i<getListSize; i++) {
                    if(data.get(i).getTitle().contains(s)){
                        searchList.add(data.get(i));
                    }
                }
                adapter = new TransactionPostAdapter(getActivity(), searchList, transactionUseCase);
                recyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));
                recyclerView.setAdapter(adapter);
            }

            @Override
            public void afterTextChanged(Editable s) {
                //입력종료
            }
        });

        token =  JWTManager.getSharedPreference(getContext(),getString(R.string.saved_JWT));
        //데이터 받아오기
        transactionUseCase.getData(token);
        writeButton = (Button) view.findViewById(R.id.write_btn);
        writeButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getActivity(), TransactionCreateActivity.class);
                startActivity(intent);
            }
        });
        return view;
    }

    @Override
    public void onSuccess(List<boardData> getList) {
        data.clear();
        //list를 받았을때 값을 add, 리사이클러뷰에 뿌림
        int getListSize = getList.size();
        for(int i = 0; i<getListSize; i++) {
            //유즈케이스에서 할것
            if(getList.get(i).getType().equals("TRADE")) {
                data.add(new TransactionPostData(
                        getList.get(i).getCategory().getName(),
                        getList.get(i).getTitle(),
                        getList.get(i).getContent(),
                        getList.get(i).getMember().getEmail(),
                        getList.get(i).getMember().getNickName(),
                        getList.get(i).getCreatedAt(),
                        getList.get(i).getMember().getImage(),
                        getList.get(i).getImageOne(),
                        getList.get(i).getImageTwo(),
                        getList.get(i).getImageThree(),
                        getList.get(i).getId()));
            }
        }
        adapter = new TransactionPostAdapter(getActivity(), data, transactionUseCase);
        recyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));
        recyclerView.setAdapter(adapter);
        adapter.adapterRefresh();
    }
    @Override
    public void onResume(){
        super.onResume();
        transactionUseCase.getData(token);
    }
}
