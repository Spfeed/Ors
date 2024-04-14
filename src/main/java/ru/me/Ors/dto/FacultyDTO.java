package ru.me.Ors.dto;

import jakarta.persistence.Column;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.Size;

public class FacultyDTO {
    @NotEmpty(message = "Название факультета не может быть пустым!")
    @Size(max = 100, message = "Название факультета не должно превышать 100 символов в длину")
    private String name;

    @NotEmpty(message = "У факультета обязательно должен быть декан!")
    @Size(max = 50, message = "Имя декана не должно превышать 50 символов в длину!")
    private String dean;

    @NotEmpty(message = "Краткое описание факультета не должно быть пустым!")
    @Size(max = 100, message = "Описание должно состоять не более, чем из 100 символов!")
    private String specialization;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDean() {
        return dean;
    }

    public void setDean(String dean) {
        this.dean = dean;
    }

    public String getSpecialization() {
        return specialization;
    }

    public void setSpecialization(String specialization) {
        this.specialization = specialization;
    }
}
