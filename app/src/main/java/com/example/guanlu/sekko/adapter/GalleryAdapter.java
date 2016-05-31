package com.example.guanlu.sekko.adapter;

import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
import android.os.Bundle;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.BaseAdapter;
import android.widget.Gallery;
import android.widget.ImageView;

import com.example.guanlu.sekko.GalleryFlow;
import com.example.guanlu.sekko.MovieDetailActivity;
import com.example.guanlu.sekko.model.Movie;

import java.util.List;

/**
 * Created by guanlu on 16/5/31.
 */
public class GalleryAdapter extends BaseAdapter {

    private Context mContext;
    private List<Bitmap> galleryList;
    private List<Movie> movieList;
    private  GalleryFlow galleryFlow;


    public GalleryAdapter(Context context,List<Bitmap> galleryList,List<Movie> movieList,GalleryFlow galleryFlow)
    {
        this.mContext = context;
        this.galleryList = galleryList;
        this.galleryFlow = galleryFlow;
        this.movieList = movieList;
    }
    @Override
    public int getCount() {
        return galleryList.size();
    }

    @Override
    public Object getItem(int position) {
        return galleryList.get(position);
    }
    @Override
    public long getItemId(int position) {
        return position;
    }
    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        ImageView imageView = new ImageView(mContext);
        imageView.setImageBitmap(galleryList.get(position));
        imageView.setLayoutParams(new Gallery.LayoutParams(140, 450));
        imageView.setScaleType(ImageView.ScaleType.FIT_CENTER);


        galleryFlow.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {

                Intent intent = new Intent();
                // intent.setClass(getActivity(), DetailsActivity.class);
                intent.setClass(mContext, MovieDetailActivity.class);
                Bundle bundle = new Bundle();
                bundle.putSerializable("movie", movieList.get(position));
                intent.putExtras(bundle);
                //startActivityForResult(intent, 1);
                mContext.startActivity(intent);
            }
        });

        return imageView;
    }
}