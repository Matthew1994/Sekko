package com.example.guanlu.sekko.VO;

/**
 * Created by guanlu on 16/5/26.
 */
public class Ticket {

    String movieName;
    String label;
    String cinemaName;

    String time;
    String num;
    String price;
    int moviePic;

    boolean isUsed;

    public boolean isUsed() {
        return isUsed;
    }

    public void setUsed(boolean used) {
        isUsed = used;
    }

    public String getMovieName() {
        return movieName;
    }

    public void setMovieName(String movieName) {
        this.movieName = movieName;
    }

    public String getLabel() {
        return label;
    }

    public void setLabel(String label) {
        this.label = label;
    }

    public String getCinemaName() {
        return cinemaName;
    }

    public void setCinemaName(String cinemaName) {
        this.cinemaName = cinemaName;
    }

    public String getTime() {
        return time;
    }

    public void setTime(String time) {
        this.time = time;
    }

    public String getNum() {
        return num;
    }

    public void setNum(String num) {
        this.num = num;
    }

    public String getPrice() {
        return price;
    }

    public void setPrice(String price) {
        this.price = price;
    }

    public int getMoviePic() {
        return moviePic;
    }


    public void setMoviePic(int moviePic) {
        this.moviePic = moviePic;
    }


    public Ticket(String movieName, String label, String cinemaName, String time, String num, String price, int moviePic,boolean isUsed) {
        this.movieName = movieName;
        this.label = label;
        this.cinemaName = cinemaName;
        this.time = time;
        this.num = num;
        this.price = price;
        this.moviePic = moviePic;
        this.isUsed = isUsed;
    }
}
