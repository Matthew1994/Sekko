package com.example.guanlu.sekko.adapter;

import android.content.Context;
import android.os.Handler;
import android.os.Message;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.BaseAdapter;
import android.widget.ListView;
import android.widget.TextView;

import com.example.guanlu.sekko.R;
import com.example.guanlu.sekko.VO.Schedule;
import com.example.guanlu.sekko.viewConctroller.OrderDialog;

import java.util.List;

/**
 * Created by guanlu on 16/6/8.
 */
public class ScheduleAdapter extends BaseAdapter {
    private LayoutInflater mInflater = null;
    private List<Schedule> data;
    private ListView lv;
    private Handler mHandler;
    public OrderDialog orderDialog;
    private Context context;

    private final int SHOW_DIALOG = 5;

    public ScheduleAdapter(Context context, List<Schedule> data, ListView lv, Handler mHandler) {
        this.mInflater = LayoutInflater.from(context);
        this.data = data;
        this.lv = lv;
        this.mHandler = mHandler;
        this.context = context;
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


        lv.setOnItemClickListener(new AdapterView.OnItemClickListener() {
                @Override
                public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                    orderDialog = new OrderDialog(context);
                    orderDialog.init();
                    orderDialog.setPrice("总价 : "+data.get(position).getPrice());
                    orderDialog.setStartTime(data.get(position).getStartTime());
                    Message message = new Message();
                    message.what = SHOW_DIALOG;
                    mHandler.sendMessage(message);

                }
            });
        return convertView;
    }

    public OrderDialog getOrderDialog() {
        return  orderDialog;
    }

    static class ViewHolder {
        public TextView startTime;
        public TextView endTime;
        public TextView room;
        public TextView roomType;
        public TextView price;

    }
}
