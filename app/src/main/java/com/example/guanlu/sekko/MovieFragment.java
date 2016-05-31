package com.example.guanlu.sekko;
import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentActivity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.BaseAdapter;
import android.widget.Gallery;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;

import com.example.guanlu.sekko.adapter.GalleryAdapter;
import com.example.guanlu.sekko.adapter.MovieListAdapter;
import com.example.guanlu.sekko.model.Movie;
import com.example.guanlu.sekko.util.BitmapUtil;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;


/**
 * Created by 关璐 on 2016/5/20.
 */
public class MovieFragment extends Fragment {
    private View mParent;

    private FragmentActivity mActivity;


    private ListView movieList;
    private List<Movie> data;

    //gallery
    private List<Bitmap> ImageList= new ArrayList<>();
    private List<Bitmap> GalleryList= new ArrayList<>();

    private GalleryFlow galleryFlow;

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
        data = getData();
        /*MyMovieAdapter myMovieAdapter = new MyMovieAdapter(mActivity);
        movieList.setAdapter(myMovieAdapter);*/
        MovieListAdapter movieListAdapter = new MovieListAdapter(mActivity,movieList,data);
        movieList.setAdapter(movieListAdapter);

        //gallery

        ImageList.add(BitmapFactory.decodeResource(mActivity.getResources(),R.drawable.allegiant));
        ImageList.add(BitmapFactory.decodeResource(mActivity.getResources(),R.drawable.angrybird));
        ImageList.add(BitmapFactory.decodeResource(mActivity.getResources(),R.drawable.nightpeacock));
        ImageList.add(BitmapFactory.decodeResource(mActivity.getResources(),R.drawable.criminal));
        ImageList.add(BitmapFactory.decodeResource(mActivity.getResources(),R.drawable.mr2));

        for(Bitmap bitmap:ImageList) {
            GalleryList.add(BitmapUtil.createReflectedBitmap(bitmap));
        }
        for(Bitmap bitmap:ImageList) {
            bitmap.recycle();
        }

        galleryFlow = (GalleryFlow)mParent.findViewById(R.id.gallery);
        GalleryAdapter galleryAdapter = new GalleryAdapter(mActivity,GalleryList,data,galleryFlow);
        galleryFlow.setAdapter(galleryAdapter);



    }

    private List<Movie> getData() {
        List<Movie> list = new ArrayList<Movie>();

        for(int i=0; i<2;i++) {
            Bitmap bitmap = BitmapFactory.decodeResource(getResources(),R.drawable.nightpeacock);
            Movie movie = new Movie("夜孔雀",Movie.getBytes(bitmap)
                    ,"9.2","戴思杰/刘亦菲 / 刘烨 / 余少群 / 黎明",
                    "84分钟/国语","爱情  剧情",
                    "剧情：法国女留学生埃尔莎在成都爱上了丝绸研究员、吹箫高手马荣 后又在巴黎与马的兄弟、纹身师建民之间产生情感。前者找到了一种符合他理想的蚕——夜孔雀, 后者把蝶化的夜孔雀永远铭刻在艾尔莎的肌肤上。"
                    ,"2016-05-20  [上映中]");
            list.add(movie);
        }

        return list;
    }



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
