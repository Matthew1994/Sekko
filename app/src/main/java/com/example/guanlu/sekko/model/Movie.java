package com.example.guanlu.sekko.model;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;

import java.io.ByteArrayOutputStream;
import java.io.Serializable;

/**
 * Created by guanlu on 16/5/31.
 */
public class Movie implements Serializable {


    String movieName;
    byte[] movieImg;
    String movieDirector;
    String moviePeriod;
    String movieType;
    String movieStory;
    String movieShowTime;
    String movieRating;

    public Movie(String movieName, byte[] movieImg, String movieRating,String movieDirector, String moviePeriod,
                 String movieType, String movieStory, String movieShowTime) {
        this.movieName = movieName;
        this.movieImg = movieImg;
        this.movieDirector = movieDirector;
        this.moviePeriod = moviePeriod;
        this.movieType = movieType;
        this.movieRating = movieRating;
        this.movieStory=movieStory;
        this.movieShowTime=movieShowTime;
    }

    public String getMovieRating() {
        return movieRating;
    }

    public void setMovieRating(String movieRating) {
        this.movieRating = movieRating;
    }

    public String getMovieName() {
        return movieName;
    }

    public void setMovieName(String movieName) {
        this.movieName = movieName;
    }

    public byte[] getMovieImg() {
        return movieImg;
    }

    public void setMovieImg(byte[] movieImg) {
        this.movieImg = movieImg;
    }

    public String getMovieDirector() {
        return movieDirector;
    }

    public void setMovieDirector(String movieDirector) {
        this.movieDirector = movieDirector;
    }

    public String getMoviePeriod() {
        return moviePeriod;
    }

    public void setMoviePeriod(String moviePeriod) {
        this.moviePeriod = moviePeriod;
    }

    public String getMovieType() {
        return movieType;
    }

    public void setMovieType(String movieType) {
        this.movieType = movieType;
    }

    public String getMovieStory() {
        return movieStory;
    }

    public void setMovieStory(String movieStory) {
        this.movieStory = movieStory;
    }

    public String getMovieShowTime() {
        return movieShowTime;
    }

    public void setMovieShowTime(String movieShowTime) {
        this.movieShowTime = movieShowTime;
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
