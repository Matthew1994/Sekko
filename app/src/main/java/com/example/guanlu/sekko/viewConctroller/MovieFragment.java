package com.example.guanlu.sekko.viewConctroller;
import android.graphics.Bitmap;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentActivity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;
import android.widget.ListView;

import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonArrayRequest;
import com.example.guanlu.sekko.R;
import com.example.guanlu.sekko.volley.VolleyManager;
import com.example.guanlu.sekko.DTO.MovieBean;
import com.example.guanlu.sekko.adapter.GalleryAdapter;
import com.example.guanlu.sekko.adapter.MovieListAdapter;
import com.example.guanlu.sekko.VO.Movie;
import com.example.guanlu.sekko.volley.WebKey;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import org.json.JSONArray;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;


/**
 * Created by 关璐 on 2016/5/20.
 */

public class MovieFragment extends Fragment {
    private View mParent;

    private FragmentActivity mActivity;

    private ListView movieList;
    private List<Movie> data = new ArrayList<>();

    //gallery
    private List<Bitmap> ImageList= new ArrayList<>();
    private List<Bitmap> GalleryList= new ArrayList<>();

    private GalleryFlow galleryFlow;

    public static final String jsonUrArrUrl = WebKey.HOST +WebKey.MOVIE;

    private static final int UPDATE_LIST = 0;
    private static final int UPDATE_GALLERY = 1;

    private MovieListAdapter movieListAdapter;
    private GalleryAdapter galleryAdapter;

    private Handler mHandler = new Handler(){
        @Override
        public void handleMessage(Message msg) {
            switch (msg.what) {
                case UPDATE_LIST:
                    MovieListAdapter movieListAdapter = new MovieListAdapter(mActivity, movieList, data);
                    movieList.setAdapter(movieListAdapter);
                    Message message = new Message();
                    message.what = UPDATE_GALLERY;
                    mHandler.sendMessageDelayed(message,3000);
                    break;

                case UPDATE_GALLERY:
                    GalleryAdapter galleryAdapter = new GalleryAdapter(mActivity,data,galleryFlow);
                    galleryFlow.setAdapter(galleryAdapter);

                    break;
            }
            super.handleMessage(msg);
        }

    };

    public static MovieFragment newInstance(int index) {
        MovieFragment f = new MovieFragment();

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
        View view = inflater.inflate(R.layout.movie_fragment, container,false);
        return view;
    }

    @Override
    public void onActivityCreated(Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        mParent=getView();
        mActivity = getActivity();

        movieList=(ListView)mParent.findViewById(R.id.movie_list);
        ImageButton refresh = (ImageButton)mParent.findViewById(R.id.refresh);
        galleryFlow = (GalleryFlow)mParent.findViewById(R.id.gallery);

        newThread.start();
        data.clear();
        movieListAdapter = new MovieListAdapter(mActivity, movieList, data);
        movieList.setAdapter(movieListAdapter);

        galleryAdapter = new GalleryAdapter(mActivity,data,galleryFlow);
        galleryFlow.setAdapter(galleryAdapter);


        refresh.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                MovieListAdapter movieListAdapter = new MovieListAdapter(mActivity, movieList, data);
                movieList.setAdapter(movieListAdapter);

                GalleryAdapter galleryAdapter = new GalleryAdapter(mActivity,data,galleryFlow);
                galleryFlow.setAdapter(galleryAdapter);
            }
        });
    }

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
                            System.out.println("json item===" + jsonObject);
                            Gson gson = new Gson();
                            java.lang.reflect.Type type = new TypeToken<MovieBean>() {}.getType();
                            //for test
//                            String jsonTemp = "{\"name\":\"美人鱼\",\"img\":\"http://pic.spider.com.cn/pic//filmpic/jdt/1453690532972_mobile2.jpg\",\"score\":\"9.0\",\"type\":\"爱情,科幻\",\"timeAndLanguage\":\"100分钟/国语\",\"description\":\"星爷作品\",\"onTime\":\"即将下映\",\"actors\":\"周星驰/邓超/张雨绮/罗志祥/林允/文章/吴亦凡\",\"id\":201602189008}";
                            MovieBean movieBean = gson.fromJson(jsonObject.toString(),MovieBean.class);
//                            MovieBean movieBean = gson.fromJson(jsonTemp,MovieBean.class);
                            Movie movie = new Movie(movieBean.getName(),movieBean.getImg()
                                    ,movieBean.getScore(),movieBean.getActors(),movieBean.getTimeAndLanguage(),
                                    movieBean.getType(),movieBean.getDescription()
                                    ,movieBean.getOnTime(),movieBean.getId());
                            data.add(movie);
                        }
                    } catch (Exception e) {
                        System.out.println(e);
                    } finally {
                        movieListAdapter.notifyDataSetChanged();
                        galleryAdapter.notifyDataSetChanged();
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

                }
            });
            VolleyManager.getRequestQueue().add(jsonArrayRequest);
        }
    });






    @Override
    public void onHiddenChanged(boolean hidden) {
        super.onHiddenChanged(hidden);
    }

    @Override
    public void onDestroy() {
        for(Bitmap bitmap:GalleryList) {
            bitmap.recycle();
        }
        super.onDestroy();
    }
}
