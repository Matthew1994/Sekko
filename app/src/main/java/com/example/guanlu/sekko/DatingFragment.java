package com.example.guanlu.sekko;
import android.os.Bundle;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentActivity;
import android.support.v4.app.FragmentManager;
import android.support.v4.view.ViewPager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.guanlu.sekko.adapter.FragAdapter;

import java.util.ArrayList;
import java.util.List;


/**
 * Created by 关璐 on 2016/5/20.
 */
public class DatingFragment extends Fragment {
    private View mParent;

    private FragmentActivity mActivity;
    private View view;

    private TabLayout tabLayout;
    private ViewPager viewPager;
    private List<Fragment> fragList;
    private List<String> titleList;

    public static DatingFragment newInstance(int index) {
        DatingFragment f = new DatingFragment();

        Bundle args = new Bundle();
        args.putInt("index", index);
        f.setArguments(args);

        return f;

    }

    public int getShownIndex() {
        return getArguments().getInt("index",0);
    }

    @Override
    public View onCreateView(LayoutInflater inflater,ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.dating_fragment, container,false);
        return view;
    }

    @Override
    public void onActivityCreated(Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        mParent= getView();
        mActivity = getActivity();
        tabLayout = (TabLayout)mParent.findViewById(R.id.dating_tabLayout);
        viewPager = (ViewPager)mParent.findViewById(R.id.dating_viewpager);

        FragmentManager fragmentManager = mActivity.getSupportFragmentManager();

        fragList =new ArrayList<Fragment>();
        fragList.add(new DatingListFragment());
        fragList.add(new DatingList2Fragment());

        titleList = new ArrayList<String>();
        titleList.add("邀约墙");
        titleList.add("照片墙");


        FragAdapter adapter = new FragAdapter(fragmentManager, fragList, titleList);

        viewPager.setAdapter(adapter);


        tabLayout.setTabMode(TabLayout.MODE_FIXED);//设置tab模式，当前为系统默认模式
        tabLayout.addTab(tabLayout.newTab().setText(titleList.get(0)));//添加tab选项卡
        tabLayout.addTab(tabLayout.newTab().setText(titleList.get(1)));//添加tab选项卡

        tabLayout.setupWithViewPager(viewPager);//将TabLayout和ViewPager关联起来。
        tabLayout.setTabsFromPagerAdapter(adapter);//给Tabs设置适配器

    }

    @Override
    public void onHiddenChanged(boolean hidden) {
        super.onHiddenChanged(hidden);
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
    }
}
