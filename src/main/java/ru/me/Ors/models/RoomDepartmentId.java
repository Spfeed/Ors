package ru.me.Ors.models;

import jakarta.persistence.Column;
import jakarta.persistence.Embeddable;

import java.io.Serializable;

@Embeddable
public class RoomDepartmentId implements Serializable {

    @Column(name = "room_id")
    private int roomId;

    @Column(name = "department_id")
    private int departmentId;

    public RoomDepartmentId(int roomId, int departmentId) {
        this.roomId = roomId;
        this.departmentId = departmentId;
    }

    public int getRoomId() {
        return roomId;
    }

    public void setRoomId(int roomId) {
        this.roomId = roomId;
    }

    public int getDepartmentId() {
        return departmentId;
    }

    public void setDepartmentId(int departmentId) {
        this.departmentId = departmentId;
    }
}
