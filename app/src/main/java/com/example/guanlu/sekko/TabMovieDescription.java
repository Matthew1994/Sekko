package com.example.guanlu.sekko;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.view.PagerAdapter;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

/**
 * A simple {@link Fragment} subclass.
 */
public class TabMovieDescription extends Fragment {


    private TextView time, director, period, type, story;

    public TabMovieDescription() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.movie_description_tab, container, false);

        time = (TextView) view.findViewById(R.id.text_time);
        period = (TextView) view.findViewById(R.id.text_period);
        type = (TextView) view.findViewById(R.id.text_type);
        director = (TextView) view.findViewById(R.id.text_director);
        story=(TextView)view.findViewById(R.id.text_story);

        time.setText(MovieDetailActivity.myMovie.getMovieShowTime());
        period.setText(MovieDetailActivity.myMovie.getMoviePeriod());
        type.setText(MovieDetailActivity.myMovie.getMovieType());
        director.setText(MovieDetailActivity.myMovie.getMovieDirector());
        story.setText(MovieDetailActivity.myMovie.getMovieStory());
        return view;
    }

}
