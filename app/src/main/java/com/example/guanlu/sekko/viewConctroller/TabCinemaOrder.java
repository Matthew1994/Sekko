package com.example.guanlu.sekko.viewConctroller;


import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.DecelerateInterpolator;
import android.view.animation.TranslateAnimation;
import android.widget.AdapterView;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.Gallery;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonArrayRequest;
import com.example.guanlu.sekko.DTO.CinemaBean;
import com.example.guanlu.sekko.DTO.MovieBean;
import com.example.guanlu.sekko.DTO.MovieBriefBean;
import com.example.guanlu.sekko.DTO.ScheduleBean;
import com.example.guanlu.sekko.R;
import com.example.guanlu.sekko.VO.Cinema;
import com.example.guanlu.sekko.VO.Movie;
import com.example.guanlu.sekko.VO.MovieBrief;
import com.example.guanlu.sekko.VO.Schedule;
import com.example.guanlu.sekko.adapter.GalleryAdapter;
import com.example.guanlu.sekko.adapter.MovieListAdapter;
import com.example.guanlu.sekko.adapter.ScheduleAdapter;
import com.example.guanlu.sekko.adapter.ScheduleGalleryAdapter;
import com.example.guanlu.sekko.util.BitmapUtil;
import com.example.guanlu.sekko.volley.VolleyManager;
import com.example.guanlu.sekko.volley.WebKey;
import com.google.gson.Gson;
import com.google.gson.JsonArray;
import com.google.gson.JsonObject;
import com.google.gson.reflect.TypeToken;

import org.json.JSONArray;
import org.json.JSONObject;

import java.lang.reflect.Type;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.TimeZone;


/**
 * A simple {@link Fragment} subclass.
 */
public class TabCinemaOrder extends Fragment {


    private List<MovieBrief> movieList = new ArrayList<>();

    private List<Schedule> scheduleList = new ArrayList<>();

    private GalleryFlow galleryFlow;

    private List<Button> buttons;

    private ListView schedule;

    private List<Schedule> data;

    private ImageView buttonBg;

    private TextView label;
    private TextView movieId;
    //get
    private String paramDate;
    private String paramMovieId;
    private String paramCinemaId;




    public TabCinemaOrder() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view =  inflater.inflate(R.layout.cinema_order_tab, container, false);

        //gallery

        galleryFlow = (GalleryFlow)view.findViewById(R.id.order_gallery);
        label = (TextView)view.findViewById(R.id.select_move_name);
        movieId = (TextView)view.findViewById(R.id.select_move_id);

        newThread.start();

        //button
        buttons = new ArrayList<Button>();
        Button button1=(Button)view.findViewById(R.id.button1);
        buttons.add(button1);

        Button button2=(Button)view.findViewById(R.id.button2);
        buttons.add(button2);

        Button button3=(Button)view.findViewById(R.id.button3);
        buttons.add(button3);

        button2.setOnClickListener(orderDateListener);
        button1.setOnClickListener(orderDateListener);
        button3.setOnClickListener(orderDateListener);

        Date date = new Date();
        System.out.println("----date---"+ date);
        SimpleDateFormat df=new SimpleDateFormat("MM-dd");

        button1.setText(df.format(new Date(date.getTime() +(long)1 * 12 * 60 * 60 * 1000)));
        paramDate = df.format(date).toString();
        button2.setText(df.format(new Date(date.getTime() + (long)1 * 24 * 60 * 60 * 1000 +(long)1 * 12 * 60 * 60 * 1000)));
        button3.setText(df.format(new Date(date.getTime() + (long)2 * 24 * 60 * 60 * 1000+(long)1 * 12 * 60 * 60 * 1000)));

        buttonBg = (ImageView)view.findViewById(R.id.button_bg);


        //schedule
        schedule = (ListView)view.findViewById(R.id.movie_schedule);


        //cinemaId
        paramCinemaId = CinemaDetailActivity.myCinema.getCinemaId();

        getSchedule();

        return view;
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

            String strings[] = label.getText().toString().split(" ");

            Toast.makeText(getContext(),paramDate + "  "+strings[0],Toast.LENGTH_SHORT).show();

            getSchedule();

        }
    };


    private static final int UPDATE_SCHEDULE = 0;
    private static final int UPDATE_GALLERY = 1;
    private static final int UPDATE_GALLERY2 = 2;

    private Handler mHandler = new Handler(){
        @Override
        public void handleMessage(Message msg) {
            switch (msg.what) {
                case UPDATE_GALLERY:
                    ScheduleGalleryAdapter galleryAdapter = new ScheduleGalleryAdapter(getActivity(),movieList,galleryFlow,label,movieId);
                    galleryFlow.setAdapter(galleryAdapter);
                    label.setText(movieList.get(0).getMovieName()+"  ");

                    Message message = new Message();
                    message.what = UPDATE_GALLERY2;
                    mHandler.sendMessage(message);
                    break;

                case UPDATE_GALLERY2:
                    galleryAdapter = new ScheduleGalleryAdapter(getActivity(),movieList,galleryFlow,label,movieId);
                    galleryFlow.setAdapter(galleryAdapter);
                    label.setText(movieList.get(0).getMovieName()+"  ");

                case UPDATE_SCHEDULE:
                    ScheduleAdapter scheduleAdapter = new ScheduleAdapter(getContext(), scheduleList);
                    schedule.setAdapter(scheduleAdapter);
                    break;
            }
            super.handleMessage(msg);
        }

    };



    public void getSchedule() {
        new Thread(scheduleRunnable).start();
    }




    private Thread newThread=new Thread(new Runnable() {
        @Override
        public void run() {

            movieList = new ArrayList<>();

            String objs = CinemaDetailActivity.myCinema.getCinemaFilms();
            objs = objs.substring(objs.indexOf("["),
                    objs.lastIndexOf("]") + 1);
            objs = objs.replace("\\\"","\"");
            try {
                JSONArray jons = new JSONArray(objs);

                for (int i = 0; i < jons.length(); i++) {
                    JSONObject film = jons.getJSONObject(i);
                    Gson gson = new Gson();
                    MovieBriefBean bean = gson.fromJson(film.toString(), MovieBriefBean.class);

                    MovieBrief mb = new MovieBrief(bean.getFilmName(), bean.getFilmPost(), bean.getFilmId());

                    movieList.add(mb);

                }
            } catch (Exception e) {
                e.printStackTrace();
            } finally {
                Message message = new Message();
                message.what = UPDATE_GALLERY;
                mHandler.sendMessage(message);
            }
        }

    });


    public int test = 1;

    private Runnable scheduleRunnable =new Runnable() {

        @Override
        public void run() {

            scheduleList = new ArrayList<>();
            String scheduleURL;
            scheduleURL = WebKey.HOST +WebKey.TIMES+ "?";
            scheduleURL = scheduleURL + WebKey.DATE +"=" +"2016-" + paramDate +"&";
            scheduleURL = scheduleURL + WebKey.CINEMAID +"=" + paramCinemaId +"&";
            scheduleURL = scheduleURL + WebKey.MOVIEID +"=" + movieId.getText().toString();
            System.out.println(scheduleURL);

//            Toast.makeText(getContext(),jsonUrArrUrl,Toast.LENGTH_SHORT).show();

            JsonArrayRequest jsonArrayRequest = new JsonArrayRequest(Request.Method.GET,
                    scheduleURL, "", new Response.Listener<JSONArray>(){

                @Override
                public void onResponse(JSONArray response) {
                    System.out.println("json data = " + response);
                    try {
                        for (int i = 0; i < response.length(); i++) {
                            JSONObject jsonObject = response.getJSONObject(i);
                            System.out.println("json item = " + jsonObject);
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



    private List<Schedule> getData() {
        List<Schedule> list = new ArrayList<Schedule>();

        for(int i=0; i<10;i++) {
            Schedule schedule = new Schedule("10:50", "12:45散场","3号厅","英语／3D","48元");
            list.add(schedule);
        }

        return list;
    }


}
