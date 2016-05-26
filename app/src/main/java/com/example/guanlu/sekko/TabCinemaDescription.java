package com.example.guanlu.sekko;


import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import com.baidu.mapapi.BMapManager;
import android.support.v7.widget.CardView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.baidu.mapapi.map.MapView;
import com.baidu.mapapi.model.inner.GeoPoint;


/**
 * A simple {@link Fragment} subclass.
 */
public class TabCinemaDescription extends Fragment {

    private TextView feature;
    private TextView address;
    private TextView route;
    private TextView tel;

    // 百度地图控件
    private BMapManager mapManager;
    private MapView mMapView;

    private com.getbase.floatingactionbutton.FloatingActionButton call,locate;


    public TabCinemaDescription() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.cinema_description_tab, container, false);

        feature =(TextView)view.findViewById(R.id.cinema_feature);
        address =(TextView)view.findViewById(R.id.cinema_address);
        route =(TextView)view.findViewById(R.id.cinema_route);
        tel =(TextView)view.findViewById(R.id.cinema_tel);

        call=(com.getbase.floatingactionbutton.FloatingActionButton)view.findViewById(R.id.menu_call);
        locate=(com.getbase.floatingactionbutton.FloatingActionButton)view.findViewById(R.id.menu_locate);

        feature.setText("影院特色：  所有影厅的豪华座椅均具超宽排距，给您无比宽松舒适的观映环境。");
        address.setText("详细地址：  天河区 广州市天河路623号天娱广场西塔5楼天河电影城");
        route.setText("交通信息： 地铁3号线岗顶站A出口，岗顶BRT站");
        tel.setText("影院电话： 87576722");

        //call the cinema
        call.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String tel = "87576722";
                Intent intent = new Intent(Intent.ACTION_CALL, Uri.parse("tel://" + tel));
                startActivity(intent);
            }
        });


       /* //map
        mMapView = (MapView)view.findViewById(R.id.map_view);
        mMapView.onResume();
*/
        return view;
    }

    @Override
    public void onDestroy() {
        /*mMapView.onDestroy();
        mMapView = null;*/
        super.onDestroy();
    }
/*
    private void initMap() {

        // 初始化MapActivity
        mapManager = new BMapManager();
        // init方法的第一个参数需填入申请的API Key
        mapManager.init();
        // 实例化搜索地址类
        mMKSearch = new MKSearch();
        // 初始化搜索地址实例
        mMKSearch.init(mapManager, new MySearchListener());
        mLocationManager = mapManager.getLocationManager();
        // 注册位置更新事件
        mLocationManager.requestLocationUpdates(this);
        // 使用GPS定位
        mLocationManager
                .enableProvider((int) MKLocationManager.MK_GPS_PROVIDER);
    }

    @Override
    protected void onResume() {
        if (mapManager != null) {
            mapManager.start();
        }
        super.onResume();

    }*/
}
