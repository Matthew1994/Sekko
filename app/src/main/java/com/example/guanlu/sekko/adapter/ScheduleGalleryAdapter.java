package com.example.guanlu.sekko.adapter;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.BaseAdapter;
import android.widget.Gallery;
import android.widget.ImageView;
import android.widget.TextView;

import com.android.volley.toolbox.ImageLoader;
import com.example.guanlu.sekko.VO.Movie;
import com.example.guanlu.sekko.VO.MovieBrief;
import com.example.guanlu.sekko.cache.ImgCacheManager;
import com.example.guanlu.sekko.util.BitmapUtil;
import com.example.guanlu.sekko.viewConctroller.GalleryFlow;
import com.example.guanlu.sekko.viewConctroller.MovieDetailActivity;
import com.example.guanlu.sekko.viewConctroller.TabCinemaOrder;

import java.util.List;

/**
 * Created by guanlu on 16/5/31.
 */
public class ScheduleGalleryAdapter extends BaseAdapter {

    private Context mContext;
    private List<MovieBrief> movieList;
    private  GalleryFlow galleryFlow;
    private TextView label;
    private TextView movieId;

    public ScheduleGalleryAdapter(Context context, List<MovieBrief> movieList, GalleryFlow galleryFlow, TextView label,TextView movieId)
    {
        this.mContext = context;
        this.galleryFlow = galleryFlow;
        this.movieList = movieList;
        this.label = label;
        this.movieId = movieId;
    }
    @Override
    public int getCount() {
        return movieList.size();
    }

    @Override
    public Object getItem(int position) {
        return movieList.get(position);
    }
    @Override
    public long getItemId(int position) {
        return position;
    }
    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        ImageView temp = new ImageView(mContext);
        temp.setVisibility(View.GONE);
        ImageView imageView = new ImageView(mContext);
        imageView.setLayoutParams(new Gallery.LayoutParams(100, 300));
        imageView.setScaleType(ImageView.ScaleType.FIT_CENTER);
        ImageLoader.ImageListener listener = ImageLoader.getImageListener(temp, android.R.drawable.ic_menu_rotate, android.R.drawable.ic_delete);
        ImageLoader.ImageContainer container =ImgCacheManager.getLoader().get(movieList.get(position).getMovieImg(), listener);
        imageView.setImageBitmap(BitmapUtil.createReflectedBitmap(container.getBitmap()));

        galleryFlow.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                label.setText(movieList.get(position).getMovieName());
                movieId.setText(movieList.get(position).getMovieId());
                System.out.println(movieId.getText().toString());
            }
        });

        return imageView;
    }

}