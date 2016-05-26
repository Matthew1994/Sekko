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

        time.setText("2016-05-20  [上映中]");
        period.setText("84分钟/国语");
        type.setText("爱情  剧情");
        director.setText("戴思杰/刘亦菲 / 刘烨 / 余少群 / 黎明");
        story.setText("剧情：　法国女留学生埃尔莎在成都爱上了丝绸研究员、吹箫高手马荣，" +
                "后又在巴黎与马的兄弟、纹身师建民之间产生情感。前者找到了一种符合他理想的蚕——夜孔雀，" +
                "后者把蝶化的夜孔雀永远铭刻在艾尔莎的肌肤上。");
        return view;
    }

}
