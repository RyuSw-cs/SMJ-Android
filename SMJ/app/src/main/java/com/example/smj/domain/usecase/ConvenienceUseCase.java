package com.example.smj.domain.usecase;

import com.example.smj.data.entity.Convenience.Document;
import com.example.smj.data.repository.ConvenienceMarkerRepository;
import com.example.smj.callback.RetrofitOnSuccess;
import com.example.smj.ui.Convenience.ConvenienceFragment;

import java.util.ArrayList;

import static com.example.smj.ui.Convenience.ConvenienceFragment.currentMapPoint;

public class ConvenienceUseCase implements RetrofitOnSuccess {

    private ConvenienceMarkerRepository convenienceMarkerRepository;
    private ConvenienceFragment convenienceFragment;
    private ArrayList<Document> list = new ArrayList<>();

    public ConvenienceUseCase(ConvenienceFragment convenienceFragment){
        convenienceMarkerRepository = new ConvenienceMarkerRepository();
        this.convenienceFragment = convenienceFragment;
    }

    public void sendLocalName(String Category){
        double x = currentMapPoint.getMapPointGeoCoord().latitude;
        double y = currentMapPoint.getMapPointGeoCoord().longitude;
        String local = "";
        switch (Category){
            case "음식점":
                local = "FD6";
                break;
            case "마트":
                local = "MT1";
                break;
            case "편의점":
                local = "CS2";
                break;
            case "공공기관":
                local = "PO3";
                break;
            case "병원":
                local = "HP8";
                break;
            case "약국":
                local = "PM9";
                break;
            case "주유소/충전소":
                local = "OL7";
                break;
            case "은행":
                local = "BK9";
                break;
            case "문화시설":
                local = "CT1";
                break;
            case "학원":
                local = "AC5";
                break;
            case "주차장":
                local = "PK6";
                break;
            default: break;
        }
        convenienceMarkerRepository.retrieveLocals(local,x,y, this);
    }

    @Override
    public void onSuccess(Object object) {
        if(object != null){
            list = (ArrayList<Document>)object;
            convenienceFragment.clickSuccess(list);
        }
    }
}
