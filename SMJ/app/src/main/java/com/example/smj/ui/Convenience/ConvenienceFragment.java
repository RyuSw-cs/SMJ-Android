package com.example.smj.ui.Convenience;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.smj.R;
import com.example.smj.callback.ConvenienceGetLocal;
import com.example.smj.data.entity.Convenience.Document;
import com.example.smj.data.entity.Convenience.PopupInfo;
import com.example.smj.domain.usecase.ConvenienceUseCase;
import com.google.android.material.floatingactionbutton.FloatingActionButton;

import net.daum.mf.map.api.MapPOIItem;
import net.daum.mf.map.api.MapPoint;
import net.daum.mf.map.api.MapView;

import java.util.ArrayList;

public class ConvenienceFragment extends Fragment implements MapView.CurrentLocationEventListener, MapView.POIItemEventListener, View.OnClickListener, ConvenienceGetLocal {

    private MapView mapView;
    private View view;
    private ViewGroup mapViewContainer;
    public static MapPoint currentMapPoint;
    private FloatingActionButton floatingActionButton;
    private ArrayList<Document>getList = new ArrayList<>();

    private RecyclerView recyclerView;
    private ConvenienceAdapter adapter;
    private RecyclerView.LayoutManager layoutManager;

    private PopupInfo popupList;
    private ConvenienceUseCase convenienceUseCase;
    private boolean checkLocationButton= false;
    private String[] getItemList;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle saveInstanceState){
        mapView = new MapView(getActivity());
        view = inflater.inflate(R.layout.activity_convenience,container,false);
        mapViewContainer = (ViewGroup)view.findViewById(R.id.map_view);
        floatingActionButton = (FloatingActionButton)view.findViewById(R.id.floatingActionButton);
        recyclerView = view.findViewById(R.id.recyclerView);

        layoutManager = new LinearLayoutManager(getActivity(),LinearLayoutManager.HORIZONTAL,true);
        convenienceUseCase = new ConvenienceUseCase(this);
        recyclerView.setLayoutManager(layoutManager);

        getItemList = getResources().getStringArray(R.array.convenienceList);
        adapter = new ConvenienceAdapter(getActivity() ,getItemList);
        recyclerView.setAdapter(adapter);
        //이벤트
        adapter.setOnItemClickListener(new ConvenienceAdapter.OnItemClickListener() {
            @Override
            public void onItemClick(View view, int position) {
                mapView.removeAllPOIItems();
                getList.clear();
                convenienceUseCase.sendLocalName(getItemList[position]);
            }
        });
        recyclerView.scrollToPosition(10);
        mapView.setCurrentLocationEventListener(this);
        mapView.setPOIItemEventListener(this);
        floatingActionButton.setOnClickListener(this);
        mapView.setZoomLevel(3,true);
        mapViewContainer.addView(mapView);

        return view;
    }



    //트래킹 모드 설정 시 실행됨
    @Override
    public void onCurrentLocationUpdate(MapView mapView, MapPoint mapPoint, float v) {
        MapPoint.GeoCoordinate mapPointGeo = mapPoint.getMapPointGeoCoord();
        Log.d("위치 업데이트",String.format("업데이트 됨(%f, %f)",mapPointGeo.latitude, mapPointGeo.longitude));
        currentMapPoint = MapPoint.mapPointWithGeoCoord(mapPointGeo.latitude, mapPointGeo.longitude);
        mapView.setMapCenterPoint(currentMapPoint, true);
        //만약 트래킹이 계속 따라가야 하는 상황이라면?
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
        mapView.setCurrentLocationTrackingMode(MapView.CurrentLocationTrackingMode.TrackingModeOnWithoutHeading);
    }

    @Override
    public void onPOIItemSelected(MapView mapView, MapPOIItem mapPOIItem) {

    }

    @Override
    public void onCalloutBalloonOfPOIItemTouched(MapView mapView, MapPOIItem mapPOIItem) {
    /* Deprecated */
    }

    @Override
    public void onCalloutBalloonOfPOIItemTouched(MapView mapView, MapPOIItem mapPOIItem, MapPOIItem.CalloutBalloonButtonType calloutBalloonButtonType) {
        Intent intent = new Intent(getContext(), ConveniencePopupActivity.class);
        //인텐트로 이름, 전화번호, 주소
        int getListSize = getList.size();
        for(int i = 0; i<getListSize;i++){
            if(mapPOIItem.getItemName().equals(getList.get(i).getPlaceName())){
                popupList = new PopupInfo(getList.get(i).getPlaceName(),getList.get(i).getPhone(),getList.get(i).getRoadAddressName());
            }
        }
        intent.putExtra("data",popupList);
        startActivity(intent);
    }
    @Override
    public void onDraggablePOIItemMoved(MapView mapView, MapPOIItem mapPOIItem, MapPoint mapPoint) {

    }

    @Override
    public void onClick(View v) {
        if(!checkLocationButton) {
            mapView.setCurrentLocationTrackingMode(MapView.CurrentLocationTrackingMode.TrackingModeOnWithHeading);
            checkLocationButton = true;
        }
        else{
            mapView.setCurrentLocationTrackingMode(MapView.CurrentLocationTrackingMode.TrackingModeOff);
            checkLocationButton = false;
        }
    }

    @Override
    public void clickSuccess(ArrayList<Document> list) {
        getList = list;
        for(Document document : list){
            MapPOIItem marker = new MapPOIItem();
            //마커 클릭 시 이름
            marker.setItemName(document.getPlaceName());
            //마커를 생성하는 좌표
            //MapPoint로 변환해야함.
            double x = Double.parseDouble(document.getY());
            double y = Double.parseDouble(document.getX());
            MapPoint mapPoint = MapPoint.mapPointWithGeoCoord(x,y);
            marker.setMapPoint(mapPoint);
            //커스텀마커 사용
            marker.setMarkerType(MapPOIItem.MarkerType.CustomImage);
            marker.setCustomImageResourceId(R.drawable.convenience_marker_12dp);
            marker.setCustomImageAutoscale(false);
            //마커 추가
            mapView.addPOIItem(marker);
        }
    }
}
