package ru.me.Ors.models;

import jakarta.persistence.*;

@Entity
@Table(name = "Room_Department")
public class RoomDepartment {
    @EmbeddedId
    private RoomDepartmentId id;

    @ManyToOne(cascade = CascadeType.ALL)
    @MapsId("roomId")
    @JoinColumn(name = "room_id", referencedColumnName = "id")
    private Room room;

    @ManyToOne(cascade = CascadeType.ALL)
    @MapsId("departmentId")
    @JoinColumn(name = "department_id", referencedColumnName = "id")
    private Department department;

    public RoomDepartment() {}

    public RoomDepartment(Room room, Department department) {
        this.room = room;
        this.department = department;
        this.id = new RoomDepartmentId(room.getId(), department.getId());
    }

    public RoomDepartmentId getId() {
        return id;
    }

    public void setId(RoomDepartmentId id) {
        this.id = id;
    }

    public Room getRoom() {
        return room;
    }

    public void setRoom(Room room) {
        this.room = room;
    }

    public Department getDepartment() {
        return department;
    }

    public void setDepartment(Department department) {
        this.department = department;
    }
}
