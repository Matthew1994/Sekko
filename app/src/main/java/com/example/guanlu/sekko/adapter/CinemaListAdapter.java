package com.example.guanlu.sekko.adapter;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.BaseAdapter;
import android.widget.ListView;
import android.widget.TextView;

import com.example.guanlu.sekko.CinemaDetailActivity;
import com.example.guanlu.sekko.R;
import com.example.guanlu.sekko.model.Cinema;

import java.util.List;

/**
 * Created by guanlu on 16/6/1.
 */
public class CinemaListAdapter extends BaseAdapter{


        private LayoutInflater mInflater = null;
        private List<Cinema> cinemaList;
        private Context context;
        private ListView lv;

        public CinemaListAdapter(Context context,List<Cinema> cinemaList,ListView lv) {
            this.mInflater = LayoutInflater.from(context);
            this.cinemaList = cinemaList;
            this.context = context;
            this.lv = lv;
        }

        @Override
        public int getCount() {
            return cinemaList.size();
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

            if(convertView ==null) {
                holder = new ViewHolder();
                convertView = mInflater.inflate(R.layout.cinema_list_item,null);
                holder.cinemaName=(TextView)convertView.findViewById(R.id.cinema_item_name);
                holder.cinemaAddress=(TextView)convertView.findViewById(R.id.cinema_item_address);
                holder.cinemaRoute=(TextView)convertView.findViewById(R.id.cinema_item_route);

                convertView.setTag(holder);
            } else  {
                holder = (ViewHolder)convertView.getTag();
            }
            holder.cinemaName.setText(cinemaList.get(position).getCinemaName());
            holder.cinemaAddress.setText(cinemaList.get(position).getCinemaAddress());
            holder.cinemaRoute.setText(cinemaList.get(position).getCinemaRoute());

            //click
            lv.setOnItemClickListener(new AdapterView.OnItemClickListener() {
                @Override
                public void onItemClick(AdapterView<?> parent, View view, int position, long id) {

                    Log.d("----","click");
                    Intent intent = new Intent();
                    // intent.setClass(getActivity(), DetailsActivity.class);
                    intent.setClass(context, CinemaDetailActivity.class);
                    Bundle bundle = new Bundle();
                    bundle.putSerializable("cinema",cinemaList.get(position));
                    intent.putExtras(bundle);
                    //startActivityForResult(intent, 1);
                    context.startActivity(intent);
                }
            });
            return convertView;
        }



    static class ViewHolder {
        public TextView cinemaName;
        public TextView cinemaAddress;
        public TextView cinemaRoute;
    }

}

