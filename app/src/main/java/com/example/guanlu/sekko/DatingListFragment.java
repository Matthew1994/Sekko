package com.example.guanlu.sekko;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.guanlu.sekko.adapter.InviterCardAdapter;
import com.example.guanlu.sekko.adapter.TicketCardAdapter;
import com.example.guanlu.sekko.model.Inviter;

import java.util.ArrayList;
import java.util.List;


/**
 * A simple {@link Fragment} subclass.
 */
public class DatingListFragment extends Fragment {


    private RecyclerView rv;
    private List<Inviter> inviterList;

    public DatingListFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_dating_list,container,false);

        rv = (RecyclerView)view.findViewById(R.id.inviter_list);
        rv.setLayoutManager(new LinearLayoutManager(getActivity()));
        rv.setItemAnimator(new DefaultItemAnimator());

        inviterList = getData();

        InviterCardAdapter inviterCardAdapter = new InviterCardAdapter(getActivity(), inviterList);
        rv.setAdapter(inviterCardAdapter);

        return view;
    }

    private List<Inviter> getData() {
        List<Inviter> list = new ArrayList<>();

        for(int i = 0; i<=5; i++) {
            Inviter inviter = new Inviter("Cathy","18","girl",null,"A nice day!~","海珠区",
                    "girls","愤怒的小鸟");
            list.add(inviter);
        }
        return list;
    }

}
