package ru.me.Ors.models;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.Size;

import java.util.List;

@Entity
@Table(name = "Faculty")
public class Faculty {

    @Id
    @Column(name = "id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @Column(name = "name")
    @NotEmpty(message = "Название факультета не может быть пустым!")
    @Size(max = 100, message = "Название факультета не должно превышать 100 символов в длину")
    private String name;

    @Column(name = "dean")
    @NotEmpty(message = "У факультета обязательно должен быть декан!")
    @Size(max = 50, message = "Имя декана не должно превышать 50 символов в длину!")
    private String dean;

    @Column(name = "specialization")
    @NotEmpty(message = "Краткое описание факультета не должно быть пустым!")
    @Size(max = 100, message = "Описание должно состоять не более, чем из 100 символов!")
    private String specialization;

    @OneToMany(mappedBy = "faculty", cascade = CascadeType.REMOVE)
    private List<Department> departments;

    public Faculty() {}

    public Faculty(String name, String dean, String specialization) {
        this.name = name;
        this.dean = dean;
        this.specialization = specialization;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

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

    public List<Department> getDepartments() {
        return departments;
    }

    public void setDepartments(List<Department> departments) {
        this.departments = departments;
    }
}
