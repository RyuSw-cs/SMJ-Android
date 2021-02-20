package com.example.smj.ui.main.fragment.Convenience;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.MotionEvent;
import android.view.View;
import android.view.Window;
import android.widget.TextView;

import com.example.smj.R;
import com.example.smj.data.entity.PopupInfo;

public class ConveniencePopupActivity extends Activity {

    private TextView title;
    private TextView phone;
    private TextView address;

    private PopupInfo getData;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        setContentView(R.layout.activity_convenience_popup);

        Intent intent = getIntent();
        getData = (PopupInfo)intent.getSerializableExtra("data");

        title = (TextView)findViewById(R.id.convenience_detail_title);
        phone = (TextView)findViewById(R.id.convenience_detail_phone);
        address = (TextView)findViewById(R.id.convenience_detail_address);

        title.setText(getData.getTitle());
        if(getData.getPhone().equals("")){
            phone.setText("전화번호가 제공되지 않습니다.");
        }
        else{
            phone.setText(getData.getPhone());
        }
        if(getData.getAddress().equals("")){
            address.setText("주소가 제공되지 않습니다.");
        }
        else{
            address.setText(getData.getAddress());
        }
    }
    public void mOnClose(View v){
        finish();
    }
    @Override
    public boolean onTouchEvent(MotionEvent event){
        if(event.getAction()==MotionEvent.ACTION_OUTSIDE){
            return false;
        }
        return true;
    }
}
