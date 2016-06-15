package com.example.guanlu.sekko.adapter;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;

import com.android.volley.toolbox.ImageLoader;
import com.example.guanlu.sekko.viewConctroller.MovieDetailActivity;
import com.example.guanlu.sekko.R;
import com.example.guanlu.sekko.cache.ImgCacheManager;
import com.example.guanlu.sekko.VO.Movie;

import java.util.List;

/**
 * Created by guanlu on 16/5/31.
 */
public class MovieListAdapter extends BaseAdapter {


    private LayoutInflater mInflater = null;
    private List<Movie> movieList;
    private ListView lv;
    private Context context;

    public MovieListAdapter(Context context, ListView lv, List<Movie> list) {
        this.mInflater = LayoutInflater.from(context);
        this.movieList = list;
        this.lv = lv;
        this.context = context;

    }


    @Override
    public int getCount() {
        return movieList.size();
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
    public View getView(int position, View convertView, ViewGroup parent) {
        ViewHolder holder = null;

        if (convertView == null) {
            holder = new ViewHolder();
            convertView = mInflater.inflate(R.layout.movie_list_item, null);
            holder.movieImg = (ImageView) convertView.findViewById(R.id.movie_img);
            holder.movieName = (TextView) convertView.findViewById(R.id.movie_item_name);
            holder.movieType = (TextView) convertView.findViewById(R.id.movie_item_type);
            holder.movieRating = (TextView) convertView.findViewById(R.id.movie_item_rating);
            convertView.setTag(holder);
        } else {
            holder = (ViewHolder) convertView.getTag();
        }


        ImageLoader.ImageListener listener = ImageLoader.getImageListener(holder.movieImg, android.R.drawable.ic_menu_rotate, android.R.drawable.ic_delete);
        ImageLoader.ImageContainer container = ImgCacheManager.getLoader().get(movieList.get(position).getMovieImg(), listener);





        holder.movieName.setText(movieList.get(position).getMovieName());
        holder.movieType.setText(movieList.get(position).getMovieType());
        holder.movieRating.setText(movieList.get(position).getMovieRating());

        //click
        lv.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Intent intent = new Intent();
                intent.setClass(context, MovieDetailActivity.class);
                Bundle bundle = new Bundle();

                bundle.putSerializable("movie", movieList.get(position));
                intent.putExtras(bundle);

                context.startActivity(intent);
            }
        });
        return convertView;
    }


    static class ViewHolder {
        public ImageView movieImg;
        public TextView movieName;
        public TextView movieType;
        public TextView movieRating;

    }
}
