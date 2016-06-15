package com.example.guanlu.sekko.viewConctroller;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.os.Parcelable;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentActivity;
import android.support.v4.view.PagerAdapter;
import android.support.v4.view.ViewPager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.ListView;


import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonArrayRequest;
import com.example.guanlu.sekko.R;
import com.example.guanlu.sekko.volley.VolleyManager;
import com.example.guanlu.sekko.adapter.CinemaListAdapter;
import com.example.guanlu.sekko.DTO.CinemaBean;
import com.example.guanlu.sekko.DTO.MovieBean;
import com.example.guanlu.sekko.VO.Cinema;
import com.example.guanlu.sekko.util.BitmapUtil;
import com.example.guanlu.sekko.volley.WebKey;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import org.json.JSONArray;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;


/**
 * Created by 关璐 on 2016/5/20.
 */
public class CinemaFragment extends Fragment {
    private View mParent;

    private FragmentActivity mActivity;

    private CinemaListAdapter cinemaListAdapter;

    private ListView cinemaList;
    private List<Cinema> data;

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

    public static final String jsonUrArrUrl = WebKey.HOST +WebKey.CINEMA;

    public static CinemaFragment newInstance(int index) {
        CinemaFragment f = new CinemaFragment();

        Bundle args = new Bundle();
        args.putInt("index", index);
        f.setArguments(args);

        return f;

    }

    private static final int UPDATE_LIST = 0;
    private static final int UPDATE_GALLERY = 1;

    private Handler mHandler = new Handler() {
        @Override
        public void handleMessage(Message msg) {
            switch (msg.what) {
                case UPDATE_LIST:
                    CinemaListAdapter cinemaListAdapter = new CinemaListAdapter(mActivity, data, cinemaList);
                    cinemaList.setAdapter(cinemaListAdapter);
                    break;
            }
            super.handleMessage(msg);
        }
    };

    private Thread newThread=new Thread(new Runnable() {
        @Override
        public void run() {


            data = new ArrayList<>();

            JsonArrayRequest jsonArrayRequest = new JsonArrayRequest(Request.Method.GET,
                    jsonUrArrUrl, "", new Response.Listener<JSONArray>(){

                @Override
                public void onResponse(JSONArray response) {
//                    System.out.println("json data===" + response);
                    try {
                        for (int i = 0; i < response.length(); i++) {
                            JSONObject jsonObject = response.getJSONObject(i);
                            Gson gson = new Gson();
                            //for test
//                            MovieBean movieBean = gson.fromJson(jsonObject.toString(),MovieBean.class);
                            CinemaBean cinemaBean = gson.fromJson(jsonObject.toString(),CinemaBean.class);
                            Cinema cinema = new Cinema(cinemaBean.getName(),cinemaBean.getLocation()
                                    ,cinemaBean.getTransport(),"--",cinemaBean.getTel(),cinemaBean.getCinemaId(),
                                    cinemaBean.getMovies());
                            System.out.println(cinema.getCinemaFilms());

                            data.add(cinema);
                        }
                    } catch (Exception e) {
                        System.out.println(e);
                    } finally {
                        cinemaListAdapter.notifyDataSetChanged();
                        System.out.println("over");
                        Message message = new Message();
                        message.what = UPDATE_LIST;
                        mHandler.sendMessage(message);

                    }
                }
            }, new Response.ErrorListener(){
                @Override
                public void onErrorResponse(VolleyError error) {
                    System.out.println("发生了一个错误！");
                    error.printStackTrace();

                    try {
                        List<String> jsonObjs = new ArrayList<>();
                        jsonObjs.add("{\"name\":\"花都太子电影城\",\"img\":\"http://pic.spider.com.cn/pic//filmpic/jdt/1435557280348.jpg\",\"location\":\"【花都区】广州市花都区狮岭镇田心路1号太子城6楼\",\"transport\":\"广州三元里广州至狮岭直达巴士，在太子广场下车；花都区新华至狮岭3路车，在太子广场下车\",\"movies\":\"爱丽丝梦游仙境2：镜中奇遇记,分歧者3：忠诚世界,北京遇上西雅图之不二情书,魔兽,X战警：天启\",\"url\":\"http://film.spider.com.cn/cinema-sp00144004701/\",\"cinemaId\":\"sp00144004701\",\"id\":1}");
                        jsonObjs.add("{\"name\":\"花都太子电影城\",\"img\":\"http://pic.spider.com.cn/pic//filmpic/jdt/1435557280348.jpg\",\"location\":\"【花都区】广州市花都区狮岭镇田心路1号太子城6楼\",\"transport\":\"广州三元里广州至狮岭直达巴士，在太子广场下车；花都区新华至狮岭3路车，在太子广场下车\",\"movies\":\"爱丽丝梦游仙境2：镜中奇遇记,分歧者3：忠诚世界,北京遇上西雅图之不二情书,魔兽,X战警：天启\",\"url\":\"http://film.spider.com.cn/cinema-sp00144004701/\",\"cinemaId\":\"sp00144004701\",\"id\":1}");
                        jsonObjs.add("{\"name\":\"花都太子电影城\",\"img\":\"http://pic.spider.com.cn/pic//filmpic/jdt/1435557280348.jpg\",\"location\":\"【花都区】广州市花都区狮岭镇田心路1号太子城6楼\",\"transport\":\"广州三元里广州至狮岭直达巴士，在太子广场下车；花都区新华至狮岭3路车，在太子广场下车\",\"movies\":\"爱丽丝梦游仙境2：镜中奇遇记,分歧者3：忠诚世界,北京遇上西雅图之不二情书,魔兽,X战警：天启\",\"url\":\"http://film.spider.com.cn/cinema-sp00144004701/\",\"cinemaId\":\"sp00144004701\",\"id\":1}");
                        for (int i = 0; i < jsonObjs.size(); i++) {
//                            JSONObject jsonObject = response.getJSONObject(i);
//                            System.out.println("json item===" + jsonObject);
                            Gson gson = new Gson();
//
                            CinemaBean cinemaBean = gson.fromJson(jsonObjs.get(i),CinemaBean.class);

                            Cinema cinema = new Cinema(cinemaBean.getName(),cinemaBean.getLocation()
                                    ,cinemaBean.getTransport(),"--",cinemaBean.getTel(),cinemaBean.getId(),
                                    cinemaBean.getMovies());
                            data.add(cinema);
                        }
                    } catch (Exception e) {
                        System.out.println(e);
                    } finally {

                    }
                }
            });
            VolleyManager.getRequestQueue().add(jsonArrayRequest);
        }
    });


    public int getShownIndex() {
        return getArguments().getInt("index",0);
    }

    @Override
    public View onCreateView(LayoutInflater inflater,ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.cinema_fragment, container,false);

        cinemaList=(ListView)view.findViewById(R.id.cinema_list);

        viewPager = (ViewPager)view.findViewById(R.id.cinema_ad_viewpager);



        return view;
    }

    @Override
    public void onActivityCreated(Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        mParent=getView();
        mActivity = getActivity();

        //cinemaList



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

        data = new ArrayList<>();
        data.clear();


        newThread.start();

        cinemaListAdapter = new CinemaListAdapter(mActivity, data, cinemaList);
        cinemaList.setAdapter(cinemaListAdapter);

        ImageButton refresh = (ImageButton)mParent.findViewById(R.id.cinema_refresh);
        refresh.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                CinemaListAdapter cinemaListAdapter = new CinemaListAdapter(mActivity, data, cinemaList);
                cinemaList.setAdapter(cinemaListAdapter);
            }
        });

        //cinemaList.addHeaderView(header,null,false);

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
