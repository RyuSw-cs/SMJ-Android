package com.example.smj.ui.main.fragment.Convenience;

import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.os.Handler;
import android.util.DisplayMetrics;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Spinner;
import android.widget.Toast;

import androidx.fragment.app.Fragment;

import com.example.smj.R;
import com.example.smj.ui.main.fragment.Convenience.datasource.RemoteDataSource;
import com.example.smj.ui.main.fragment.Convenience.entity.Entity_Convenience;
import com.example.smj.ui.main.fragment.Convenience.remote.Category;
import com.example.smj.ui.main.fragment.Convenience.remote.Document;
import com.example.smj.ui.main.fragment.Convenience.remote.PopupInfo;
import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.android.material.tabs.TabLayout;

import net.daum.mf.map.api.MapCircle;
import net.daum.mf.map.api.MapPOIItem;
import net.daum.mf.map.api.MapPoint;
import net.daum.mf.map.api.MapView;

import java.util.ArrayList;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

import static android.app.Activity.RESULT_OK;

public class ConvenienceFragment extends Fragment implements MapView.CurrentLocationEventListener, TabLayout.OnTabSelectedListener, MapView.POIItemEventListener, View.OnClickListener {

    private MapView mapView;
    private View view;
    private ViewGroup mapViewContainer;
    private MapPoint currentMapPoint;
    private TabLayout tabMenu;
    private FloatingActionButton floatingActionButton;

    private ArrayList<Document> getList = new ArrayList<>();
    private PopupInfo popupList;

    private String word;
    private double x,y;
    private boolean checkLocationButton= false;
    private boolean checkToastMessage = false;


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle saveInstanceState){
        mapView = new MapView(getActivity());
        view = inflater.inflate(R.layout.activity_convenience,container,false);
        mapViewContainer = (ViewGroup)view.findViewById(R.id.map_view);
        tabMenu = (TabLayout)view.findViewById(R.id.convenience_menu);
        floatingActionButton = (FloatingActionButton)view.findViewById(R.id.floatingActionButton);
        mapView.setCurrentLocationEventListener(this);
        mapView.setPOIItemEventListener(this);
        tabMenu.addOnTabSelectedListener(this);
        floatingActionButton.setOnClickListener(this);
        mapView.setCurrentLocationTrackingMode(MapView.CurrentLocationTrackingMode.TrackingModeOnWithoutHeading);
        mapView.setZoomLevel(3,true);
        mapViewContainer.addView(mapView);
        Handler handler = new Handler();
        handler.postDelayed(new Runnable() {
            @Override
            public void run() {
                TabLayout.Tab tab = tabMenu.getTabAt(0);
                tabMenu.selectTab(tab);
            }
        },1500);
        return view;
    }

    //트래킹 모드 설정 시 실행됨
    @Override
    public void onCurrentLocationUpdate(MapView mapView, MapPoint mapPoint, float v) {
        MapPoint.GeoCoordinate mapPointGeo = mapPoint.getMapPointGeoCoord();
        Log.d("위치 업데이트",String.format("업데이트 됨(%f, %f)",mapPointGeo.latitude, mapPointGeo.longitude));
        currentMapPoint = MapPoint.mapPointWithGeoCoord(mapPointGeo.latitude, mapPointGeo.longitude);
        mapView.setMapCenterPoint(currentMapPoint, true);
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

    private void setPOIItem(int position){
        //자취생들이 필요한 정보를 추리자.
         switch (position){
            case 0:
                word = "MT1";
                break;
            case 1:
                word = "CS2";
                break;
            case 2:
                word = "PS3";
                break;
            case 3:
                word = "SC4";
                break;
            case 4:
                word = "AC5";
                break;
            case 5:
                word = "PK6";
                break;
            case 6:
                word = "OL7";
                break;
            case 7:
                word = "SW8";
                break;
            case 8:
                word = "BK9";
                break;
            case 9:
                word = "CT1";
                break;
            case 10:
                word = "AG2";
                break;
            case 11:
                word = "PO3";
                break;
            case 12:
                word = "AT4";
                break;
            case 13:
                word = "AD5";
                break;
            case 14:
                word = "FD6";
                break;
            case 15:
                word = "CE7";
                break;
            case 16:
                word = "HP8";
                break;
            default: word ="PM9";
        }
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
        for(int i = 0; i<getList.size();i++){
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
    public void onTabSelected(TabLayout.Tab tab) {
        if(!checkToastMessage)
            checkToastMessage = true;
        else
            Toast.makeText(getActivity(), "주변 " + tab.getText() + " 검색합니다.", Toast.LENGTH_LONG).show();
        setPOIItem(tab.getPosition());
        getList.clear();
        mapView.removeAllPOIItems();
        Entity_Convenience entityConvenience = RemoteDataSource.getInstance().create(Entity_Convenience.class);
        x = currentMapPoint.getMapPointGeoCoord().latitude;
        y = currentMapPoint.getMapPointGeoCoord().longitude;
        Call<Category> call = entityConvenience.getSearchCategory("KakaoAK "+ getString(R.string.kakao_api_key),word,y+"",x+"",1500);
        call.enqueue(new Callback<Category>() {
            @Override
            public void onResponse(Call<Category> call, Response<Category> response) {
                if(response.isSuccessful()){
                    getList.addAll(response.body().getDocuments());
                    for(Document document : getList){
                        MapPOIItem marker = new MapPOIItem();
                        marker.setItemName(document.getPlaceName());
                        double x = Double.parseDouble(document.getY());
                        double y = Double.parseDouble(document.getX());
                        MapPoint mapPoint = MapPoint.mapPointWithGeoCoord(x,y);
                        marker.setMapPoint(mapPoint);
                        marker.setMarkerType(MapPOIItem.MarkerType.CustomImage);
                        marker.setCustomImageResourceId(R.drawable.convenience_marker_12dp);
                        marker.setCustomImageAutoscale(false);
                        mapView.addPOIItem(marker);
                    }
                }
            }

            @Override
            public void onFailure(Call<Category> call, Throwable t) {

            }
        });
    }

    @Override
    public void onTabUnselected(TabLayout.Tab tab) {
        //탭 선택이 사라질 때
    }

    @Override
    public void onTabReselected(TabLayout.Tab tab) {
        //탭이 다시 눌릴 때
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
}
