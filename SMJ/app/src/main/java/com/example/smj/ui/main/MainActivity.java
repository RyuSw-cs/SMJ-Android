package com.example.smj.ui.main;

import androidx.annotation.NonNull;
import androidx.fragment.app.FragmentActivity;
import androidx.viewpager2.adapter.FragmentStateAdapter;
import androidx.viewpager2.widget.ViewPager2;

import android.os.Bundle;
import android.util.Log;
import android.view.MenuItem;

import com.example.smj.R;
import com.example.smj.ui.main.fragment.ViewPagerAdapter;
import com.google.android.material.bottomnavigation.BottomNavigationView;

public class MainActivity extends FragmentActivity {

    private ViewPager2 mViewPager;
    private FragmentStateAdapter pageAdapter;
    private int num_page = 5;
    private int check_num = 0;

    BottomNavigationView bottomNavigationView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        mViewPager = findViewById(R.id.pager);

        pageAdapter = new ViewPagerAdapter(this,num_page);

        //로그인 화면 후 2초만 기달려주새오..
        mViewPager.setAdapter(pageAdapter);
        //초기에 1002으로 맞춤 -> 살립꿀팁
        Log.d("123",String.valueOf(mViewPager.getCurrentItem()));
        mViewPager.setCurrentItem(1002);
        mViewPager.setOffscreenPageLimit(5);
        mViewPager.setUserInputEnabled(false);

        bottomNavigationView = findViewById(R.id.bottomNavigationView);
        bottomNavigationView.setOnNavigationItemSelectedListener(new BottomNavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                switch (item.getItemId()){
                    case R.id.tab1:
                        mViewPager.setCurrentItem(1000);
                        return true;
                    case R.id.tab2:
                        mViewPager.setCurrentItem(1001);
                        return true;
                    case R.id.tab3:
                        mViewPager.setCurrentItem(1002);
                        return true;
                    case R.id.tab4:
                        mViewPager.setCurrentItem(1003);
                        return true;
                    case R.id.tab5:
                        mViewPager.setCurrentItem(1004);
                        return true;
                    default: return false;
                }
            }
        });
    }
}