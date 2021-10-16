package com.emse.spring.faircorp.dto;
import com.emse.spring.faircorp.model.Building;

import java.util.*;

public class BuildingDto {

    private Long id;
    private String name;
    private Integer numberOfRooms;

    BuildingDto(){

    }

    public BuildingDto(Building building){
        this.id = building.getId();
        this.name = building.getName();
        this.numberOfRooms = building.getNumberOfRooms();
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Integer getNumberOfRooms() {
        return numberOfRooms;
    }

    public void setNumberOfRooms(Integer numberOfRooms) {
        this.numberOfRooms = numberOfRooms;
    }
}
