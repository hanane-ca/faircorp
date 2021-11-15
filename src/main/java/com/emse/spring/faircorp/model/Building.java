package com.emse.spring.faircorp.model;

import javax.persistence.*;
import java.util.Set;

@Entity
@Table
public class Building {
    @Id
    @GeneratedValue
    private long id;
    private double outsideTemperature;

    @OneToMany(mappedBy = "building")
    private Set<Room> rooms;

    public Building(){

    }

    public Building(double outsideTemperature){
        this.outsideTemperature = outsideTemperature;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public double getOutsideTemperature() {
        return outsideTemperature;
    }

    public void setOutsideTemperature(double outsideTemperature) {
        this.outsideTemperature = outsideTemperature;
    }

    public Set<Room> getRoom() {
        return rooms;
    }

    public void setRoom(Set<Room> rooms) {
        this.rooms = rooms;
    }

}
