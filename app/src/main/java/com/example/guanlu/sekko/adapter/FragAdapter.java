package com.example.guanlu.sekko.adapter;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;

import java.util.List;

/**
 * Created by guanlu on 16/6/1.
 */
public class FragAdapter extends FragmentPagerAdapter {

    private List<Fragment> mFragments;
    private List<String> titleList;

    public FragAdapter(FragmentManager fm, List<Fragment> fragments, List<String> titleList) {
        super(fm);
        // TODO Auto-generated constructor stub
        mFragments=fragments;
        this.titleList = titleList;
    }

    @Override
    public Fragment getItem(int arg0) {
        // TODO Auto-generated method stub
        return mFragments.get(arg0);
    }

    @Override
    public int getCount() {
        // TODO Auto-generated method stub
        return mFragments.size();
    }

    @Override
    public CharSequence getPageTitle(int position) {

        return titleList.get(position);
    }

}