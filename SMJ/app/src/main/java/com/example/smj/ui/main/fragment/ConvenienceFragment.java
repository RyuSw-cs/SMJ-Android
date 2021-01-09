package com.example.smj.ui.main.fragment;

import android.os.Bundle;
import android.os.Handler;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.fragment.app.Fragment;

import com.example.smj.R;

import net.daum.mf.map.api.MapPoint;
import net.daum.mf.map.api.MapView;

public class ConvenienceFragment extends Fragment implements MapView.CurrentLocationEventListener {

    MapView mapView;
    View view;
    ViewGroup mapViewContainer;
    MapPoint currentMapPoint;
    Handler handler;

    public ConvenienceFragment(){

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle saveInstanceState){
        mapView = new MapView(getActivity());
        view = inflater.inflate(R.layout.activity_convenience,container,false);
        mapViewContainer = (ViewGroup)view.findViewById(R.id.map_view);
        mapView.setCurrentLocationEventListener(this);
        handler = new Handler();
        mapView.setCurrentLocationTrackingMode(MapView.CurrentLocationTrackingMode.TrackingModeOnWithoutHeading);
        mapViewContainer.addView(mapView);
        return view;
    }

    @Override
    public void onCurrentLocationUpdate(MapView mapView, MapPoint mapPoint, float v) {
        MapPoint.GeoCoordinate mapPointGeo = mapPoint.getMapPointGeoCoord();
        Log.d("위치 업데이트",String.format("업데이트 됨(%f, %f)",mapPointGeo.latitude, mapPointGeo.longitude));
        currentMapPoint = MapPoint.mapPointWithGeoCoord(mapPointGeo.latitude, mapPointGeo.longitude);

        mapView.setMapCenterPoint(currentMapPoint, true);
        mapView.setCurrentLocationTrackingMode(MapView.CurrentLocationTrackingMode.TrackingModeOff);
    }

    @Override
    public void onCurrentLocationDeviceHeadingUpdate(MapView mapView, float v) {

    }

    @Override
    public void onCurrentLocationUpdateFailed(MapView mapView) {

    }

    @Override
    public void onCurrentLocationUpdateCancelled(MapView mapView) {

    }
    @Override
    public void onPause() {
        super.onPause();
        mapView.setCurrentLocationTrackingMode(MapView.CurrentLocationTrackingMode.TrackingModeOff);
    }
    public void onResume() {
        super.onResume();
        mapView.setCurrentLocationTrackingMode(MapView.CurrentLocationTrackingMode.TrackingModeOff);
    }
}
