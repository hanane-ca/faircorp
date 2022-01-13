package com.emse.spring.faircorp.api;

import com.emse.spring.faircorp.dao.HeaterDao;
import com.emse.spring.faircorp.dao.RoomDao;
import com.emse.spring.faircorp.dto.HeaterDto;
import com.emse.spring.faircorp.model.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.transaction.Transactional;
import java.util.List;
import java.util.stream.Collectors;

@RestController
@CrossOrigin
@RequestMapping("/api/heaters")
@Transactional
/**
 * Represents a REST controller for the heater with Spring.
 * It can get the heaters list, add a heater, get a single heater and delete a heater.
 */
public class HeaterController {
    @Autowired
    private final HeaterDao heaterDao;
    @Autowired
    private final RoomDao roomDao;

    public HeaterController(HeaterDao heaterDao, RoomDao roomDao) {
        this.heaterDao = heaterDao;
        this.roomDao = roomDao;
    }

    @GetMapping
    /**
     * A REST request to Get the heaters list.
     * @return List<HeaterDto>.
     */
    public List<HeaterDto> findAll() {
        return heaterDao.findAll().stream().map(HeaterDto::new).collect(Collectors.toList());
    }

    @GetMapping(path = "/{heater_id}")
    /**
     * A REST request to Get the heater by id.
     * @return HeaterDto A class that its constructor contains infos about the heater.
     * @param heater_id the id of the heater you want to get.
     */
    public HeaterDto findById(@PathVariable Long heater_id) {
        return heaterDao.findById(heater_id).map(HeaterDto::new).orElse(null);
    }


    @PostMapping
    /**
     * A REST request to Create (POST) a heater.
     * @return HeaterDto A class that its constructor contains infos about the heater.
     * @param dto a building with a constructor (id, name, power, heaterStatus, roomName, roomId).
     */
    public HeaterDto create(@RequestBody HeaterDto dto) {
        Room room = roomDao.getById(dto.getRoomId());
        Heater heater = null;
        if (dto.getId() == null) {
            heater = heaterDao.save(new Heater(dto.getName(),room, dto.getHeaterStatus()));
        }
        else {
            heater = heaterDao.getById(dto.getId());
            heater.setHeaterStatus(dto.getHeaterStatus());
            heater.setName(dto.getName());
            heater.setPower(dto.getPower());
        }
        return new HeaterDto(heater);
    }

    @DeleteMapping(path = "/{heater_id}")
    /**
     * A REST request to Delete the heater by id.
     * @return void.
     * @param heater_id the id of the heater you want to get.
     */
    public void delete(@PathVariable Long heater_id) {
        heaterDao.deleteById(heater_id);
    }
}

