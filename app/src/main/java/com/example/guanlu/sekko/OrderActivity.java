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

import com.example.guanlu.sekko.adapter.TicketCardAdapter;
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

        recyclerView = (RecyclerView) findViewById(R.id.order_list);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        recyclerView.setItemAnimator(new DefaultItemAnimator());
        //recyclerView.setHasFixedSize(true);

        ticketList = getData();
/*        CardAdapter cardAdapter = new CardAdapter(this,ticketList);
        recyclerView.setAdapter(cardAdapter);*/
        TicketCardAdapter ticketCardAdapter = new TicketCardAdapter(this, ticketList);
        recyclerView.setAdapter(ticketCardAdapter);

    }

    private List<Ticket> getData() {
        List<Ticket> list = new ArrayList<>();
        for (int i = 0; i < 5; i++) {
            boolean isUsed = false;
            if (i % 2 ==0) {
                isUsed = true;
            }
                Ticket ticket = new Ticket(
                        "夜孔雀", "已完成",
                        "影院： 金逸珠江国际影院（大学城店）",
                        "场次：2016-02-27 17:00",
                        "数量： 2",
                        "   总价： ¥76",
                        "nightpeacock.jpg",
                        isUsed
                );

                list.add(ticket);


        }
        Toast.makeText(getBaseContext(), Integer.toString(list.size()), Toast.LENGTH_SHORT).show();
        return list;
    }
}

