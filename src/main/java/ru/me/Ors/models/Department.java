package ru.me.Ors.models;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.Size;

@Entity
@Table(name = "Department")
public class Department {

    @Id
    @Column(name = "id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @Column(name = "name")
    @NotEmpty(message = "Название кафедры не должно быть пустым!")
    @Size(max = 100, message = "Название кафедры не должно превышать 100 символов в длину!")
    private String name;

    @Column(name = "chairperson")
    @NotEmpty(message = "У кафедры должен быть заведующий!")
    @Size(max = 50, message = "Имя зав. кафедрой не должно превышать 50 символов в длину!")
    private String chairperson;

    @ManyToOne(cascade = CascadeType.REMOVE)
    @JoinColumn(name = "faculty_id", referencedColumnName = "id")
    private Faculty faculty;

    public Department() {}

    public Department(String name, String chairperson, Faculty faculty) {
        this.name = name;
        this.chairperson = chairperson;
        this.faculty = faculty;
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

    public String getChairperson() {
        return chairperson;
    }

    public void setChairperson(String chairperson) {
        this.chairperson = chairperson;
    }

    public Faculty getFaculty() {
        return faculty;
    }

    public void setFaculty(Faculty faculty) {
        this.faculty = faculty;
    }
}
