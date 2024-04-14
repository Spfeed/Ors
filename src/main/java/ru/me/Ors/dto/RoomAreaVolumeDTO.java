package ru.me.Ors.dto;

import jakarta.persistence.Column;

public class RoomAreaVolumeDTO {
    @Column(name = "room_id")
    private int roomId;
    @Column(name = "campus_name")
    private String campusName;
    @Column(name = "area")
    private double area;
    @Column(name = "volume")
    private double volume;

    public int getRoomId() {
        return roomId;
    }

    public void setRoomId(int roomId) {
        this.roomId = roomId;
    }

    public String getCampusName() {
        return campusName;
    }

    public void setCampusName(String campusName) {
        this.campusName = campusName;
    }

    public double getArea() {
        return area;
    }

    public void setArea(double area) {
        this.area = area;
    }

    public double getVolume() {
        return volume;
    }

    public void setVolume(double volume) {
        this.volume = volume;
    }
}
