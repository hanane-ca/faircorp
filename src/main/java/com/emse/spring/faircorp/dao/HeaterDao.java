package com.emse.spring.faircorp.dao;

import com.emse.spring.faircorp.model.Heater;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;

public interface HeaterDao extends JpaRepository<Heater, Long>, HeaterCustomDao {
    @Modifying
    @Query("delete from Heater c where c.room.id = ?1")
    void deleteByRoom(Long name);
}
