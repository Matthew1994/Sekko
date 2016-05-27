package com.example.guanlu.sekko;


import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Bundle;
import android.support.design.widget.Snackbar;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.AccelerateDecelerateInterpolator;
import android.view.animation.Animation;
import android.view.animation.DecelerateInterpolator;
import android.view.animation.TranslateAnimation;
import android.widget.AdapterView;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.Gallery;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;


/**
 * A simple {@link Fragment} subclass.
 */
public class TabCinemaOrder extends Fragment {

    //gallery
    private List<Bitmap> ImageList= new ArrayList<Bitmap>();
    private List<Bitmap> GalleryList= new ArrayList<Bitmap>();

    private GalleryFlow galleryFlow;
    private BitmapProcessor bitmapProcessor = new BitmapProcessor();

    private List<Button> buttons;

    private ListView schedule;

    private List<Map<String,Object>> data;

    private ImageView buttonBg;

    public TabCinemaOrder() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view =  inflater.inflate(R.layout.cinema_order_tab, container, false);

        //gallery

        ImageList.add(BitmapFactory.decodeResource(getActivity().getResources(),R.drawable.allegiant));
        ImageList.add(BitmapFactory.decodeResource(getActivity().getResources(),R.drawable.angrybird));
        ImageList.add(BitmapFactory.decodeResource(getActivity().getResources(),R.drawable.nightpeacock));
        ImageList.add(BitmapFactory.decodeResource(getActivity().getResources(),R.drawable.criminal));
        ImageList.add(BitmapFactory.decodeResource(getActivity().getResources(),R.drawable.mr2));

        for(Bitmap bitmap:ImageList) {
            if(bitmap!=null && GalleryList!=null)
            GalleryList.add(bitmapProcessor.createReflectedBitmap(bitmap));
        }
        for(Bitmap bitmap:ImageList) {
            bitmap.recycle();
        }

        galleryFlow = (GalleryFlow)view.findViewById(R.id.order_gallery);
        ImageAdapter imageAdapter = new ImageAdapter(getActivity());
        galleryFlow.setAdapter(imageAdapter);


        //button
        buttons = new ArrayList<Button>();
        Button button1=(Button)view.findViewById(R.id.button1);
        buttons.add(button1);

        Button button2=(Button)view.findViewById(R.id.button2);
        buttons.add(button2);

        Button button3=(Button)view.findViewById(R.id.button3);
        buttons.add(button3);

        button2.setOnClickListener(orderDateListener);
        button1.setOnClickListener(orderDateListener);
        button3.setOnClickListener(orderDateListener);

        Date date = new Date();
        SimpleDateFormat df=new SimpleDateFormat("MM-dd");
        button1.setText(df.format(date).toString());
        button2.setText(df.format(new Date(date.getTime() + (long)1 * 24 * 60 * 60 * 1000)));
        button3.setText(df.format(new Date(date.getTime() + (long)2 * 24 * 60 * 60 * 1000)));

        buttonBg = (ImageView)view.findViewById(R.id.button_bg);


        //schedule
        data=getData();
        schedule = (ListView)view.findViewById(R.id.movie_schedule);
        ScheduleAdapter scheduleAdapter = new ScheduleAdapter(getContext());
        schedule.setAdapter(scheduleAdapter);

        return view;
    }

    private int lastButton = 0;
    private View.OnClickListener orderDateListener = new View.OnClickListener() {
        @Override
        public void onClick(View v) {


            /*for(Button i:buttons) {
                i.setBackgroundColor(0xffA7A7A);
            }
            v.setBackgroundColor(0xff000000);*/

            int pace = buttons.indexOf(v) - lastButton;
            Toast.makeText(getContext(),buttons.indexOf(v)+" - " +Integer.toString(lastButton)
                    +" = " +Integer.toString(pace),Toast.LENGTH_SHORT).show();
            TranslateAnimation animation = null;
            animation = new TranslateAnimation(lastButton*buttonBg.getWidth(),(lastButton+pace)*buttonBg.getWidth(), buttonBg.getTop(),
                    buttonBg.getTop());

            /*animation = new TranslateAnimation(0,buttonBg.getWidth(), buttonBg.getTop(),
                    buttonBg.getTop());*/
            animation.setDuration(400);
            animation.setFillAfter(true);
            animation.setInterpolator(new DecelerateInterpolator());
            buttonBg.startAnimation(animation);

            lastButton = buttons.indexOf(v);

            for(Button i:buttons) {
                i.setTextColor(0xFFdedede);
            }
            buttons.get(buttons.indexOf(v)).setTextColor(0xFFFFFFFF);

            //buttonBg.layout(0,0,buttonBg.getWidth(),buttonBg.getTop());
        }
    };


    public class ImageAdapter extends BaseAdapter {

        Context mContext;

        public ImageAdapter(Context context) {
            this.mContext = context;
        }
        @Override
        public int getCount() {
            return GalleryList.size();
        }

        @Override
        public Object getItem(int position) {
            return GalleryList.get(position);
        }
        @Override
        public long getItemId(int position) {
            return position;
        }
        @Override
        public View getView(int position, View convertView, ViewGroup parent) {
            ImageView imageView = new ImageView(mContext);
            imageView.setImageBitmap(GalleryList.get(position));
            imageView.setLayoutParams(new Gallery.LayoutParams(100, 300));
            imageView.setScaleType(ImageView.ScaleType.FIT_CENTER);


            galleryFlow.setOnItemClickListener(new AdapterView.OnItemClickListener() {
                @Override
                public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                    Toast.makeText(getContext(),"select ",Toast.LENGTH_SHORT).show();
                }
            });

            return imageView;
        }

    }


    private List<Map<String,Object>> getData() {
        List<Map<String,Object>> list = new ArrayList<Map<String,Object>>();
        Map<String,Object> map;

        for(int i=0; i<10;i++) {
            map = new HashMap<String,Object>();
            map.put("startTime","10:50");
            map.put("endTime","12:45散场");
            map.put("room","3号厅");
            map.put("type","英语／3D");
            map.put("price","48元");
            list.add(map);
        }

        return list;
    }

    static class ViewHolder {
        public TextView startTime;
        public TextView endTime;
        public TextView room;
        public TextView roomType;
        public TextView price;

    }

    public class ScheduleAdapter extends BaseAdapter {
        private LayoutInflater mInflater = null;

        private ScheduleAdapter(Context context) {
            this.mInflater = LayoutInflater.from(context);
        }

        @Override
        public int getCount() {
            return data.size();
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
        public View getView(int position, View convertView,ViewGroup parent) {
            ViewHolder holder = null;

            if(convertView ==null) {
                holder = new ViewHolder();
                convertView = mInflater.inflate(R.layout.schedule_item,null);
                holder.startTime=(TextView) convertView.findViewById(R.id.start_time);
                holder.endTime=(TextView) convertView.findViewById(R.id.end_time);
                holder.room=(TextView) convertView.findViewById(R.id.room);
                holder.roomType=(TextView) convertView.findViewById(R.id.room_type);
                holder.price=(TextView) convertView.findViewById(R.id.price);
                convertView.setTag(holder);
            } else  {
                holder = (ViewHolder)convertView.getTag();
            }
            holder.startTime.setText((String)data.get(position).get("startTime"));
            holder.endTime.setText((String)data.get(position).get("endTime"));
            holder.room.setText((String)data.get(position).get("room"));
            holder.roomType.setText((String)data.get(position).get("type"));
            holder.price.setText((String)data.get(position).get("price"));

            /*//click
            movieList.setOnItemClickListener(new AdapterView.OnItemClickListener() {
                @Override
                public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                    Intent intent = new Intent();
                    // intent.setClass(getActivity(), DetailsActivity.class);
                    intent.setClass(getActivity(), MovieDetailActivity.class);
                    Bundle bundle = new Bundle();
                    bundle.putInt("img", (int)data.get(position).get("img"));
                    bundle.putString("name", (String)data.get(position).get("name"));
                    bundle.putString("type", (String)data.get(position).get("type"));
                    bundle.putString("rating", (String)data.get(position).get("rating"));
                    intent.putExtras(bundle);
                    //startActivityForResult(intent, 1);
                    startActivity(intent);
                }
            });*/
            return convertView;
        }
    }
}
