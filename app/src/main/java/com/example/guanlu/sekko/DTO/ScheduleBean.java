package com.example.guanlu.sekko.DTO;

/**
 * Created by guanlu on 16/6/8.
 */
public class ScheduleBean{
    private String id;
    private String date;
    private String cinemaId;
    private String movieId;
    private String startTime;
    private String endTime;

    private String languageAndEffect;
    private String playingRoom;
    private String price;

    public ScheduleBean(String id, String date, String cinemaId, String movieId, String startTime, String endTime, String languageAndEffect, String playingRoom, String price) {
        this.id = id;
        this.date = date;
        this.cinemaId = cinemaId;
        this.movieId = movieId;
        this.startTime = startTime;
        this.endTime = endTime;
        this.languageAndEffect = languageAndEffect;
        this.playingRoom = playingRoom;
        this.price = price;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public String getCinemaId() {
        return cinemaId;
    }

    public void setCinemaId(String cinemaId) {
        this.cinemaId = cinemaId;
    }

    public String getMovieId() {
        return movieId;
    }

    public void setMovieId(String movieId) {
        this.movieId = movieId;
    }

    public String getStartTime() {
        return startTime;
    }

    public void setStartTime(String startTime) {
        this.startTime = startTime;
    }

    public String getEndTime() {
        return endTime;
    }

    public void setEndTime(String endTime) {
        this.endTime = endTime;
    }

    public String getLanguageAndEffect() {
        return languageAndEffect;
    }

    public void setLanguageAndEffect(String languageAndEffect) {
        this.languageAndEffect = languageAndEffect;
    }

    public String getPlayingRoom() {
        return playingRoom;
    }

    public void setPlayingRoom(String playingRoom) {
        this.playingRoom = playingRoom;
    }

    public String getPrice() {
        return price;
    }

    public void setPrice(String price) {
        this.price = price;
    }
}
