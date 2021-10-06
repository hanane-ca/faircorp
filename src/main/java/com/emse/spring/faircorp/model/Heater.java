package com.emse.spring.faircorp.model;

import com.sun.istack.NotNull;

import javax.persistence.*;

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

    Heater(String name, Room room, HeaterStatus heaterStatus){
        this.name = name;
        this.room = room;
        this.heaterStatus = heaterStatus;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public long getPower() {
        return power;
    }

    public void setPower(long power) {
        this.power = power;
    }

    public Room getRoom() {
        return room;
    }

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
