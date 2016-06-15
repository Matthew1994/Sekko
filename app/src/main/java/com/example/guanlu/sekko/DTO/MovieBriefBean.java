package com.example.guanlu.sekko.DTO;

/**
 * Created by guanlu on 16/6/13.
 */
public class MovieBriefBean {
    String filmName;
    String filmPost;
    String filmId;

    public MovieBriefBean(String filmName, String filmPost, String filmId) {
        this.filmName = filmName;
        this.filmPost = filmPost;
        this.filmId = filmId;
    }

    public String getFilmName() {
        return filmName;
    }

    public void setFilmName(String filmName) {
        this.filmName = filmName;
    }

    public String getFilmPost() {
        return filmPost;
    }

    public void setFilmPost(String filmPost) {
        this.filmPost = filmPost;
    }

    public String getFilmId() {
        return filmId;
    }

    public void setFilmId(String filmId) {
        this.filmId = filmId;
    }

    @Override
    public String toString() {
        return "MovieBriefBean{" +
                "filmName='" + filmName + '\'' +
                ", filmPost='" + filmPost + '\'' +
                ", filmId='" + filmId + '\'' +
                '}';
    }
}
