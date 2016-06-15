package com.example.guanlu.sekko.VO;

import android.widget.TextView;

/**
 * Created by guanlu on 16/6/8.
 */
public class Schedule {
    private String startTime;
    private String endTime;
    private String room;
    private String roomType;
    private String price;

    public Schedule(String startTime, String endTime, String room, String roomType, String price) {
        this.startTime = startTime;
        this.endTime = endTime;
        this.room = room;
        this.roomType = roomType;
        this.price = price;
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

    public String getRoom() {
        return room;
    }

    public void setRoom(String room) {
        this.room = room;
    }

    public String getRoomType() {
        return roomType;
    }

    public void setRoomType(String roomType) {
        this.roomType = roomType;
    }

    public String getPrice() {
        return price;
    }

    public void setPrice(String price) {
        this.price = price;
    }

    @Override
    public String toString() {
        return "Schedule{" +
                "startTime='" + startTime + '\'' +
                ", endTime='" + endTime + '\'' +
                ", room='" + room + '\'' +
                ", roomType='" + roomType + '\'' +
                ", price='" + price + '\'' +
                '}';
    }
}
