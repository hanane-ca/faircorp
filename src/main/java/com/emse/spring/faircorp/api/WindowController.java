package com.emse.spring.faircorp.api;

import com.emse.spring.faircorp.dao.RoomDao;
import com.emse.spring.faircorp.dao.WindowDao;
import com.emse.spring.faircorp.dto.WindowDto;
import com.emse.spring.faircorp.model.Room;
import com.emse.spring.faircorp.model.Window;
import com.emse.spring.faircorp.model.WindowStatus;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.transaction.Transactional;
import java.util.List;
import java.util.stream.Collectors;

/**
 * Represents a REST controller for the window with Spring.
 * It can get the windows list, add a window, get a single window, delete a room, switch a window status.
 */
@RestController
@RequestMapping("/api/windows")
//@CrossOrigin(origins = "http://localhost:8080", maxAge = 3600)
@CrossOrigin
@Transactional
public class WindowController {
    @Autowired
    private final WindowDao windowDao;
    @Autowired
    private final RoomDao roomDao;

    public WindowController(WindowDao windowDao, RoomDao roomDao) { // (4)
        this.windowDao = windowDao;
        this.roomDao = roomDao;
    }

    /**
     * A REST request to Get the windows list.
     * @return List of the WindowDto
     */
    @GetMapping
    public List<WindowDto> findAll() {
        return windowDao.findAll().stream().map(WindowDto::new).collect(Collectors.toList());  // (6)
    }

    /**
     * A REST request to Get the window by id.
     * @return WindowDto A class that its constructor contains infos about the window.
     * @param id the id of the window you want to get.
     */
    @GetMapping(path = "/{id}")
    public WindowDto findById(@PathVariable Long id) {
        return windowDao.findById(id).map(WindowDto::new).orElse(null); // (7)
    }

    /**
     * A REST request to update the window status (OPEN/CLOSED).
     * @return WindowDto A class that its constructor contains infos about the window.
     * @param id the id of the window you want to update.
     */
    @PutMapping(path = "/{id}/switch")
    public WindowDto switchStatus(@PathVariable Long id) {
        Window window = windowDao.findById(id).orElseThrow(IllegalArgumentException::new);
        window.setWindowStatus(window.getWindowStatus() == WindowStatus.OPEN ? WindowStatus.CLOSED: WindowStatus.OPEN);
        return new WindowDto(window);
    }

    /**
     * A REST request to Create (POST) a window.
     * @return WindowDto A class that its constructor contains infos about the window.
     * @param dto a window with a constructor (id, name, windowStatus, roomName, roomId).
     */
    @PostMapping
    public WindowDto create(@RequestBody WindowDto dto) {
        // WindowDto must always contain the window room
        Room room = roomDao.getById(dto.getRoomId());
        Window window = null;
        // On creation id is not defined
        if (dto.getId() == null) {
            window = windowDao.save(new Window(room, dto.getName(), dto.getWindowStatus()));
        }
        else {
            window = windowDao.getById(dto.getId());  // (9)
            window.setWindowStatus(dto.getWindowStatus());
        }
        return new WindowDto(window);
    }

    /**
     * A REST request to Delete the window by id.
     * @param id the id of the window you want to get.
     */
    @DeleteMapping(path = "/{id}")
    public void delete(@PathVariable Long id) {
        windowDao.deleteById(id);
    }
}
