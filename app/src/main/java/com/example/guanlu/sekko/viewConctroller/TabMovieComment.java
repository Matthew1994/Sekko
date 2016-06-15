package com.example.guanlu.sekko.viewConctroller;


import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.AppBarLayout;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v4.app.Fragment;
import android.support.v7.widget.CardView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.example.guanlu.sekko.R;

/**
 * A simple {@link Fragment} subclass.
 */
public class TabMovieComment extends Fragment {


    private LinearLayout content_lv;
    public TabMovieComment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        View view=inflater.inflate(R.layout.movie_comment_tab, container, false);

        content_lv = (LinearLayout)view.findViewById(R.id.comment_l);

        FloatingActionButton fab_add_comment = (FloatingActionButton)view.findViewById(R.id.fab_add_comment);

        fab_add_comment.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                //TODO: add a card, generate a CommentCard Class

                Snackbar.make(view, "TODO: add a comment", Snackbar.LENGTH_LONG)
                        .setAction("Action", null).show();
                /*CardView cardView = new CardView(getContext());

                CardView.LayoutParams lp = new CardView.LayoutParams(
                        ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.MATCH_PARENT);

                lp.setMargins(10,(int)getResources().getDimension(R.dimen.activity_horizontal_margin),
                        10,(int)getResources().getDimension(R.dimen.activity_horizontal_margin));

                cardView.setLayoutParams(lp);
                cardView.setCardElevation(5);
                cardView.setBackgroundColor(0xFFFFFF);


                EditText editText = new EditText(getContext());
                editText.setHint("请输入评论内容");

                cardView.addView(editText);
                editText.setFocusable(true);


                content_lv.addView(cardView);*/
            }
        });
        return view;
    }

}
