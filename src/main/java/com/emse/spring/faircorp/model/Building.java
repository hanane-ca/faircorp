package com.emse.spring.faircorp.model;

import javax.persistence.*;
import java.util.Set;

@Entity
@Table
public class Building {
    @Id
    @GeneratedValue
    private long id;

    @Column(nullable = false)
    private Integer numberOfRooms;

    @Column(nullable = false)
    private String name;

    @OneToMany(mappedBy = "building")
    private Set<Room> room;

    Building(){

    }

    Building(long id, Integer numberOfRooms, String name, Set<Room> room){
        this.id = id;
        this.name = name;
        this.numberOfRooms = numberOfRooms;
        this.room = room;
    }

    public Building(Integer numberOfRooms, String name) {
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public Integer getNumberOfRooms() {
        return numberOfRooms;
    }

    public void setNumberOfRooms(Integer numberOfRooms) {
        this.numberOfRooms = numberOfRooms;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Set<Room> getRoom() {
        return room;
    }

    public void setRoom(Set<Room> room) {
        this.room = room;
    }

}
