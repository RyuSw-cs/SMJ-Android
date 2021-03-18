package com.example.smj.ui.main.fragment;

import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.smj.R;
import com.example.smj.data.entity.Schedule.Alarm;
import com.example.smj.data.entity.board.boardData;
import com.example.smj.domain.usecase.LivingTipUseCase;
import com.example.smj.ui.LivingTip.LivingTipPostAdapter;
import com.example.smj.ui.LivingTip.LivingTipPostData;
import com.example.smj.ui.main.MainActivity;

import java.util.ArrayList;
import java.util.List;

//홈 버튼
public class LivingTipFragment extends Fragment {

    ArrayList<LivingTipPostData> data = new ArrayList<>();
    List<boardData> boardData = new ArrayList<>();
    RecyclerView recyclerView;
    LivingTipPostAdapter adapter;

    public LivingTipFragment(){
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle saveInstanceState){
        View view = inflater.inflate(R.layout.activity_living_tip_main,container,false);
        LivingTipUseCase livingTipUseCase = new LivingTipUseCase(this);
        recyclerView = (RecyclerView) view.findViewById(R.id.living_tip_post_list);

        data.add(new LivingTipPostData("카테고리","타이틀","콘텐츠","글쓴이","날짜","이미지"));

        /*Log.d("get전","get전");
        livingTipUseCase.getData("eyJhbGciOiJIUzI1NiJ9.eyJzdWIiOiJqb25naHl1bl9oQGtha2FvLmNvbSIsImlhdCI6MTYxNjA0NzE2MSwiZXhwIjoxNjE2MDQ4OTYxfQ.r2BOEH3S4RjcX-LdCLZiVhjFMCQ2i0GK7izM0Yu0Nl4");
        Log.d("get후","get후");*/

        recyclerView.setHasFixedSize(true);
        adapter = new LivingTipPostAdapter(getActivity(), data);
        recyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));
        recyclerView.setAdapter(adapter);
        return view;
    }

/*    public void getData(Object object) {
        if(object != null){
            boardData = (List<boardData>)object;
            for(boardData board : boardData){
                data.add(new LivingTipPostData(board.getCategory().getName(),board.getTitle(),board.getContent(),"글쓴이",board.getCreateAt(),"이미지"));
            }
        }
    }*/
}
