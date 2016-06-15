package com.example.guanlu.sekko.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.guanlu.sekko.R;
import com.example.guanlu.sekko.VO.Inviter;

import java.util.List;

/**
 * Created by guanlu on 16/6/1.
 */
public class InviterCardAdapter extends RecyclerView.Adapter<InviterCardAdapter.InviterViewHolder>  {


    private List<Inviter> list;
    private Context context;

    public InviterCardAdapter(Context context, List<Inviter> list) {
        this.context = context;
        this.list = list;
    }

    @Override
    public InviterViewHolder onCreateViewHolder(ViewGroup viewGroup, int i) {
        View v = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.inviter_card_item,
                viewGroup, false);
        return new InviterViewHolder(v);
    }

    @Override
    public void onBindViewHolder(InviterViewHolder viewHolder, int i) {
        Inviter inviter = list.get(i);
        viewHolder.name.setText(inviter.getName());
        viewHolder.ageAndGender.setText(inviter.getGender() + inviter.getAge());
        viewHolder.words.setText(inviter.getWords());
        viewHolder.movie.setText(inviter.getMovie());
        viewHolder.location.setText(inviter.getLocation());
        viewHolder.invitee.setText(inviter.getInvitee());
        //viewHolder.img.setImageResource(R.drawable.nightpeacock);
    }

    @Override
    public int getItemCount() {
        return list.size();
    }

   class InviterViewHolder extends  RecyclerView.ViewHolder {

        public TextView name;
        public TextView ageAndGender;
        public TextView words;
        public TextView movie;
        public TextView location;
        public TextView invitee;
        public ImageView img;

        public InviterViewHolder(View v) {
            super(v);

            name = (TextView) v.findViewById(R.id.inviter_name);
            ageAndGender = (TextView) v.findViewById(R.id.inviter_age);
            words = (TextView) v.findViewById(R.id.inviter_words);
            movie = (TextView) v.findViewById(R.id.inviter_movie);
            location = (TextView) v.findViewById(R.id.inviter_location);
            invitee = (TextView) v.findViewById(R.id.inviter_invitee);
            img = (ImageView) v.findViewById(R.id.inviter_img);
        }
    }
}
