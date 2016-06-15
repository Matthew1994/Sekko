package com.example.guanlu.sekko.DTO;

/**
 * Created by guanlu on 16/6/8.
 */
public class ScheduleBean{
    private String id;
    private String timesId;
    private String date;
    private String cinemaId;
    private String movieId;
    private String time;
    private String languageAndEffect;
    private String playingRoom;
    private String price;

    public ScheduleBean(String id, String timesId, String date, String cinemaId, String movieId, String time, String languageAndEffect, String playingRoom, String price) {
        this.id = id;
        this.timesId = timesId;
        this.date = date;
        this.cinemaId = cinemaId;
        this.movieId = movieId;
        this.time = time;
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

    public String getTimesId() {
        return timesId;
    }

    public void setTimesId(String timesId) {
        this.timesId = timesId;
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

    public String getTime() {
        return time;
    }

    public void setTime(String time) {
        this.time = time;
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

    @Override
    public String toString() {
        return "ScheduleBean{" +
                "id='" + id + '\'' +
                ", timesId='" + timesId + '\'' +
                ", date='" + date + '\'' +
                ", cinemaId='" + cinemaId + '\'' +
                ", movieId='" + movieId + '\'' +
                ", time='" + time + '\'' +
                ", languageAndEffect='" + languageAndEffect + '\'' +
                ", playingRoom='" + playingRoom + '\'' +
                ", price='" + price + '\'' +
                '}';
    }
}
