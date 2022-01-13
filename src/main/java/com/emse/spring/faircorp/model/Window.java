package com.emse.spring.faircorp.model;

import com.sun.istack.NotNull;

import javax.persistence.*;

@Entity
@Table(name = "RWINDOW")
/**
 * This is an entity that represents a model for the windows.
 */
public class Window {
    @Id
    @GeneratedValue
    private Long id;

    @Column(nullable = false)
    private String name;

    @Enumerated(EnumType.STRING)
    @NotNull
    private WindowStatus windowStatus;

    @ManyToOne(optional = false)
    private Room room;

    public Window() {
    }

    public Window(String name, WindowStatus status) {
        this.windowStatus = status;
        this.name = name;
    }

    public Window(Room room, String name, WindowStatus windowStatus) {
        this.room = room;
        this.name = name;
        this.windowStatus = windowStatus;
    }

    /**
     * A getter to get id of the window.
     * @return Long returns the id of the window.
     */
    public Long getId() {
        return this.id;
    }

    /**
     * A setter to set id.
     * @return void.
     * @param id the window id.
     */
    public void setId(Long id) {
        this.id = id;
    }

    /**
     * A getter to get the name of the window.
     * @return String returns the name of the window.
     */
    public String getName() {
        return name;
    }

    /**
     * A setter to set window status (OPEN, CLOSED).
     * @return void.
     * @param windowStatus the status of the window.
     */
    public void setWindowStatus(WindowStatus windowStatus) {
        this.windowStatus = windowStatus;
    }

    /**
     * A getter to get the room to which this window belongs.
     * @return Room returns the Room object which contains all the infos about the room.
     */
    public Room getRoom() {
        return room;
    }

    /**
     * A setter to set the room in which the window belongs.
     * @return void.
     * @param room the room that we want to set.
     */
    public void setRoom(Room room) {
        this.room = room;
    }

    /**
     * A setter to set name of the window.
     * @return void.
     * @param name the name of the window.
     */
    public void setName(String name) {
        this.name = name;
    }

    /**
     * A getter to get the status of the window (OPEN, CLOSED).
     * @return WindowStatus the status of the window (OPEN/CLOSED).
     */
    public WindowStatus getWindowStatus() {
        return windowStatus;
    }

    /**
     * A setter to set window status (OPEN, CLOSED).
     * @return void.
     * @param windowStatus the status of the window.
     */
    public void setWindowStatus(WindowStatus windowStatus,Room room) {
        this.windowStatus = windowStatus;
        this.room = room;
    }
}