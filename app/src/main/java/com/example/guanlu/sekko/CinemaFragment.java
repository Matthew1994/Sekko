package com.example.guanlu.sekko;
import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Bundle;
import android.os.Handler;
import android.os.Parcelable;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentActivity;
import android.support.v4.view.PagerAdapter;
import android.support.v4.view.ViewPager;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.WindowManager;
import android.widget.AdapterView;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;


import com.example.guanlu.sekko.util.BitmapUtil;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;


/**
 * Created by 关璐 on 2016/5/20.
 */
public class CinemaFragment extends Fragment {
    private View mParent;

    private FragmentActivity mActivity;

    private ListView cinemaList;
    private List<Map<String,Object>> data;

    //ids
    private ViewPager viewPager;
    private List<ImageView> imageViews;
    private List<Bitmap> bitmaps = new ArrayList<Bitmap>();
    private List<View> dots;

    private int currentItem = 0;

    private View header;
    //pic cut

    private static int Width;
    private static int Height;


    // An ExecutorService that can schedule commands to run after a given delay,
    // or to execute periodically.
    private ScheduledExecutorService scheduledExecutorService;

    private Handler handler = new Handler() {
        public void handleMessage(android.os.Message msg) {
            viewPager.setCurrentItem(currentItem);// 切换当前显示的图片
        };
    };

    public static CinemaFragment newInstance(int index) {
        CinemaFragment f = new CinemaFragment();

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
        View view = inflater.inflate(R.layout.cinema_fragment, container,false);
        return view;
    }

    @Override
    public void onActivityCreated(Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        mParent=getView();
        mActivity = getActivity();

        //cinemaList

        cinemaList=(ListView)mParent.findViewById(R.id.cinema_list);

        viewPager = (ViewPager)mParent.findViewById(R.id.cinema_ad_viewpager);

        data = getData();

        MyCinemaAdapter myCinemaAdapter = new MyCinemaAdapter(mActivity);

        cinemaList.setAdapter(myCinemaAdapter);

      /*  LayoutInflater layoutInflater = LayoutInflater.from(getContext());
        header = layoutInflater.inflate(R.layout.cinema_list_header,cinemaList,false);
        viewPager = (ViewPager)header.findViewById(R.id.cinema_ad_viewpager);*/


        //ids


        //pic cut
        WindowManager wm = mActivity.getWindowManager();
        Width = wm.getDefaultDisplay().getWidth();
        Height = wm.getDefaultDisplay().getHeight();


        Bitmap bitmap1 = BitmapFactory.decodeResource(mActivity.getResources(),R.drawable.ad1);
        Bitmap bitmap2 = BitmapFactory.decodeResource(mActivity.getResources(),R.drawable.ad2);

        BitmapUtil.big(bitmap2,Width,(int)(Height*0.3));

        bitmaps.add(BitmapUtil.big(bitmap1,Width+10,(int)((Width+10)*0.45)));
        bitmaps.add(BitmapUtil.big(bitmap2,Width+10,(int)((Width+10)*0.45)));

        bitmap1.recycle();
        bitmap2.recycle();


        imageViews = new ArrayList<ImageView>();

        for (int i = 0; i < bitmaps.size(); i++) {
            ImageView imageView = new ImageView(mActivity);
            imageView.setImageBitmap(bitmaps.get(i));
            imageViews.add(imageView);
        }

//        viewPager = (ViewPager)mParent.findViewById(R.id.cinema_ad_viewpager);
        viewPager.setAdapter(new MyAdapter());// 设置填充ViewPager页面的适配器
        // 设置一个监听器，当ViewPager中的页面改变时调用
        viewPager.setOnPageChangeListener(new MyPageChangeListener());

        //cinemaList.addHeaderView(header,null,false);

    }

    private List<Map<String,Object>> getData() {
        List<Map<String,Object>> list = new ArrayList<Map<String,Object>>();
        Map<String,Object> map;

        for(int i=0; i<1;i++) {
            map = new HashMap<String,Object>();
            map.put("name","广州天河电影城");
            map.put("address","地址：【天河区】广州市天河路623号天娱广场西塔5楼天河电影城 ");
            map.put("route","路线：地铁3号线岗顶站A出口，岗顶BRT站");
            list.add(map);
        }
        for(int i=0; i<1;i++) {
            map = new HashMap<String,Object>();
            map.put("name","广州IDC星梦影城");
            map.put("address","地址：【番禺区】广州市番禺区番禺大道北383号海印又一城4楼 ");
            map.put("route","路线：地铁：汉溪长隆 ; 公交：地铁接驳9、303、987");
            list.add(map);
        }
        for(int i=0; i<1;i++) {
            map = new HashMap<String,Object>();
            map.put("name","华影佳永国际影城");
            map.put("address","地址：【海珠区】广州海珠区新滘西路西与江南大道南交汇689-709号（单号）枫瀛汇二楼（即荣煦商贸忠心");
            map.put("route","路线：公交：761路、469路、583路到新滘西路西站下，地铁：东晓南C1出口，坐便民车到荣煦中心站下。");
            list.add(map);
        }
        for(int i=0; i<3;i++) {
            map = new HashMap<String,Object>();
            map.put("name","广州IDC星梦影城");
            map.put("address","地址：【番禺区】广州市番禺区番禺大道北383号海印又一城4楼 ");
            map.put("route","路线：地铁：汉溪长隆 ; 公交：地铁接驳9、303、987");
            list.add(map);
        }
        return list;
    }

    static class ViewHolder {
        public TextView cinemaName;
        public TextView cinemaAddress;
        public TextView cinemaRoute;
    }


    public class MyCinemaAdapter extends BaseAdapter {
        private LayoutInflater mInflater = null;

        private MyCinemaAdapter(Context context) {
            this.mInflater = LayoutInflater.from(context);
        }

        @Override
        public int getCount() {
            return data.size();
        }

        @Override
        public Object getItem(int position) {
            return position;
        }
        @Override
        public long getItemId(int position) {
            return position;
        }

        @Override
        public View getView(int position, View convertView,ViewGroup parent) {
            ViewHolder holder = null;

            if(convertView ==null) {
                holder = new ViewHolder();
                convertView = mInflater.inflate(R.layout.cinema_list_item,null);
                holder.cinemaName=(TextView)convertView.findViewById(R.id.cinema_item_name);
                holder.cinemaAddress=(TextView)convertView.findViewById(R.id.cinema_item_address);
                holder.cinemaRoute=(TextView)convertView.findViewById(R.id.cinema_item_route);

                convertView.setTag(holder);
            } else  {
                holder = (ViewHolder)convertView.getTag();
            }
            holder.cinemaName.setText((String)data.get(position).get("name"));
            holder.cinemaAddress.setText((String)data.get(position).get("address"));
            holder.cinemaRoute.setText((String)data.get(position).get("route"));

            //click
            cinemaList.setOnItemClickListener(new AdapterView.OnItemClickListener() {
                @Override
                public void onItemClick(AdapterView<?> parent, View view, int position, long id) {

                    Log.d("----","click");
                    Intent intent = new Intent();
                    // intent.setClass(getActivity(), DetailsActivity.class);
                    intent.setClass(getActivity(), CinemaDetailActivity.class);
                    Bundle bundle = new Bundle();
                    bundle.putString("name", (String)data.get(position).get("name"));
                    bundle.putString("address", (String)data.get(position).get("address"));
                    bundle.putString("route", (String)data.get(position).get("route"));
                    intent.putExtras(bundle);
                    //startActivityForResult(intent, 1);
                    startActivity(intent);
                }
            });
            return convertView;
        }
    }
    @Override
    public void onHiddenChanged(boolean hidden) {
        super.onHiddenChanged(hidden);
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
    }

    @Override
    public void onStart() {
        scheduledExecutorService = Executors.newSingleThreadScheduledExecutor();
        // 当Activity显示出来后，每两秒钟切换一次图片显示
        scheduledExecutorService.scheduleAtFixedRate(new ScrollTask(), 1, 5, TimeUnit.SECONDS);
        super.onStart();
    }

    @Override
    public void onStop() {
        // 当Activity不可见的时候停止切换
        scheduledExecutorService.shutdown();
        super.onStop();
    }

    private class ScrollTask implements Runnable {

        public void run() {
            synchronized (viewPager) {
                //System.out.println("currentItem: " + currentItem);
                currentItem = (currentItem + 1) % imageViews.size();
                handler.obtainMessage().sendToTarget(); // 通过Handler切换图片
            }
        }

    }

    private class MyPageChangeListener implements ViewPager.OnPageChangeListener {
        private int oldPosition = 0;

        /**
         * This method will be invoked when a new page becomes selected.
         * position: Position index of the new selected page.
         */
        public void onPageSelected(int position) {
            currentItem = position;
           /* tv_title.setText(titles[position]);
            dots.get(oldPosition).setBackgroundResource(R.drawable.dot_normal);
            dots.get(position).setBackgroundResource(R.drawable.dot_focused);*/
            oldPosition = position;
        }

        public void onPageScrollStateChanged(int arg0) {

        }

        public void onPageScrolled(int arg0, float arg1, int arg2) {

        }
    }

    /**
     * 填充ViewPager页面的适配器
     *
     * @author Administrator
     *
     */
    private class MyAdapter extends PagerAdapter {

        @Override
        public int getCount() {
            return bitmaps.size();
        }

        @Override
        public Object instantiateItem(View arg0, int arg1) {
            ((ViewPager) arg0).addView(imageViews.get(arg1));
            return imageViews.get(arg1);
        }

        @Override
        public void destroyItem(View arg0, int arg1, Object arg2) {
            ((ViewPager) arg0).removeView((View) arg2);
        }

        @Override
        public boolean isViewFromObject(View arg0, Object arg1) {
            return arg0 == arg1;
        }

        @Override
        public void restoreState(Parcelable arg0, ClassLoader arg1) {

        }

        @Override
        public Parcelable saveState() {
            return null;
        }

        @Override
        public void startUpdate(View arg0) {

        }

        @Override
        public void finishUpdate(View arg0) {

        }
    }

}
