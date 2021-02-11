package com.example.smj.ui.main.fragment.Convenience;

import android.Manifest;
import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.location.LocationManager;
import android.os.Bundle;
import android.provider.Settings;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.smj.R;
import com.example.smj.callback.ConvenienceGetLocal;
import com.example.smj.data.entity.Document;
import com.example.smj.data.entity.PopupInfo;
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

    private static final int GPS_ENABLE_REQUEST_CODE = 2001;
    private static final int PERMISSIONS_REQUEST_CODE = 100;
    String[] REQUIRED_PERMISSIONS = {Manifest.permission.ACCESS_FINE_LOCATION};

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle saveInstanceState){
        mapView = new MapView(getActivity());
        //위치권한 설정
        if(!checkLocationServicesStatus())
            showDialogForLocationServiceSetting();
        else
            checkRunTimePermission();
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

    @Override
    public void onRequestPermissionResult(int permsRequestCode, @NonNull String[] permission, @NonNull int[] grandResults){
        if(permsRequestCode == PERMISSIONS_REQUEST_CODE && grandResults.length == REQUIRED_PERMISSIONS.length){
            boolean check_result = true;
            for(int result : grandResults){
                if(result != PackageManager.PERMISSION_GRANTED){
                    check_result = false;
                    break;
                }
            }
            //이제 true면 위치권한 설정 완료!
            if(check_result){
                mapView.setCurrentLocationTrackingMode(MapView.CurrentLocationTrackingMode.TrackingModeOnWithoutHeading);
            }
            //거부한 퍼미션 존재함, 앱을 사용하지 못하는 경우는 2가지
            else{
                if(ActivityCompat.shouldShowRequestPermissionRationale(getActivity(), REQUIRED_PERMISSIONS[0])){
                    Toast.makeText(getContext(),"퍼미션이 거부되었습니다. 앱을 다시 실행해주세요.",Toast.LENGTH_LONG).show();
                    FragmentManager fragmentManager = getActivity().getSupportFragmentManager();
                    fragmentManager.beginTransaction().remove(ConvenienceFragment.this).commit();
                    fragmentManager.popBackStack();
                }
                else{
                    Toast.makeText(getContext(),"퍼미션이 거부되었습니다. 앱 설정에서 퍼미션을 허용해주세요.",Toast.LENGTH_LONG).show();
                }
            }
        }
    }

    public void checkRunTimePermission(){
        //위치 퍼미션을 가지고 있는지 확인
        int hasFineLocationPermission = ContextCompat.checkSelfPermission(getContext(), Manifest.permission.ACCESS_FINE_LOCATION);
        //퍼미션이 존재하면??
        if(hasFineLocationPermission == PackageManager.PERMISSION_GRANTED){
            mapView.setCurrentLocationTrackingMode(MapView.CurrentLocationTrackingMode.TrackingModeOnWithoutHeading);
        }
        //퍼미션 요청을 허용한 적이 없음 -> 퍼미션 요청
        else{
            if(ActivityCompat.shouldShowRequestPermissionRationale(getActivity(), REQUIRED_PERMISSIONS[0])){
                Toast.makeText(getContext(), "해당 기능은 위치 접근 권한 필요합니다.",Toast.LENGTH_LONG).show();
                ActivityCompat.requestPermissions(getActivity(), REQUIRED_PERMISSIONS, PERMISSIONS_REQUEST_CODE);
            }
            else{
                //위치 권한 요구
                ActivityCompat.requestPermissions(getActivity(), REQUIRED_PERMISSIONS, PERMISSIONS_REQUEST_CODE);
            }
        }
    }

    private void showDialogForLocationServiceSetting(){
        AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());
        builder.setTitle("위치 서비스 비활성화");
        builder.setMessage("해당 기능은 위치 서비스가 필요합니다\n"+"위치 권환을 수정하시겠습니까?");
        builder.setCancelable(true);
        builder.setPositiveButton("설정", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                Intent callGPSSettingIntent = new Intent(Settings.ACTION_LOCATION_SOURCE_SETTINGS);
                startActivityForResult(callGPSSettingIntent, GPS_ENABLE_REQUEST_CODE);
            }
        });
        builder.setNegativeButton("취소", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                dialog.cancel();
            }
        });
        builder.create().show();
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data){
        super.onActivityResult(requestCode, resultCode, data);
        switch (requestCode){
            case GPS_ENABLE_REQUEST_CODE:
                if(checkLocationServicesStatus()) {
                    checkRunTimePermission();
                    return;
                }
                break;
        }
    }

    public boolean checkLocationServicesStatus(){
        LocationManager locationManager = (LocationManager)getActivity().getSystemService(Context.LOCATION_SERVICE);
        return locationManager.isProviderEnabled(LocationManager.GPS_PROVIDER) || locationManager.isProviderEnabled(LocationManager.NETWORK_PROVIDER);
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
