package com.emse.spring.faircorp.model;

import javax.persistence.*;
import java.util.Set;

@Entity
public class Room {

    @Id
    @GeneratedValue
    private Long id;

    @Column(nullable = false)
    private int floor;

    @Column(nullable = false)
    private String name;

    private Double currentTemp;

    private Double targetTemp;

    @OneToMany(mappedBy = "room")
    private Set<Heater> heaters;

    @OneToMany(mappedBy = "room")
    private Set<Window> windows;

    @ManyToOne(optional = false)
    private Building building;

    public Room(){}

    public Room(String name,int floor,Building building){
        this.floor = floor;
        this.name = name;
        this.building=building;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public int getFloor() {
        return floor;
    }

    public void setFloor(int floor) {
        this.floor = floor;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Double getCurrentTemp() {
        return currentTemp;
    }

    public void setCurrentTemp(Double currentTemp) {
        this.currentTemp = currentTemp;
    }

    public Double getTargetTemp() {
        return targetTemp;
    }

    public void setTargetTemp(Double targetTemp) {
        this.targetTemp = targetTemp;
    }

    public Set<Heater> getHeaters() {
        return heaters;
    }

    public void setHeaters(Set<Heater> heaters) {
        this.heaters = heaters;
    }

    public Set<Window> getWindows() {
        return windows;
    }

    public void setWindows(Set<Window> windows) {
        this.windows = windows;
    }

    public Building getBuilding() {
        return building;
    }

    public void setBuilding(Building building) {
        this.building = building;
    }
}
