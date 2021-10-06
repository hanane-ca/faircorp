package com.emse.spring.faircorp.dao;

import com.emse.spring.faircorp.model.Room;

public interface RoomCustomDao {
    Room findByName(String name);
}
