package com.example.guanlu.sekko.DTO;

/**
 * Created by guanlu on 16/6/8.
 */
public class CinemaBean {

    private String name;
    private String img;
    private String location;
    private String transport;
    private String url;
    private String cinemaId;
    private String id;
    private String movies;
    private String coordinate;
    private String tel;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getImg() {
        return img;
    }

    public void setImg(String img) {
        this.img = img;
    }

    public String getLocation() {
        return location;
    }

    public void setLocation(String location) {
        this.location = location;
    }

    public String getTransport() {
        return transport;
    }

    public void setTransport(String transport) {
        this.transport = transport;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public String getCinemaId() {
        return cinemaId;
    }

    public void setCinemaId(String cinemaId) {
        this.cinemaId = cinemaId;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getMovies() {
        return movies;
    }

    public void setMovies(String movies) {
        this.movies = movies;
    }



    public CinemaBean(String name, String img, String coordinate, String location, String transport, String movies, String url, String cinemaId, String tel, String id ) {
        this.name = name;
        this.img = img;
        this.location = location;
        this.transport = transport;
        this.url = url;
        this.cinemaId = cinemaId;
        this.id = id;
        this.movies = movies;
        this.tel = tel;
        this.coordinate = coordinate;
    }

    public String getCoordinate() {
        return coordinate;
    }

    public void setCoordinate(String coordinate) {
        this.coordinate = coordinate;
    }

    public String getTel() {
        return tel;
    }

    public void setTel(String tel) {
        this.tel = tel;
    }

    @Override
    public String toString() {
        return "CinemaBean{" +
                "name='" + name + '\'' +
                ", img='" + img + '\'' +
                ", location='" + location + '\'' +
                ", transport='" + transport + '\'' +
                ", url='" + url + '\'' +
                ", cinemaId='" + cinemaId + '\'' +
                ", id='" + id + '\'' +
                ", movies='" + movies + '\'' +
                '}';
    }
}
