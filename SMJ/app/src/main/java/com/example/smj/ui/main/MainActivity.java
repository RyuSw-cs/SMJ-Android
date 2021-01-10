package com.example.smj.ui.main;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentActivity;
import androidx.viewpager2.widget.ViewPager2;

import android.os.Bundle;
import android.view.MenuItem;

import com.example.smj.R;
import com.example.smj.ui.main.fragment.ConvenienceFragment;
import com.example.smj.ui.main.fragment.LivingTipFragment;
import com.example.smj.ui.main.fragment.MyPageFragment;
import com.example.smj.ui.main.fragment.ScheduleFragment;
import com.example.smj.ui.main.fragment.TradeFragment;
import com.example.smj.ui.main.fragment.ViewPagerAdapter;
import com.google.android.material.bottomnavigation.BottomNavigationView;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends FragmentActivity {

    private ViewPager2 mViewPager;
    private ViewPagerAdapter pageAdapter;
    private int num_page = 5;
    private List<Fragment>list = new ArrayList<Fragment>();
    private ConvenienceFragment convenienceFragment;
    private LivingTipFragment livingTipFragment;
    private MyPageFragment myPageFragment;
    private ScheduleFragment scheduleFragment;
    private TradeFragment tradeFragment;

    BottomNavigationView bottomNavigationView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        init_Fragment();
        init_List();
        mViewPager = findViewById(R.id.pager);
        pageAdapter = new ViewPagerAdapter(this,num_page);
        pageAdapter.addFragments(list);

        //로그인 화면 후 2초만 기달려주새오..
        mViewPager.setAdapter(pageAdapter);
        //초기에 1002으로 맞춤 -> 살립꿀팁
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

    private void init_Fragment() {
        convenienceFragment = new ConvenienceFragment();
        livingTipFragment = new LivingTipFragment();
        myPageFragment = new MyPageFragment();
        scheduleFragment = new ScheduleFragment();
        tradeFragment = new TradeFragment();
    }
    private void init_List(){
        list.add(convenienceFragment);
        list.add(tradeFragment);
        list.add(livingTipFragment);
        list.add(scheduleFragment);
        list.add(myPageFragment);
    }
}