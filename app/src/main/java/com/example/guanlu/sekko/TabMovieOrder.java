package com.example.guanlu.sekko;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import java.util.Calendar;

import sun.bob.mcalendarview.CellConfig;
import sun.bob.mcalendarview.MarkStyle;
import sun.bob.mcalendarview.listeners.OnDateClickListener;
import sun.bob.mcalendarview.listeners.OnExpDateClickListener;
import sun.bob.mcalendarview.listeners.OnMonthScrollListener;
import sun.bob.mcalendarview.views.DefaultCellView;
import sun.bob.mcalendarview.views.ExpCalendarView;
import sun.bob.mcalendarview.vo.DateData;

/**
 * A simple {@link Fragment} subclass.
 */
public class TabMovieOrder extends Fragment {

    private TextView YearMonthTv;
    private ExpCalendarView expCalendarView;
    private View view;

    public TabMovieOrder() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        view = inflater.inflate(R.layout.movie_order_tab, container, false);
        //      Get instance.
        expCalendarView = ((ExpCalendarView) view.findViewById(R.id.calendar_exp));
        YearMonthTv = (TextView) view.findViewById(R.id.main_YYMM_Tv);
        YearMonthTv.setText(Calendar.getInstance().get(Calendar.YEAR) + "年" + (Calendar.getInstance().get(Calendar.MONTH) + 1) + "月");

//      Set up listeners.

        CellConfig.Month2WeekPos = CellConfig.middlePosition;
        CellConfig.ifMonth = false;
       // expandIV.setImageResource(R.drawable.ic_keyboard_arrow_down_black_24dp);
        expCalendarView.shrink();

//        imageInit();



        expCalendarView.setOnDateClickListener(new OnExpDateClickListener()).setOnMonthScrollListener(new OnMonthScrollListener() {
            @Override
            public void onMonthChange(int year, int month) {
                YearMonthTv.setText(String.format("%d年%d月", year, month));
            }

            @Override
            public void onMonthScroll(float positionOffset) {
//                Log.i("listener", "onMonthScroll:" + positionOffset);
            }

        });
        expCalendarView.setOnDateClickListener(new OnExpDateClickListener() {
            @Override
            public void onDateClick(View view, DateData date) {
                ((DefaultCellView) view).setDateChoose();
                Toast.makeText(getContext(),date.getDayString(),Toast.LENGTH_SHORT).show();
               // expCalendarView.markDate(date);
                //expCalendarView.setMarkedStyle(MarkStyle.DOT,getResources().getColor(R.color.purple));
                super.onDateClick(view,date);
            }
        });


        return view;
    }

    private boolean ifExpand = true;


  /*  private void imageInit() {
        final ImageView expandIV = (ImageView) view.findViewById(R.id.main_expandIV);
       *//* expandIV.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (ifExpand) {
                    CellConfig.Month2WeekPos = CellConfig.middlePosition;
                    CellConfig.ifMonth = false;
                    expandIV.setImageResource(R.drawable.ic_keyboard_arrow_down_black_24dp);
                    expCalendarView.shrink();
                } else {
                    CellConfig.Week2MonthPos = CellConfig.middlePosition;
                    CellConfig.ifMonth = true;
                    expandIV.setImageResource(R.drawable.ic_keyboard_arrow_up_black_24dp);
                    expCalendarView.expand();
                }
                ifExpand = !ifExpand;
            }
        });*//*
    }*/



}
