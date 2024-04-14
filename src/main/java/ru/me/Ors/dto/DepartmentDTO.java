package ru.me.Ors.dto;

import jakarta.persistence.Column;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.Size;

public class DepartmentDTO {
    @NotEmpty(message = "Название кафедры не должно быть пустым!")
    @Size(max = 100, message = "Название кафедры не должно превышать 100 символов в длину!")
    private String name;

    @NotEmpty(message = "У кафедры должен быть заведующий!")
    @Size(max = 50, message = "Имя зав. кафедрой не должно превышать 50 символов в длину!")
    private String chairperson;

    @NotEmpty(message = "Кафедра должна принаджлежать факультету!")
    @Size(max=100, message = "Название факультета не должно превышать 100 символов!")
    private String faculty_name;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getChairperson() {
        return chairperson;
    }

    public void setChairperson(String chairperson) {
        this.chairperson = chairperson;
    }

    public String getFaculty_name() {
        return faculty_name;
    }

    public void setFaculty_name(String faculty_name) {
        this.faculty_name = faculty_name;
    }
}
