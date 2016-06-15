package com.example.guanlu.sekko.volley;

import android.content.Context;

import com.android.volley.RequestQueue;
import com.android.volley.toolbox.Volley;

/**
 * Created by guanlu on 16/6/5.
 */
public class VolleyManager {

    private static RequestQueue requestQueue;

    public static void init(Context context) {
        if(requestQueue == null)
        requestQueue = Volley.newRequestQueue(context);
    }

    public static RequestQueue getRequestQueue() {
        return  requestQueue;
    }
}
