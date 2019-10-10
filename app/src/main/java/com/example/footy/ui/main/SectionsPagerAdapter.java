package com.example.footy.ui.main;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentPagerAdapter;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;

/**
 * A [FragmentPagerAdapter] that returns a fragment corresponding to
 * one of the sections/tabs/pages.
 */
public class SectionsPagerAdapter extends FragmentPagerAdapter {


    public SectionsPagerAdapter(FragmentManager fm) {
        super(fm);
    }

    private String prevThreeDay = new SimpleDateFormat("yyyy-MM-dd", Locale.getDefault()).format(new Date(System.currentTimeMillis()-24*4*60*60*1000));
    private String prevTwoDay = new SimpleDateFormat("yyyy-MM-dd", Locale.getDefault()).format(new Date(System.currentTimeMillis()-24*3*60*60*1000));
    private String prevOneDay = new SimpleDateFormat("yyyy-MM-dd", Locale.getDefault()).format(new Date(System.currentTimeMillis()-24*2*60*60*1000));
    private String nextOneDay = new SimpleDateFormat("yyyy-MM-dd", Locale.getDefault()).format(new Date(System.currentTimeMillis()+24*2*60*60*1000));
    private String nextTwoDay = new SimpleDateFormat("yyyy-MM-dd", Locale.getDefault()).format(new Date(System.currentTimeMillis()+24*3*60*60*1000));
    private String nextThreeDay = new SimpleDateFormat("yyyy-MM-dd", Locale.getDefault()).format(new Date(System.currentTimeMillis()+24*4*60*60*1000));


    private String[] tabTitles = new String[]{prevThreeDay, prevTwoDay, prevOneDay,"Yesterday", "Today", "Tomorrow", nextOneDay, nextTwoDay, nextThreeDay};

    @NonNull
    @Override
    public Fragment getItem(int position) {
        Fragment fragment = null;

        if(position == 0){
            fragment = new PreviousThreeDayFragment();
        }else if(position == 1){
            fragment = new PreviousTwoDayFragment();
        }else if (position == 2){
            fragment = new PreviousOneDayFragment();
        }else if (position == 3){
            fragment = new YesterdayFragment();
        }else if (position == 4){
            fragment = new TodayFragment();
        }else if (position == 5){
            fragment = new TomorrowFragment();
        }else if (position == 6){
            fragment = new NextOneDayFragment();
        }else if (position == 7){
            fragment = new NextTwoDayFragment();
        }else if (position == 8){
            fragment = new NextThreeDayFragment();
        }
        return fragment;
    }

    @Nullable
    @Override
    public CharSequence getPageTitle(int position) {
        return tabTitles[position];
    }

    @Override
    public int getCount() {
        return 9;
    }
}