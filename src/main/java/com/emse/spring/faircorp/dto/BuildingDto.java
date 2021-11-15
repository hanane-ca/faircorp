package com.emse.spring.faircorp.dto;
import com.emse.spring.faircorp.model.Building;

import java.util.*;

public class BuildingDto {

    private Long id;
    private Double outsideTemperature;

    BuildingDto(){

    }

    public BuildingDto(Building building){
        this.id = building.getId();
        this.outsideTemperature=building.getOutsideTemperature();
    }

    public Long getId() {
        return id;
    }

    public Double getOutsideTemperature() {
        return outsideTemperature;
    }

    public void setOutsideTemperature(Double outsideTemperature) {
        this.outsideTemperature = outsideTemperature;
    }

    public void setId(Long id) {
        this.id = id;
    }
}
