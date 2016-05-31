package com.example.guanlu.sekko.model;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;

import java.io.ByteArrayOutputStream;
import java.io.Serializable;

/**
 * Created by guanlu on 16/5/31.
 */
public class Cinema implements Serializable {
    String cinemaName;
    String cinemaAddress;
    String cinemaRoute;
    String cinemaFeature;
    String cinemaTel;
    byte[] movieImg;

    public Cinema(String cinemaName, String cinemaAddress, String cinemaRoute, String cinemaFeature,
                  String cinemaTel, byte[] movieImg) {
        this.cinemaName = cinemaName;
        this.cinemaAddress = cinemaAddress;
        this.cinemaRoute = cinemaRoute;
        this.cinemaFeature = cinemaFeature;
        this.cinemaTel = cinemaTel;
        this.movieImg = movieImg;
    }

    public String getCinemaName() {
        return cinemaName;
    }

    public void setCinemaName(String cinemaName) {
        this.cinemaName = cinemaName;
    }

    public String getCinemaAddress() {
        return cinemaAddress;
    }

    public void setCinemaAddress(String cinemaAddress) {
        this.cinemaAddress = cinemaAddress;
    }

    public String getCinemaRoute() {
        return cinemaRoute;
    }

    public void setCinemaRoute(String cinemaRoute) {
        this.cinemaRoute = cinemaRoute;
    }

    public String getCinemaFeature() {
        return cinemaFeature;
    }

    public void setCinemaFeature(String cinemaFeature) {
        this.cinemaFeature = cinemaFeature;
    }

    public String getCinemaTel() {
        return cinemaTel;
    }

    public void setCinemaTel(String cinemaTel) {
        this.cinemaTel = cinemaTel;
    }

    public byte[] getMovieImg() {
        return movieImg;
    }

    public void setMovieImg(byte[] movieImg) {
        this.movieImg = movieImg;
    }



    public static byte[] getBytes(Bitmap bitmap){
        //实例化字节数组输出流
        ByteArrayOutputStream baos = new ByteArrayOutputStream();
        bitmap.compress(Bitmap.CompressFormat.PNG,0, baos);//压缩位图
        return baos.toByteArray();//创建分配字节数组
    }

    public static Bitmap getBitmap(byte[] data){
        return BitmapFactory.decodeByteArray(data, 0, data.length);//从字节数组解码位图
    }
}
