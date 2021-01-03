package com.example.smj.utill.ui.main.fragment;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentActivity;
import androidx.viewpager2.adapter.FragmentStateAdapter;

public class ViewPagerAdapter extends FragmentStateAdapter {

    public int mCount;
    public ViewPagerAdapter(@NonNull FragmentActivity fragmentActivity, int count) {
        super(fragmentActivity);
        mCount = count;
    }

    @NonNull
    @Override
    public Fragment createFragment(int position) {
        int index = getRealPosition(position);
        if(index == 0)return new ConvenienceFragment();
        else if(index == 1)return new TradeFragment();
        else if(index == 2)return new LivingTipFragment();
        else if(index == 3)return new ScheduleFragment();
        else return new MyPageFragment();
    }

    public int getRealPosition(int position) {
        return position % mCount;
    }

    @Override
    public int getItemCount() {
        return 2000;
    }
}
