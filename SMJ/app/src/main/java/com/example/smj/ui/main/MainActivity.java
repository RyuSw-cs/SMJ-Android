package com.example.smj.ui.main;

import androidx.annotation.NonNull;
import androidx.core.app.ActivityCompat;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentActivity;
import androidx.viewpager2.widget.ViewPager2;

import android.Manifest;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.os.Build;
import android.os.Bundle;
import android.util.Log;
import android.view.MenuItem;
import android.widget.Toast;

import com.example.smj.R;
import com.example.smj.callback.JWTGetLocal;
import com.example.smj.domain.usecase.JWTUseCase;
import com.example.smj.ui.main.fragment.Convenience.ConvenienceFragment;
import com.example.smj.ui.main.fragment.LivingTipFragment;
import com.example.smj.ui.main.fragment.MyPageFragment;
import com.example.smj.ui.main.fragment.ScheduleFragment;
import com.example.smj.ui.main.fragment.TransactionFragment;
import com.example.smj.ui.main.fragment.ViewPagerAdapter;
import com.google.android.material.bottomnavigation.BottomNavigationView;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends FragmentActivity implements ActivityCompat.OnRequestPermissionsResultCallback, JWTGetLocal {
    private String at;
    public static String jwt;
    private ViewPager2 mViewPager;
    private ViewPagerAdapter pageAdapter;
    private int num_page = 5;
    private List<Fragment>list = new ArrayList<Fragment>();
    private ConvenienceFragment convenienceFragment;
    private LivingTipFragment livingTipFragment;
    private MyPageFragment myPageFragment;
    private ScheduleFragment scheduleFragment;
    private TransactionFragment transactionFragment;
    private JWTUseCase jwtUseCase;
    BottomNavigationView bottomNavigationView;

    private String[] REQUIRED_PERMISSIONS = {Manifest.permission.ACCESS_FINE_LOCATION};

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        checkPermission();
        init_Fragment();
        init_List();
        jwtUseCase = new JWTUseCase(this);
        getJWT();
        mViewPager = findViewById(R.id.pager);
        pageAdapter = new ViewPagerAdapter(this,num_page);
        pageAdapter.addFragments(list);
        mViewPager.setAdapter(pageAdapter);
        mViewPager.setCurrentItem(2);
        mViewPager.setOffscreenPageLimit(5);
        mViewPager.setUserInputEnabled(false);
        bottomNavigationView = findViewById(R.id.bottomNavigationView);
        bottomNavigationView.setSelectedItemId(R.id.tab3);
        bottomNavigationView.setOnNavigationItemSelectedListener(new BottomNavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                switch (item.getItemId()){
                    case R.id.tab1:
                        mViewPager.setCurrentItem(0);
                        return true;
                    case R.id.tab2:
                        mViewPager.setCurrentItem(1);
                        return true;
                    case R.id.tab3:
                        mViewPager.setCurrentItem(2);
                        return true;
                    case R.id.tab4:
                        mViewPager.setCurrentItem(3);
                        return true;
                    case R.id.tab5:
                        mViewPager.setCurrentItem(4);
                        return true;
                    default: return false;
                }
            }
        });


    }

    public void checkPermission(){
        //6.0미만시 권한체크가 필요없음 -> 메소드 종료
        if(Build.VERSION.SDK_INT < Build.VERSION_CODES.M)
            return;
        for(String permission:REQUIRED_PERMISSIONS){
            int check = checkCallingOrSelfPermission(permission);
            if(check == PackageManager.PERMISSION_DENIED){
                //권한요청 메시지를 나오게함.
                requestPermissions(REQUIRED_PERMISSIONS,0);
            }
        }
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grandResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grandResults);
        if(requestCode==0)
        {
            for(int i=0; i<grandResults.length; i++)
            {
                //허용됨
                if(grandResults[i]==PackageManager.PERMISSION_GRANTED){
                }
                else {
                    Toast.makeText(getApplicationContext(),"위치권한을 설정해주셔야 합니다.",Toast.LENGTH_LONG).show();
                    finish();
                }
            }
        }
    }
    private void init_Fragment() {
        convenienceFragment = new ConvenienceFragment();
        livingTipFragment = new LivingTipFragment();
        myPageFragment = new MyPageFragment();
        scheduleFragment = new ScheduleFragment();
        transactionFragment = new TransactionFragment();
    }
    private void init_List(){
        list.add(convenienceFragment);
        list.add(transactionFragment);
        list.add(livingTipFragment);
        list.add(scheduleFragment);
        list.add(myPageFragment);
    }
    public void getJWT(){
        Intent intent = getIntent(); /*데이터 수신*/
        at = intent.getExtras().getString("accessToken"); /*String형*/
        jwtUseCase.sendAT(at);
    }
    public void clickSuccess(String jwt){
        this.jwt = jwt;
    }
}