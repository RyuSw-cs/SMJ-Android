package com.example.smj.ui.main.fragment;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.fragment.app.Fragment;

import com.example.smj.R;
import net.daum.mf.map.api.MapView;

public class ConvenienceFragment extends Fragment {

    public ConvenienceFragment(){

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle saveInstanceState){
        View view = inflater.inflate(R.layout.activity_convenience,container,false);
        MapView mapView = new MapView(getActivity());
        ViewGroup mapViewContainer = (ViewGroup)view.findViewById(R.id.map_view);
        mapViewContainer.addView(mapView);
        //mapView.setMapCenterPoint();

        return view;
    }
}
