package com.example.guanlu.sekko.VO;

import java.io.Serializable;
import java.util.List;

/**
 * Created by guanlu on 16/5/31.
 */
public class Cinema implements Serializable {
    String cinemaName;
    String cinemaAddress;
    String cinemaRoute;
    String cinemaFeature;
    String cinemaTel;
    String cinemaId;
    String cinemaFilms;

    public Cinema(String cinemaName, String cinemaAddress, String cinemaRoute, String cinemaFeature,
                  String cinemaTel, String cinemaId , String cinemaFilms) {
        this.cinemaName = cinemaName;
        this.cinemaAddress = cinemaAddress;
        this.cinemaRoute = cinemaRoute;
        this.cinemaFeature = cinemaFeature;
        this.cinemaTel = cinemaTel;
        this.cinemaId =cinemaId;
        this.cinemaFilms = cinemaFilms;
    }



    public String getCinemaId() {
        return cinemaId;
    }

    public void setCinemaId(String cinemaId) {
        this.cinemaId = cinemaId;
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

    public String getCinemaFilms() {
        return cinemaFilms;
    }

    public void setCinemaFilms(String cinemaFilms) {
        this.cinemaFilms = cinemaFilms;
    }
}
