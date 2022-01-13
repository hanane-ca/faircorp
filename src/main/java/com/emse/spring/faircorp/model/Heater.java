package com.emse.spring.faircorp.model;

import com.sun.istack.NotNull;

import javax.persistence.*;

/**
 * This is an entity that represents a model for the heaters.
 */
@Entity
@Table
public class Heater {

    @Id
    @GeneratedValue
    private long id;

    @Column(nullable = false)
    private String name;

    @Column
    private Long power;

    @ManyToOne(optional = false)
    private Room room;

    @Enumerated(EnumType.STRING)
    @NotNull
    private HeaterStatus heaterStatus;

    Heater(){

    }

    public Heater(String name, Room room, HeaterStatus heaterStatus){
        this.name = name;
        this.room = room;
        this.heaterStatus = heaterStatus;
    }

    /**
     * A getter to get id of the heater.
     * @return Long returns the id of the heater.
     */
    public long getId() {
        return id;
    }

    /**
     * A setter to set id.
     * @param id the heater id.
     */
    public void setId(long id) {
        this.id = id;
    }

    /**
     * A getter to get the name of the heater.
     * @return String returns the name of the heater.
     */
    public String getName() {
        return name;
    }

    /**
     * A setter to set the name of the heater.
     * @param name the heater name.
     */
    public void setName(String name) {
        this.name = name;
    }

    /**
     * A getter to get the power of the heater.
     * @return Long returns the power of the heater.
     */
    public Long getPower() {
        return power;
    }

    /**
     * A setter to set the power of the heater.
     * @param power the heater power.
     */
    public void setPower(long power) {
        this.power = power;
    }

    /**
     * A getter to get the room where the heater is located.
     * @return Room returns the room of the heater.
     */
    public Room getRoom() {
        return room;
    }

    /**
     * A setter to set the room where the heater is located.
     * @param room the room where the heater is located.
     */
    public void setRoom(Room room) {
        this.room = room;
    }

    public HeaterStatus getHeaterStatus() {
        return heaterStatus;
    }

    public void setHeaterStatus(HeaterStatus heaterStatus) {
        this.heaterStatus = heaterStatus;
    }



}
