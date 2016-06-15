package com.example.guanlu.sekko.DTO;

/**
 * Created by guanlu on 16/6/5.
 */
public class MovieBean {
    private String name;
    private String img;
    private String score;
    private String type;
    private String timeAndLanguage;
    private String description;
    private String onTime;
    private String actors;
    private String id;

    public MovieBean(String name, String img, String score, String type, String timeAndLanguage,
                     String description, String onTime, String actors, String id) {
        this.name = name;
        this.img = img;
        this.score = score;
        this.type = type;
        this.timeAndLanguage = timeAndLanguage;
        this.description = description;
        this.onTime = onTime;
        this.actors = actors;
        this.id = id;
    }

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

    public String getScore() {
        return score;
    }

    public void setScore(String score) {
        this.score = score;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getTimeAndLanguage() {
        return timeAndLanguage;
    }

    public void setTimeAndLanguage(String timeAndLanguage) {
        this.timeAndLanguage = timeAndLanguage;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getOnTime() {
        return onTime;
    }

    public void setOnTime(String onTime) {
        this.onTime = onTime;
    }

    public String getActors() {
        return actors;
    }

    public void setActors(String actors) {
        this.actors = actors;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    @Override
    public String toString() {
        return "MovieBean{" +
                "name='" + name + '\'' +
                ", img='" + img + '\'' +
                ", score='" + score + '\'' +
                ", type='" + type + '\'' +
                ", timeAndLanguage='" + timeAndLanguage + '\'' +
                ", description='" + description + '\'' +
                ", onTime='" + onTime + '\'' +
                ", actors='" + actors + '\'' +
                ", id='" + id + '\'' +
                '}';
    }
}
