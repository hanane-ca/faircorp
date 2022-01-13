package com.emse.spring.faircorp.model;

import javax.persistence.*;
import java.util.Set;

@Entity
/**
 * This is an entity that represents a model for the rooms.
 */
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

    /**
     * A getter to get id.
     * @return Long returns the id of the room.
     */
    public Long getId() {
        return id;
    }

    /**
     * A setter to set id.
     * @return void.
     * @param id the room id.
     */
    public void setId(Long id) {
        this.id = id;
    }

    /**
     * A getter to get floor.
     * @return int returns the number of the floor in which the room is located.
     */
    public int getFloor() {
        return floor;
    }

    /**
     * A setter to set the floor.
     * @return void.
     * @param floor the floor number of the room.
     */
    public void setFloor(int floor) {
        this.floor = floor;
    }

    /**
     * A getter to get name of the room.
     * @return String returns the name of the room.
     */
    public String getName() {
        return name;
    }

    /**
     * A setter to set the name of the room.
     * @return void.
     * @param name the name of the room.
     */
    public void setName(String name) {
        this.name = name;
    }

    /**
     * A getter to get the current temperature of the room.
     * @return Double returns the current temperature of the room.
     */
    public Double getCurrentTemp() {
        return currentTemp;
    }

    /**
     * A setter to set the current temperature of the room.
     * @return void.
     * @param currentTemp the current temperature of the room.
     */
    public void setCurrentTemp(Double currentTemp) {
        this.currentTemp = currentTemp;
    }

    /**
     * A getter to get the target temperature of the room.
     * @return Double returns the target temperature of the room.
     */
    public Double getTargetTemp() {
        return targetTemp;
    }

    /**
     * A setter to set the target temperature of the room.
     * @return void.
     * @param targetTemp the target temperature of the room.
     */
    public void setTargetTemp(Double targetTemp) {
        this.targetTemp = targetTemp;
    }

    /**
     * A getter to get the heaters existing in the room.
     * @return Set<Heater> returns the heaters existing in the room.
     */
    public Set<Heater> getHeaters() {return heaters;}

    /**
     * A setter to set heaters in the room.
     * @return void.
     * @param heaters heaters in the room.
     */
    public void setHeaters(Set<Heater> heaters) {
        this.heaters = heaters;
    }

    /**
     * A getter to get the windows existing in the room.
     * @return Set<Window> returns the windows existing in the room.
     */
    public Set<Window> getWindows() {
        return windows;
    }

    /**
     * A setter to set windows in the room.
     * @return void.
     * @param windows windows in the room.
     */
    public void setWindows(Set<Window> windows) {
        this.windows = windows;
    }

    /**
     * A getter to get the building where the room is located.
     * @return Building returns the building where the room is located.
     */
    public Building getBuilding() {
        return building;
    }

    /**
     * A setter to set the building of the room.
     * @return void.
     * @param building building of the room.
     */
    public void setBuilding(Building building) {
        this.building = building;
    }
}
