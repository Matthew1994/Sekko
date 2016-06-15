package com.example.guanlu.sekko.cache;

import android.content.Context;

import com.android.volley.RequestQueue;
import com.android.volley.toolbox.ImageLoader;
import com.android.volley.toolbox.Volley;
import com.baidu.platform.comapi.map.I;

/**
 * Created by guanlu on 16/6/6.
 */
public class ImgCacheManager {

    private static RequestQueue mQueue;
    private static ImageLoader mImageLoader;


    public static void init (Context context) {
        if(mQueue == null)
        mQueue = Volley.newRequestQueue(context);

        mImageLoader = new ImageLoader(mQueue, new BitmapCache());
    }


    public static ImageLoader getLoader() {
        return mImageLoader;

    }


}

