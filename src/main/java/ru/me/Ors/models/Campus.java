package ru.me.Ors.models;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.NotEmpty;

import java.util.List;

@Entity
@Table(name = "Campus")
public class Campus {

    @Id
    @Column(name = "id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @Column(name = "name", length = 10)
    @NotEmpty(message = "Название корпуса не должно быть пустым!")
    private String name;

    @JsonIgnore
    @OneToMany(mappedBy = "campus", cascade = CascadeType.REMOVE)
    private List<Room> rooms;

    public Campus() {}

    public Campus(String name) {
        this.name = name;
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

    public List<Room> getRooms() {
        return rooms;
    }

    public void setRooms(List<Room> rooms) {
        this.rooms = rooms;
    }
}
