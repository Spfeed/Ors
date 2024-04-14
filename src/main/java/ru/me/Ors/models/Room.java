package ru.me.Ors.models;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;

@Entity
@Table(name = "Room")
public class Room {

    @Id
    @Column(name = "id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @Column(name = "number")
    @NotNull(message = "Номер кабинета не должен быть пустым!")
    private int number;

    @Column(name = "width")
    @NotNull(message = "Ширина кабинета не может быть равна нулю!")
    private double width;

    @Column(name = "length")
    @NotNull(message = "Длина кабинета не можеть быть равна нулю!")
    private double length;

    @Column(name = "height")
    @NotNull(message = "Высота комнаты не может быть равна нулю!")
    private double height;

    @Column(name = "purpose")
    @NotEmpty(message = "Назначение комнаты не может быть пустым!")
    @Size(max=50, message = "Назанчение должно быть кратким!")
    private String purpose;

    @Column(name = "floor")
    @NotNull(message = "Этаж не должен быть пустым!")
    private int floor;

    @ManyToOne(cascade = CascadeType.REMOVE)
    @JoinColumn(name = "campus_id", referencedColumnName = "id")
    private Campus campus;

    public Room() {}

    public Room(int number, double width, double length, double height, String purpose, int floor, Campus campus) {
        this.number = number;
        this.width = width;
        this.length = length;
        this.height = height;
        this.purpose = purpose;
        this.floor = floor;
        this.campus = campus;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getNumber() {
        return number;
    }

    public void setNumber(int number) {
        this.number = number;
    }

    public double getWidth() {
        return width;
    }

    public void setWidth(double width) {
        this.width = width;
    }

    public double getLength() {
        return length;
    }

    public void setLength(double length) {
        this.length = length;
    }

    public double getHeight() {
        return height;
    }

    public void setHeight(double height) {
        this.height = height;
    }

    public String getPurpose() {
        return purpose;
    }

    public void setPurpose(String purpose) {
        this.purpose = purpose;
    }

    public int getFloor() {
        return floor;
    }

    public void setFloor(int floor) {
        this.floor = floor;
    }

    public Campus getCampus() {
        return campus;
    }

    public void setCampus(Campus campus) {
        this.campus = campus;
    }
}
