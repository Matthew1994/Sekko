package com.example.guanlu.sekko.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.guanlu.sekko.R;
import com.example.guanlu.sekko.model.Ticket;

import java.util.List;

/**
 * Created by guanlu on 16/6/1.
 */

public  class TicketCardAdapter extends  RecyclerView.Adapter<TicketCardAdapter.TicketViewHolder> {

    private List<Ticket> list;
    private Context context;

    public TicketCardAdapter(Context context, List<Ticket> list) {
        this.context = context;
        this.list = list;
    }

    @Override
    public TicketViewHolder onCreateViewHolder(ViewGroup viewGroup, int i) {
        View v = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.order_list_card_item,
                viewGroup, false);
        return new TicketViewHolder(v);
    }

    @Override
    public void onBindViewHolder(TicketViewHolder viewHolder, int i) {
        Ticket t = list.get(i);
        viewHolder.movieName.setText(t.getMovieName());
        viewHolder.label.setText(t.getLabel());
        viewHolder.cinemaName.setText(t.getCinemaName());
        viewHolder.time.setText(t.getTime());
        viewHolder.num.setText(t.getNum());
        viewHolder.price.setText(t.getPrice());
        viewHolder.img.setImageResource(R.drawable.nightpeacock);
        if(t.isUsed() == true) {
            viewHolder.isUsed.setImageResource(R.drawable.mark_used);
        } else {
            viewHolder.isUsed.setImageResource(R.drawable.mark_unused);
        }
    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    class TicketViewHolder extends RecyclerView.ViewHolder {

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
            isUsed=(ImageView) v.findViewById(R.id.order_item_isused);
        }
    }

}


