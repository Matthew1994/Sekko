package com.example.guanlu.sekko;


import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

public class CinemaDetailActivity extends AppCompatActivity {


    private TabLayout tabLayout;
    private ViewPager viewPager;
    private List<Fragment> tabList;
    private List<String> titleList;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_cinema_detail);

        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.cinema_fab_return);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent();
                intent.setClass(CinemaDetailActivity.this, MainActivity.class);
                startActivity(intent);
            }
        });


        Bundle bundle = this.getIntent().getExtras();
        if (!bundle.isEmpty()) {

            String title = "";
            title = bundle.getString("name").toString();
            setTitle(title);

        }

        tabLayout = (TabLayout)findViewById(R.id.cTtabLayout);
        viewPager = (ViewPager)findViewById(R.id.cinema_viewpager);

        tabList =new ArrayList<Fragment>();
        tabList.add(new TabCinemaDescription());
        tabList.add(new TabCinemaOrder());

        FragAdapter adapter = new FragAdapter(getSupportFragmentManager(), tabList);

        viewPager.setAdapter(adapter);

        titleList = new ArrayList<String>();
        titleList.add("影院介绍");
        titleList.add("排片购票");

        tabLayout.setTabMode(TabLayout.MODE_FIXED);//设置tab模式，当前为系统默认模式
        tabLayout.addTab(tabLayout.newTab().setText(titleList.get(0)));//添加tab选项卡
        tabLayout.addTab(tabLayout.newTab().setText(titleList.get(1)));//添加tab选项卡

        tabLayout.setupWithViewPager(viewPager);//将TabLayout和ViewPager关联起来。
        tabLayout.setTabsFromPagerAdapter(adapter);//给Tabs设置适配器


    }

    public class FragAdapter extends FragmentPagerAdapter {

        private List<Fragment> mFragments;

        public FragAdapter(FragmentManager fm, List<Fragment> fragments) {
            super(fm);
            // TODO Auto-generated constructor stub
            mFragments=fragments;
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

}



