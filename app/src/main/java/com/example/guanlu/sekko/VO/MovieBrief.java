package com.example.guanlu.sekko.VO;

import java.io.Serializable;

/**
 * Created by guanlu on 16/6/13.
 */
public class MovieBrief implements Serializable{
    String MovieName;
    String MovieImg;
    String movieId;

    public MovieBrief(String movieName, String movieImg, String movieId) {
        MovieName = movieName;
        MovieImg = movieImg;
        this.movieId = movieId;
    }

    public String getMovieName() {
        return MovieName;
    }

    public void setMovieName(String movieName) {
        MovieName = movieName;
    }

    public String getMovieImg() {
        return MovieImg;
    }

    public void setMovieImg(String movieImg) {
        MovieImg = movieImg;
    }

    public String getMovieId() {
        return movieId;
    }

    public void setMovieId(String movieId) {
        this.movieId = movieId;
    }

    @Override
    public String toString() {
        return "MovieBrief{" +
                "MovieName='" + MovieName + '\'' +
                ", MovieImg='" + MovieImg + '\'' +
                ", movieId='" + movieId + '\'' +
                '}';
    }
}
