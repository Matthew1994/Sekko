package com.example.guanlu.sekko.viewConctroller;


import android.graphics.Color;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.support.design.widget.AppBarLayout;
import android.support.v4.app.Fragment;
import android.text.SpannableStringBuilder;
import android.text.style.ForegroundColorSpan;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.DecelerateInterpolator;
import android.view.animation.TranslateAnimation;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonArrayRequest;
import com.example.guanlu.sekko.DTO.MovieBean;
import com.example.guanlu.sekko.DTO.ScheduleBean;
import com.example.guanlu.sekko.R;
import com.example.guanlu.sekko.VO.Schedule;
import com.example.guanlu.sekko.adapter.ScheduleAdapter;
import com.example.guanlu.sekko.adapter.ScheduleGalleryAdapter;
import com.example.guanlu.sekko.volley.VolleyManager;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import org.json.JSONArray;
import org.json.JSONObject;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import me.next.tagview.TagCloudView;


/**
 * A simple {@link Fragment} subclass.
 */
public class TabMovieOrder extends Fragment {

    private View view;
    private Spinner spinner;

    private List<Button> buttons;
    private ImageView buttonBg;

    //get
    private String paramDate;
    private String paramMovieId;
    private String paramCinemaId;

    //div
    private LinearLayout div;
    private List<String> labels;
    private List<TextView> tvs;

    //schedule
    private List<Schedule> scheduleList = new ArrayList<>();
    private ListView schedule;
    public  String jsonUrArrUrl = "http://172.19.48.32:8001/api/movie";



    //update
    private static final int UPDATE_SCHEDULE = 0;
    private static final int UPDATE_GALLERY = 1;

    public TabMovieOrder() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(final LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        view = inflater.inflate(R.layout.movie_order_tab, container, false);

        spinner =(Spinner)view.findViewById(R.id.area_spinner);
        String[] mItems = getResources().getStringArray(R.array.area);

        ArrayAdapter<String> adapter=new ArrayAdapter<String>(getActivity(),R.layout.spinner_item);

        for (int i = 0; i < mItems.length; i++) {
            adapter.add( mItems[i]);

        }

    //绑定 Adapter到控件
        spinner .setAdapter(adapter);
        spinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view,
                                       int pos, long id) {

                String[] areas = getResources().getStringArray(R.array.area);
                Toast.makeText(getContext(), "你点击的是:"+ areas[pos], Toast.LENGTH_SHORT).show();
            }
            @Override
            public void onNothingSelected(AdapterView<?> parent) {
                // Another interface callback
            }
        });


        //button
        buttons = new ArrayList<Button>();
        Button button1=(Button)view.findViewById(R.id.btn1);
        buttons.add(button1);

        Button button2=(Button)view.findViewById(R.id.btn2);
        buttons.add(button2);

        Button button3=(Button)view.findViewById(R.id.btn3);
        buttons.add(button3);

        button2.setOnClickListener(orderDateListener);
        button1.setOnClickListener(orderDateListener);
        button3.setOnClickListener(orderDateListener);

        Date date = new Date();
        SimpleDateFormat df=new SimpleDateFormat("MM-dd");
        button1.setText(df.format(date).toString());
        paramDate = df.format(date).toString();
        button2.setText(df.format(new Date(date.getTime() + (long)1 * 24 * 60 * 60 * 1000)));
        button3.setText(df.format(new Date(date.getTime() + (long)2 * 24 * 60 * 60 * 1000)));

        buttonBg = (ImageView)view.findViewById(R.id.btn_bg);


        //init labels
        labels = new ArrayList<>();
        labels.add("大地影院广州白云新市天地店");
        labels.add("广州庆丰横店电影城");
        labels.add("橙天嘉禾广州影院");
        labels.add("大地影院广州白云新市天地店");
        labels.add("大学城金逸影院");

        //add label
        div = (LinearLayout)view.findViewById(R.id.div_label);

        LinearLayout.LayoutParams lp = new LinearLayout.LayoutParams(LinearLayout.LayoutParams.WRAP_CONTENT,
                LinearLayout.LayoutParams.WRAP_CONTENT);

        lp.setMargins(10,0,10,0);

        tvs = new ArrayList<>();
        for (int i = 0; i < labels.size(); i++) {
            TextView tx = new TextView(getContext());

            tx.setBackgroundResource(R.drawable.shape_label);
            tx.setText(labels.get(i));

            tx.setTextSize(12);
            tx.setTextColor(getResources().getColor(R.color.content_gray));
            tx.setLayoutParams(lp);
            tx.setPadding(15,10,15,10);

            tx.setClickable(true);
            div.addView(tx);
            tvs.add(tx);
    }
        for (TextView tv : tvs) {
            tv.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    initLabel();
                    v.setBackgroundResource(R.drawable.shape_label_dark);
                    TextView tv =(TextView)v;
                    tv.setTextColor(0xccffffff);
                }
            });
        }

        //schedule
        schedule = (ListView)view.findViewById(R.id.movie_schedule2);
        schedule.setMinimumHeight(500);

        getSchedule();

        return view;
    }

    private void initLabel() {
        for (TextView tv : tvs) {
            tv.setBackgroundResource(R.drawable.shape_label);
            tv.setTextColor(getResources().getColor(R.color.content_gray));

        }
    }
    private int lastButton = 0;


    private View.OnClickListener orderDateListener = new View.OnClickListener() {
        @Override
        public void onClick(View v) {

            int pace = buttons.indexOf(v) - lastButton;

            TranslateAnimation animation = null;
            animation = new TranslateAnimation(lastButton*buttonBg.getWidth(),(lastButton+pace)*buttonBg.getWidth(), buttonBg.getTop(),
                    buttonBg.getTop());

            animation.setDuration(400);
            animation.setFillAfter(true);
            animation.setInterpolator(new DecelerateInterpolator());
            buttonBg.startAnimation(animation);

            lastButton = buttons.indexOf(v);


            for(Button i:buttons) {
                i.setTextColor(0xFFdedede);
            }
            buttons.get(buttons.indexOf(v)).setTextColor(0xFFFFFFFF);
            Date date = new Date();
            SimpleDateFormat df=new SimpleDateFormat("MM-dd");

            paramDate = df.format(new Date(date.getTime() + (long)buttons.indexOf(v)* 24 * 60 * 60 * 1000));

//            String strings[] = label.getText().toString().split(" ");

//            Toast.makeText(getContext(),paramDate + "  "+strings[0],Toast.LENGTH_SHORT).show();

//            getSchedule();

        }
    };


    public void getSchedule() {
        new Thread(scheduleRunnable).start();
    }

    //for test
    public int test = 1;


    private Runnable scheduleRunnable =new Runnable() {
        /**
         *
         */
        @Override
        public void run() {

            scheduleList = new ArrayList<>();

            jsonUrArrUrl += "？";
            jsonUrArrUrl += "cinemaId=";
            jsonUrArrUrl += paramCinemaId;
            jsonUrArrUrl += "&";
            jsonUrArrUrl  = jsonUrArrUrl + "movieId=" +paramMovieId + "&";
            jsonUrArrUrl  = jsonUrArrUrl + "date=" + paramDate;

//            Toast.makeText(getContext(),jsonUrArrUrl,Toast.LENGTH_SHORT).show();

            JsonArrayRequest jsonArrayRequest = new JsonArrayRequest(Request.Method.GET,
                    jsonUrArrUrl, "", new Response.Listener<JSONArray>(){

                @Override
                public void onResponse(JSONArray response) {
//                    System.out.println("json data===" + response);
                    try {
                        for (int i = 0; i < response.length(); i++) {
                            JSONObject jsonObject = response.getJSONObject(i);
                            System.out.println("json item===" + jsonObject);
                            Gson gson = new Gson();
                            java.lang.reflect.Type type = new TypeToken<MovieBean>() {}.getType();
                            //for test
                            ScheduleBean scheduleBean = gson.fromJson(jsonObject.toString(),ScheduleBean.class);
//                            MovieBean movieBean = gson.fromJson(jsonTemp,MovieBean.class);
                            Schedule schedule= new Schedule(scheduleBean.getTime(),scheduleBean.getDate(),
                                    scheduleBean.getPlayingRoom(),scheduleBean.getLanguageAndEffect(),scheduleBean.getPrice() );
                            scheduleList.add(schedule);
                        }
                    } catch (Exception e) {
                        System.out.println(e);
                    }
                }
            }, new Response.ErrorListener(){
                @Override
                public void onErrorResponse(VolleyError error) {
                    System.out.println("发生了一个错误！");
                    error.printStackTrace();

                    try {
                        List<String> jsonObjs = new ArrayList<>();
                        jsonObjs.add("{\"id\":1,\"timesId\":\"times1\",\"date\":\"2016-06-05\",\"cinemaId\":\"aaa\",\"movieId\":\"bbb\",\"time\":\"23:40\",\"languageAndEffect\":\"英语3D\",\"playingRoom\":\"3号厅\",\"price\":\"100\"}");
                        jsonObjs.add("{\"id\":1,\"timesId\":\"times1\",\"date\":\"2016-06-05\",\"cinemaId\":\"aaa\",\"movieId\":\"bbb\",\"time\":\"23:40\",\"languageAndEffect\":\"英语3D\",\"playingRoom\":\"3号厅\",\"price\":\"100\"}");
                        jsonObjs.add("{\"id\":1,\"timesId\":\"times1\",\"date\":\"2016-06-05\",\"cinemaId\":\"aaa\",\"movieId\":\"bbb\",\"time\":\"23:40\",\"languageAndEffect\":\"英语3D\",\"playingRoom\":\"3号厅\",\"price\":\"100\"}");
                        jsonObjs.add("{\"id\":1,\"timesId\":\"times1\",\"date\":\"2016-06-05\",\"cinemaId\":\"aaa\",\"movieId\":\"bbb\",\"time\":\"23:40\",\"languageAndEffect\":\"英语3D\",\"playingRoom\":\"3号厅\",\"price\":\"100\"}");
                        jsonObjs.add("{\"id\":1,\"timesId\":\"times1\",\"date\":\"2016-06-05\",\"cinemaId\":\"aaa\",\"movieId\":\"bbb\",\"time\":\"23:40\",\"languageAndEffect\":\"英语3D\",\"playingRoom\":\"3号厅\",\"price\":\"100\"}");
                        for (int i = 0; i < jsonObjs.size(); i++) {
                            Gson gson = new Gson();
                            ScheduleBean scheduleBean = gson.fromJson(jsonObjs.get(i),ScheduleBean.class);
                            Schedule schedule= new Schedule(scheduleBean.getTime(),scheduleBean.getDate() + Integer.toString(test),
                                    scheduleBean.getPlayingRoom(),scheduleBean.getLanguageAndEffect(),scheduleBean.getPrice() );
                            scheduleList.add(schedule);
                        }
                    } catch (Exception e) {
                        System.out.println(e);
                    } finally {
                        Message message = new Message();
                        message.what = UPDATE_SCHEDULE;
                        mHandler.sendMessage(message);
                        test++;
                    }
                }
            });
            VolleyManager.getRequestQueue().add(jsonArrayRequest);
        }
    };


    private Runnable cinemaListRunnable =new Runnable() {
        /**
         *
         */
        @Override
        public void run() {

            scheduleList = new ArrayList<>();

            jsonUrArrUrl += "？";
            jsonUrArrUrl += "cinemaId=";
            jsonUrArrUrl += paramCinemaId;
            jsonUrArrUrl += "&";
            jsonUrArrUrl  = jsonUrArrUrl + "movieId=" +paramMovieId + "&";
            jsonUrArrUrl  = jsonUrArrUrl + "date=" + paramDate;

//            Toast.makeText(getContext(),jsonUrArrUrl,Toast.LENGTH_SHORT).show();

            JsonArrayRequest jsonArrayRequest = new JsonArrayRequest(Request.Method.GET,
                    jsonUrArrUrl, "", new Response.Listener<JSONArray>(){

                @Override
                public void onResponse(JSONArray response) {
//                    System.out.println("json data===" + response);
                    try {
                        for (int i = 0; i < response.length(); i++) {
                            JSONObject jsonObject = response.getJSONObject(i);
                            System.out.println("json item===" + jsonObject);
                            Gson gson = new Gson();
                            java.lang.reflect.Type type = new TypeToken<MovieBean>() {}.getType();
                            //for test
                            ScheduleBean scheduleBean = gson.fromJson(jsonObject.toString(),ScheduleBean.class);
//                            MovieBean movieBean = gson.fromJson(jsonTemp,MovieBean.class);
                            Schedule schedule= new Schedule(scheduleBean.getTime(),scheduleBean.getDate(),
                                    scheduleBean.getPlayingRoom(),scheduleBean.getLanguageAndEffect(),scheduleBean.getPrice() );
                            scheduleList.add(schedule);
                        }
                    } catch (Exception e) {
                        System.out.println(e);
                    }
                }
            }, new Response.ErrorListener(){
                @Override
                public void onErrorResponse(VolleyError error) {
                    System.out.println("发生了一个错误！");
                    error.printStackTrace();

                    try {
                        List<String> jsonObjs = new ArrayList<>();
                        jsonObjs.add("{\"id\":1,\"timesId\":\"times1\",\"date\":\"2016-06-05\",\"cinemaId\":\"aaa\",\"movieId\":\"bbb\",\"time\":\"23:40\",\"languageAndEffect\":\"英语3D\",\"playingRoom\":\"3号厅\",\"price\":\"100\"}");
                        jsonObjs.add("{\"id\":1,\"timesId\":\"times1\",\"date\":\"2016-06-05\",\"cinemaId\":\"aaa\",\"movieId\":\"bbb\",\"time\":\"23:40\",\"languageAndEffect\":\"英语3D\",\"playingRoom\":\"3号厅\",\"price\":\"100\"}");
                        jsonObjs.add("{\"id\":1,\"timesId\":\"times1\",\"date\":\"2016-06-05\",\"cinemaId\":\"aaa\",\"movieId\":\"bbb\",\"time\":\"23:40\",\"languageAndEffect\":\"英语3D\",\"playingRoom\":\"3号厅\",\"price\":\"100\"}");
                        jsonObjs.add("{\"id\":1,\"timesId\":\"times1\",\"date\":\"2016-06-05\",\"cinemaId\":\"aaa\",\"movieId\":\"bbb\",\"time\":\"23:40\",\"languageAndEffect\":\"英语3D\",\"playingRoom\":\"3号厅\",\"price\":\"100\"}");
                        jsonObjs.add("{\"id\":1,\"timesId\":\"times1\",\"date\":\"2016-06-05\",\"cinemaId\":\"aaa\",\"movieId\":\"bbb\",\"time\":\"23:40\",\"languageAndEffect\":\"英语3D\",\"playingRoom\":\"3号厅\",\"price\":\"100\"}");
                        for (int i = 0; i < jsonObjs.size(); i++) {
                            Gson gson = new Gson();
                            ScheduleBean scheduleBean = gson.fromJson(jsonObjs.get(i),ScheduleBean.class);
                            Schedule schedule= new Schedule(scheduleBean.getTime(),scheduleBean.getDate() + Integer.toString(test),
                                    scheduleBean.getPlayingRoom(),scheduleBean.getLanguageAndEffect(),scheduleBean.getPrice() );
                            scheduleList.add(schedule);
                        }
                    } catch (Exception e) {
                        System.out.println(e);
                    } finally {
                        Message message = new Message();
                        message.what = UPDATE_SCHEDULE;
                        mHandler.sendMessage(message);
                        test++;
                    }
                }
            });
            VolleyManager.getRequestQueue().add(jsonArrayRequest);
        }
    };

    private Handler mHandler = new Handler(){
        @Override
        public void handleMessage(Message msg) {
            switch (msg.what) {
                case UPDATE_SCHEDULE:
                    ScheduleAdapter scheduleAdapter = new ScheduleAdapter(getContext(), scheduleList);
                    schedule.setAdapter(scheduleAdapter);
                    break;
            }
            super.handleMessage(msg);
        }

    };

}
