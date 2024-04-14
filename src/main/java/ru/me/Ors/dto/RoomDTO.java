package ru.me.Ors.dto;

import jakarta.persistence.Column;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;

public class RoomDTO {
    @NotNull(message = "Номер кабинета не должен быть пустым!")
    private int number;

    @NotNull(message = "Ширина кабинета не может быть равна нулю!")
    private double width;

    @NotNull(message = "Длина кабинета не можеть быть равна нулю!")
    private double length;

    @NotNull(message = "Высота комнаты не может быть равна нулю!")
    private double height;

    @NotEmpty(message = "Назначение комнаты не может быть пустым!")
    @Size(max=50, message = "Назанчение должно быть кратким!")
    private String purpose;

    @NotNull(message = "Этаж не должен быть пустым!")
    private int floor;

    @NotEmpty(message = "название корпуса не должно быть пустым")
    private String campusName;

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

    public String getCampusName() {
        return campusName;
    }

    public void setCampusName(String campusName) {
        this.campusName = campusName;
    }
}
