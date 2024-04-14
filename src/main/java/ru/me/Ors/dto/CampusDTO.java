package ru.me.Ors.dto;

import jakarta.persistence.Column;
import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.NotEmpty;

public class CampusDTO {
    @NotEmpty(message = "Название корпуса не должно быть пустым!")
    private String name;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
