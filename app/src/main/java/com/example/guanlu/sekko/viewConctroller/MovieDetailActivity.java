package com.example.guanlu.sekko.viewConctroller;

import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.drawable.BitmapDrawable;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.text.SpannableStringBuilder;
import android.view.View;
import android.view.WindowManager;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;


import com.android.volley.toolbox.ImageLoader;
import com.example.guanlu.sekko.R;
import com.example.guanlu.sekko.cache.ImgCacheManager;
import com.example.guanlu.sekko.VO.Movie;
import com.example.guanlu.sekko.util.BitmapUtil;

import java.util.ArrayList;
import java.util.List;

public class MovieDetailActivity extends AppCompatActivity {

    private ImageView detail_img;


    private android.support.design.widget.CollapsingToolbarLayout detail_bg;
    private TextView detail_rating;

    private Bitmap bg;
    private Bitmap bitmap;

    private static int Width;
    private static int Height;

    //tabs
    private TabLayout tabLayout;
    private ViewPager viewPager;
    private List<Fragment> tabList;
    private List<String> titleList;

    public static Movie myMovie;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_movie_detail);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        detail_img=(ImageView)findViewById(R.id.detail_img);
        detail_bg =(android.support.design.widget.CollapsingToolbarLayout) findViewById(R.id.toolbar_layout);
        detail_rating=(TextView)findViewById(R.id.detail_rating);


        //get Window's height and width
        WindowManager wm = this.getWindowManager();
        Width = wm.getDefaultDisplay().getWidth();
        Height = wm.getDefaultDisplay().getHeight();

        //process intent data
        myMovie =(Movie)this.getIntent().getSerializableExtra("movie");
        Toast.makeText(this,myMovie.getMovieShowTime(),Toast.LENGTH_SHORT).show();

        //set rating
        String rating ="";
        rating = myMovie.getMovieRating();
        String result = "";

        int num_real = Integer.valueOf(rating.split("\\.")[0]);
        for(int i = 0;i<num_real/2;i++) {
            result+="★";
        }
        for(int i = 0;i<5-num_real/2;i++) {
            result+="☆";
        }
        detail_rating.setText(result+" "+myMovie.getMovieRating());

        // set img

        ImageLoader.ImageListener listener = ImageLoader.getImageListener(detail_img, android.R.drawable.ic_menu_rotate, android.R.drawable.ic_delete);
        ImageLoader.ImageContainer bgContainer = ImgCacheManager.getLoader().get(myMovie.getMovieImg(), listener);


        //set bg

        byte[] bitmap=Movie.getBytes(bgContainer.getBitmap());
        bg = BitmapUtil.AfterBlurring(getBaseContext(), Movie.getBitmap(bitmap), Width, (int) (Height * 0.4));



        Drawable bgr  = new BitmapDrawable(bg);
        detail_bg.setBackground(bgr);
//        detail_img.setImageBitmap(bgContainer.getBitmap());


        //set title
        String title = "";
//        SpannableStringBuilder tx = new SpannableStringBuilder();
//        tx.setSpan(tx);
        title = myMovie.getMovieName();
        setTitle(title);






        FloatingActionButton fab_return = (FloatingActionButton) findViewById(R.id.fab_return);
        fab_return.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                /*Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
                        .setAction("Action", null).show();*/

                Intent intent = new Intent();
                intent.setClass(MovieDetailActivity.this, MainActivity.class);
                startActivity(intent);
            }
        });

        //viewPager

        tabLayout = (TabLayout)findViewById(R.id.tabLayout);



        tabList =new ArrayList<Fragment>();
        tabList.add(new TabMovieDescription());
        tabList.add(new TabMovieOrder());
        tabList.add(new TabMovieComment());

        FragAdapter adapter = new FragAdapter(getSupportFragmentManager(), tabList);

        viewPager = (ViewPager)findViewById(R.id.viewpager);
        viewPager.setAdapter(adapter);



        titleList = new ArrayList<String>();
        titleList.add("描述");
        titleList.add("预定");
        titleList.add("评论");

        tabLayout.setTabMode(TabLayout.MODE_FIXED);//设置tab模式，当前为系统默认模式
        tabLayout.addTab(tabLayout.newTab().setText(titleList.get(0)));//添加tab选项卡
        tabLayout.addTab(tabLayout.newTab().setText(titleList.get(1)));//添加tab选项卡
        tabLayout.addTab(tabLayout.newTab().setText(titleList.get(2)));//添加tab选项卡

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

    @Override
    public void onDestroy() {
        bg.recycle();
//        bitmap.recycle();
        super.onDestroy();
    }



}
