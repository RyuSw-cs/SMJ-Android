package com.example.smj.ui.Boards.LivingTip;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.smj.Manager.JWTManager;
import com.example.smj.R;
import com.example.smj.data.entity.board.boardData;
import com.example.smj.domain.usecase.LivingTipUseCase;
import com.example.smj.ui.Boards.LivingTip.LivingTipCreateActivity;
import com.example.smj.ui.Boards.LivingTip.LivingTipPostAdapter;
import com.example.smj.ui.Boards.LivingTip.LivingTipPostData;

import java.util.ArrayList;
import java.util.List;

//홈 버튼
public class LivingTipFragment extends Fragment {

    ArrayList<LivingTipPostData> data = new ArrayList<>();
    RecyclerView recyclerView;
    LivingTipPostAdapter adapter;
    Button writeBtn;
    LivingTipUseCase livingTipUseCase = new LivingTipUseCase(this);
    String key;

    public LivingTipFragment(){
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle saveInstanceState){
        View view = inflater.inflate(R.layout.activity_living_tip_main,container,false);
        LivingTipUseCase livingTipUseCase = new LivingTipUseCase(this);
        recyclerView = (RecyclerView) view.findViewById(R.id.living_tip_post_list);
        writeBtn = view.findViewById(R.id.write_btn);
        key = JWTManager.getSharedPreference(getActivity(),getString(R.string.saved_JWT));

        livingTipUseCase.getData(key);

        Log.d("JWT",key);

        writeBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getActivity(), LivingTipCreateActivity.class);
                startActivity(intent);
            }
        });

        return view;
    }

    public void onSuccess(List<boardData> list) {
        for(boardData board : list){
            Log.d("살림 팁 onSuccess","onSuccess");
            if(board.getType().equals("LIVE")){
                data.add(new LivingTipPostData(board.getId(), board.getCategory().getName(),board.getTitle(),board.getContent(),"글쓴이",board.getCreatedAt(),"이미지"));
            }
        }

        recyclerView.setHasFixedSize(true);
        adapter = new LivingTipPostAdapter(getActivity(), data);
        recyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));
        recyclerView.setAdapter(adapter);
    }

    @Override
    public void onResume(){
        super.onResume();
        data.clear();
        key = JWTManager.getSharedPreference(getActivity(),getString(R.string.saved_JWT));
        Log.d("JWT",key);
        livingTipUseCase.getData(key);
    }

}
