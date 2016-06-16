package com.example.guanlu.sekko.viewConctroller;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.widget.Toast;

import com.example.guanlu.sekko.R;
import com.example.guanlu.sekko.adapter.TicketCardAdapter;
import com.example.guanlu.sekko.VO.Ticket;

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

        boolean isUsed = false;

        Ticket ticket = new Ticket(
                "夜孔雀", "已完成",
                "影院： 金逸珠江国际影院（大学城店）",
                "场次：2016-06-03 17:00",
                "数量： 2",
                "   总价： ¥76",
                R.drawable.nightpeacock,
                isUsed
        );

        list.add(ticket);

        isUsed= true;

        Ticket ticket2 = new Ticket(
                "愤怒的小鸟", "已完成",
                "影院： 天河城影院 ",
                "场次：2016-06-01 13:20",
                "数量： 1",
                "   总价： ¥40",
                R.drawable.angrybird,
                isUsed
        );

        list.add(ticket2);

        isUsed= true;

        Ticket ticket3 = new Ticket(
                "分歧者：忠诚世界", "已完成",
                "影院： 天河城影院 ",
                "场次：2016-05-28 22:20",
                "数量： 2",
                "   总价： ¥46",
                R.drawable.allegiant,
                isUsed
        );

        list.add(ticket3);

        return list;
    }
}

