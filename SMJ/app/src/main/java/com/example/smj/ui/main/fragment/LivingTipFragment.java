package com.example.smj.ui.main.fragment;

import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.smj.Manager.JWTManager;
import com.example.smj.R;
import com.example.smj.data.entity.Schedule.Alarm;
import com.example.smj.data.entity.board.boardData;
import com.example.smj.domain.usecase.LivingTipUseCase;
import com.example.smj.ui.LivingTip.LivingTipPostAdapter;
import com.example.smj.ui.LivingTip.LivingTipPostData;
import com.example.smj.ui.main.MainActivity;
import com.example.smj.ui.transaction.TransactionPostAdapter;

import java.util.ArrayList;
import java.util.List;

//홈 버튼
public class LivingTipFragment extends Fragment {

    ArrayList<LivingTipPostData> data = new ArrayList<>();
    RecyclerView recyclerView;
    LivingTipPostAdapter adapter;

    public LivingTipFragment(){
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle saveInstanceState){
        View view = inflater.inflate(R.layout.activity_living_tip_main,container,false);
        LivingTipUseCase livingTipUseCase = new LivingTipUseCase(this);
        recyclerView = (RecyclerView) view.findViewById(R.id.living_tip_post_list);
        String key = JWTManager.getSharedPreference(getActivity(),getString(R.string.saved_JWT));

        livingTipUseCase.getData(key);

        Log.d("JWT",key);

        return view;
    }

    public void onSuccess(List<boardData> list) {
        for(boardData board : list){
            Log.d("살림 팁 onSuccess","onSuccess");
            if(board.getType().equals("LIVE")){
                data.add(new LivingTipPostData(board.getId(), board.getCategory().getName(),board.getTitle(),board.getContent(),"글쓴이",board.getCreateAt(),"이미지"));
            }
        }

        recyclerView.setHasFixedSize(true);
        adapter = new LivingTipPostAdapter(getActivity(), data);
        recyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));
        recyclerView.setAdapter(adapter);
    }
}
