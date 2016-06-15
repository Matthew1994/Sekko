package com.example.guanlu.sekko.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.example.guanlu.sekko.R;
import com.example.guanlu.sekko.VO.Schedule;

import java.util.List;

/**
 * Created by guanlu on 16/6/8.
 */
public class ScheduleAdapter extends BaseAdapter {
    private LayoutInflater mInflater = null;
    private List<Schedule> data;

    public ScheduleAdapter(Context context, List<Schedule> data) {
        this.mInflater = LayoutInflater.from(context);
        this.data = data;
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
    public View getView(int position, View convertView, ViewGroup parent) {
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
        holder.startTime.setText(data.get(position).getStartTime());
        holder.endTime.setText(data.get(position).getEndTime());
        holder.room.setText(data.get(position).getRoom());
        holder.roomType.setText(data.get(position).getRoomType());
        holder.price.setText(data.get(position).getPrice());

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

    static class ViewHolder {
        public TextView startTime;
        public TextView endTime;
        public TextView room;
        public TextView roomType;
        public TextView price;

    }
}
