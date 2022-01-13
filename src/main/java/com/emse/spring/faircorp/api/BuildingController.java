package com.emse.spring.faircorp.api;

import com.emse.spring.faircorp.dao.BuildingDao;
import com.emse.spring.faircorp.dao.HeaterDao;
import com.emse.spring.faircorp.dao.RoomDao;
import com.emse.spring.faircorp.dao.WindowDao;
import com.emse.spring.faircorp.dto.BuildingDto;
import com.emse.spring.faircorp.dto.RoomDto;
import com.emse.spring.faircorp.model.Building;
import com.emse.spring.faircorp.model.Room;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.transaction.Transactional;
import java.util.List;
import java.util.stream.Collectors;

/**
 * Represents a REST controller for the building with Spring.
 * It can get the buildings list, add a building, get a single building and delete a building.
 */
@RestController
@CrossOrigin
@RequestMapping("/api/buildings")
@Transactional
public class BuildingController {
    @Autowired
    private final BuildingDao buildingDao;
    @Autowired
    private final RoomDao roomDao;
    @Autowired
    private final WindowDao windowDao;
    @Autowired
    private final HeaterDao heaterDao;


    public BuildingController(BuildingDao buildingDao, RoomDao roomDao, WindowDao windowDao, HeaterDao heaterDao) {
        this.buildingDao = buildingDao;
        this.roomDao = roomDao;
        this.windowDao = windowDao;
        this.heaterDao = heaterDao;
    }

    /**
     * A REST request to Get the buildings list.
     * @return List of BuildingDto.
     */
    @GetMapping
    public List<BuildingDto> findAll() {
        return buildingDao.findAll().stream().map(BuildingDto::new).collect(Collectors.toList());
    }

    /**
     * A REST request to Get the building by id.
     * @return BuildingDto A class that its constructor contains infos about the building.
     * @param building_id the id of the building you want to get.
     */
    @GetMapping(path = "/{building_id}")
    public BuildingDto findById(@PathVariable Long building_id) {
        return buildingDao.findById(building_id).map(BuildingDto::new).orElse(null);
    }

    /**
     * A REST request to Delete the building by id.
     * @param building_id the id of the building you want to get.
     */
    @DeleteMapping(path = "/{building_id}")
    public void delete(@PathVariable Long building_id) {
        buildingDao.deleteById(building_id);
    }

    /**
     * A REST request to Create (POST) a building.
     * @return BuildingDto A class that its constructor contains infos about the building.
     * @param buildingdto a building with a constructor (id, outsideTemperature).
     */
    @PostMapping
    public BuildingDto create(@RequestBody BuildingDto buildingdto){
        Building building=null;
        if(buildingdto.getId()==null){
            building=buildingDao.save(new Building(buildingdto.getOutsideTemperature()));

        }else {
            building=buildingDao.getById(buildingdto.getId());
            buildingdto.setOutsideTemperature(buildingdto.getOutsideTemperature());

        }
        return new BuildingDto(building);
    }
}
