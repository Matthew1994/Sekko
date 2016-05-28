package com.example.guanlu.sekko;
import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentActivity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.BaseAdapter;
import android.widget.Gallery;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;

import com.example.guanlu.sekko.util.BitmapUtil;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;


/**
 * Created by 关璐 on 2016/5/20.
 */
public class MovieFragment extends Fragment {
    private View mParent;

    private FragmentActivity mActivity;


    private ListView movieList;
    private List<Map<String,Object>> data;

    //gallery
    private List<Bitmap> ImageList= new ArrayList<>();
    private List<Bitmap> GalleryList= new ArrayList<>();

    private GalleryFlow galleryFlow;

    public static MovieFragment newInstance(int index) {
        MovieFragment f = new MovieFragment();

        Bundle args = new Bundle();
        args.putInt("index", index);
        f.setArguments(args);

        return f;

    }

    public int getShownIndex() {
        return getArguments().getInt("index",0);
    }

    @Override
    public View onCreateView(LayoutInflater inflater,ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.movie_fragment, container,false);
        return view;
    }

    @Override
    public void onActivityCreated(Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        mParent=getView();
        mActivity = getActivity();

        movieList=(ListView)mParent.findViewById(R.id.movie_list);
        data = getData();
        MyMovieAdapter myMovieAdapter = new MyMovieAdapter(mActivity);
        movieList.setAdapter(myMovieAdapter);

        //gallery

        ImageList.add(BitmapFactory.decodeResource(mActivity.getResources(),R.drawable.allegiant));
        ImageList.add(BitmapFactory.decodeResource(mActivity.getResources(),R.drawable.angrybird));
        ImageList.add(BitmapFactory.decodeResource(mActivity.getResources(),R.drawable.nightpeacock));
        ImageList.add(BitmapFactory.decodeResource(mActivity.getResources(),R.drawable.criminal));
        ImageList.add(BitmapFactory.decodeResource(mActivity.getResources(),R.drawable.mr2));

        for(Bitmap bitmap:ImageList) {
            GalleryList.add(BitmapUtil.createReflectedBitmap(bitmap));
        }
        for(Bitmap bitmap:ImageList) {
            bitmap.recycle();
        }

        galleryFlow = (GalleryFlow)mParent.findViewById(R.id.gallery);
        ImageAdapter imageAdapter = new ImageAdapter(mActivity);
        galleryFlow.setAdapter(imageAdapter);



    }

    private List<Map<String,Object>> getData() {
        List<Map<String,Object>> list = new ArrayList<Map<String,Object>>();
        Map<String,Object> map;

        for(int i=0; i<2;i++) {
            map = new HashMap<String,Object>();
            map.put("img",R.drawable.allegiant);
            map.put("name","分歧者3: 忠诚世界");
            map.put("type","类型： 爱情／动作／科幻／冒险");
            map.put("rating","8.4");
            list.add(map);
        }
        for(int i=0; i<2;i++) {
            map = new HashMap<String,Object>();
            map.put("img",R.drawable.nightpeacock);
            map.put("name","夜孔雀");
            map.put("type","类型： 爱情／剧情");
            map.put("rating","10.0");
            list.add(map);
        }
        return list;
    }

    static class ViewHolder {
        public ImageView movieImg;
        public TextView movieName;
        public TextView movieType;
        public TextView movieRating;

    }

    public class MyMovieAdapter extends BaseAdapter {
        private LayoutInflater mInflater = null;

        private MyMovieAdapter(Context context) {
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
                convertView = mInflater.inflate(R.layout.movie_list_item,null);
                holder.movieImg=(ImageView)convertView.findViewById(R.id.movie_img);
                holder.movieName=(TextView) convertView.findViewById(R.id.movie_item_name);
                holder.movieType=(TextView) convertView.findViewById(R.id.movie_item_type);
                holder.movieRating=(TextView) convertView.findViewById(R.id.movie_item_rating);
                convertView.setTag(holder);
            } else  {
                holder = (ViewHolder)convertView.getTag();
            }
            holder.movieImg.setBackgroundResource((Integer)data.get(position).get("img"));
            holder.movieName.setText((String)data.get(position).get("name"));
            holder.movieType.setText((String)data.get(position).get("type"));
            holder.movieRating.setText((String)data.get(position).get("rating"));

            //click
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
            });
            return convertView;
        }
    }


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
            imageView.setLayoutParams(new Gallery.LayoutParams(140, 450));
            imageView.setScaleType(ImageView.ScaleType.FIT_CENTER);


            galleryFlow.setOnItemClickListener(new AdapterView.OnItemClickListener() {
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
            });

            return imageView;
        }
    }


    @Override
    public void onHiddenChanged(boolean hidden) {
        super.onHiddenChanged(hidden);
    }

    @Override
    public void onDestroy() {
        for(Bitmap bitmap:GalleryList) {
            bitmap.recycle();
        }
        super.onDestroy();
    }
}
