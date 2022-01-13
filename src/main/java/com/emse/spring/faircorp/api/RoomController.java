package com.emse.spring.faircorp.api;

import com.emse.spring.faircorp.dao.BuildingDao;
import com.emse.spring.faircorp.dao.HeaterDao;
import com.emse.spring.faircorp.dao.RoomDao;
import com.emse.spring.faircorp.dao.WindowDao;
import com.emse.spring.faircorp.dto.RoomDto;
import com.emse.spring.faircorp.dto.WindowDto;
import com.emse.spring.faircorp.model.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.transaction.Transactional;
import java.util.List;
import java.util.stream.Collectors;

/**
 * Represents a REST controller for the room with Spring.
 * It can get the rooms list, add a room, get a single room, delete a room, switch the heater status or room status .
 */
@RestController
@CrossOrigin
@RequestMapping("/api/rooms")
@Transactional
public class RoomController {
    @Autowired
    private final RoomDao roomDao;
    @Autowired
    private final WindowDao windowDao;
    @Autowired
    private final HeaterDao heaterDao;
    @Autowired
    private final BuildingDao buildingDao;

    public RoomController(RoomDao roomDao,WindowDao windowDao,HeaterDao heaterDao, BuildingDao buildingDao) {
        this.roomDao = roomDao;
        this.windowDao = windowDao;
        this.heaterDao = heaterDao;
        this.buildingDao = buildingDao;
    }

    /**
     * A REST request to Get the rooms list.
     * @return List of RoomDto
     */
    @GetMapping
    public List<RoomDto> findAll() {
        return roomDao.findAll().stream().map(RoomDto::new).collect(Collectors.toList());
    }

    /**
     * A REST request to Get the room by id.
     * @return RoomDto A class that its constructor contains infos about the room.
     * @param room_id the id of the room you want to get.
     */
    @GetMapping(path = "/{room_id}")
    public RoomDto findById(@PathVariable Long room_id) {
        return roomDao.findById(room_id).map(RoomDto::new).orElse(null);
    }

    /**
     * A REST request to Delete the room by id.
     * @param room_id the id of the room you want to get.
     */
    @DeleteMapping(path = "/{room_id}")
    public void delete(@PathVariable Long room_id) {
        List<Window> windows=windowDao.findWindows(room_id);
        List<Heater> heaters = heaterDao.findByRoomId(room_id);
        for (Window window:windows) {
            windowDao.deleteById(window.getId());
        }
        for (Heater heater:heaters) {
            heaterDao.deleteById(heater.getId());
        }
        roomDao.deleteById(room_id);
    }

    /**
     * A REST request to update the window status (OPEN/CLOSED).
     * @param room_id the id of the room you want to change its windows.
     */
    @PutMapping(path = "/{room_id}/switchWindow")
    public void switchWindows(@PathVariable Long room_id){
        List<Window> windows= windowDao.findWindows(room_id);
        for (Window window:windows){
            if(window.getWindowStatus()==WindowStatus.CLOSED )
            {
                window.setWindowStatus(WindowStatus.OPEN);}
            else if(window.getWindowStatus()==WindowStatus.OPEN){
                window.setWindowStatus(WindowStatus.CLOSED);
            }
        }
    }

    /**
     * A REST request to Update the heater status (ON/OFF).
     * @param room_id the id of the room you want to change its heaters.
     */
    @PutMapping(path = "/{room_id}/switchHeaters")
    public void switchHeaters(@PathVariable Long room_id) {
        List<Heater> heaters = heaterDao.findByRoomId(room_id);
        for (Heater heater : heaters) {
            if (heater.getHeaterStatus() == HeaterStatus.OFF) {
                heater.setHeaterStatus(HeaterStatus.ON);
            } else if (heater.getHeaterStatus() == HeaterStatus.ON) {
                heater.setHeaterStatus(HeaterStatus.OFF);
            }
        }
    }

    /**
     * A REST request to Create (POST) a room.
     * @return RoomDto A class that its constructor contains infos about the room.
     * @param roomdto a room with a constructor (id, name, floor, currentTemp, targetTemp, buildingId).
     */
    @PostMapping
    public RoomDto create(@RequestBody RoomDto roomdto){
        Building building=buildingDao.getById(roomdto.getBuildingId());
        Room room =null;
        if(roomdto.getId()==null){
            room=roomDao.save(new Room(roomdto.getName(),roomdto.getFloor(),building));

        }else {
            room=roomDao.getById(roomdto.getId());
            room.setFloor(roomdto.getFloor());
            room.setName(roomdto.getName());
            room.setCurrentTemp(roomdto.getCurrentTemp());
            room.setTargetTemp(roomdto.getTargetTemp());
        }
        return new RoomDto(room);
    }


}
