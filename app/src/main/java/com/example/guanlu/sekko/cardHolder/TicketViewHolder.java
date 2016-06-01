package com.example.guanlu.sekko.cardHolder;

import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.guanlu.sekko.R;

/**
 * Created by guanlu on 16/6/1.
 */

public class TicketViewHolder extends  RecyclerView.ViewHolder {

    public TextView movieName;
    public TextView label;
    public TextView cinemaName;
    public TextView time;
    public TextView num;
    public TextView price;
    public ImageView img;
    public ImageView isUsed;

    public TicketViewHolder(View v) {
        super(v);

        movieName = (TextView) v.findViewById(R.id.order_item_movie_name);
        label = (TextView) v.findViewById(R.id.order_item_label);
        cinemaName = (TextView) v.findViewById(R.id.order_item_cinema_name);
        time = (TextView) v.findViewById(R.id.order_item_time);
        num = (TextView) v.findViewById(R.id.order_item_num);
        price = (TextView) v.findViewById(R.id.order_item_price);
        img = (ImageView) v.findViewById(R.id.order_item_movie_img);
        isUsed = (ImageView)v.findViewById(R.id.order_item_isused);

    }
}