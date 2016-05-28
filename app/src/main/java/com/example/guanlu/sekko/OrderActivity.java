package com.example.guanlu.sekko;

import android.content.Context;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.guanlu.sekko.model.Ticket;

import java.util.ArrayList;
import java.util.List;

public class OrderActivity extends AppCompatActivity {


    private RecyclerView recyclerView;

    private List<Ticket> ticketList;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_order);

//        getActionBar().setTitle("订单");

        recyclerView=(RecyclerView)findViewById(R.id.order_list);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        recyclerView.setItemAnimator(new DefaultItemAnimator());
        //recyclerView.setHasFixedSize(true);

        ticketList = getData();
        CardAdapter cardAdapter = new CardAdapter(this,ticketList);
        recyclerView.setAdapter(cardAdapter);

    }

    private List<Ticket> getData() {
        List<Ticket> list = new ArrayList<>();
        for (int i = 0;i<5;i++) {

            Ticket ticket = new Ticket(
                    "夜孔雀","已完成",
                    "影院： 金逸珠江国际影院（大学城店）",
                    "场次：2016-02-27 17:00",
                    "数量： 2",
                    "   总价： ¥76",
                    "nightpeacock.jpg"
            );

            list.add(ticket);

        }
        Toast.makeText(getBaseContext(),Integer.toString(list.size()),Toast.LENGTH_SHORT).show();
        return list;
    }

    public  class CardAdapter extends  RecyclerView.Adapter<ViewHolder> {

        private List<Ticket> list;
        private Context context;

        public CardAdapter(Context context, List<Ticket> list) {
            this.context = context;
            this.list  =list;
        }

        @Override
        public ViewHolder onCreateViewHolder(ViewGroup viewGroup, int i) {
            View v = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.order_list_card_item,
                    viewGroup,false);
            return new ViewHolder(v);
        }

        @Override
        public void onBindViewHolder(ViewHolder viewHolder, int i) {
            Ticket t = list.get(i);
            viewHolder.movieName.setText(t.getMovieName());
            viewHolder.label.setText(t.getLabel());
            viewHolder.cinemaName.setText(t.getCinemaName());
            viewHolder.time.setText(t.getTime());
            viewHolder.num.setText(t.getNum());
            viewHolder.price.setText(t.getPrice());
            viewHolder.img.setImageResource(R.drawable.nightpeacock);
        }
        @Override
        public int getItemCount() {
            return list.size();
        }
    }


    public class ViewHolder extends  RecyclerView.ViewHolder {

        public TextView movieName;
        public TextView label;
        public TextView cinemaName;
        public TextView time;
        public TextView num;
        public TextView price;
        public ImageView img;

        public ViewHolder(View v) {
            super(v);

            movieName= (TextView)v.findViewById(R.id.order_item_movie_name);
            label= (TextView)v.findViewById(R.id.order_item_label);
            cinemaName= (TextView)v.findViewById(R.id.order_item_cinema_name);
            time= (TextView)v.findViewById(R.id.order_item_time);
            num= (TextView)v.findViewById(R.id.order_item_num);
            price= (TextView)v.findViewById(R.id.order_item_price);
            img= (ImageView)v.findViewById(R.id.order_item_movie_img);
        }
    }
}

