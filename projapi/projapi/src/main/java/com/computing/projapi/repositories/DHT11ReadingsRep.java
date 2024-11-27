/**
 * Nombre del archivo: DHT11ReadingsRep.java
 * Nombre de la clase: DHT11ReadingsRep
 * Autor: Rocha Ronquillo Luis Angel
 * Fecha de creación: 2024-11-10
 * Descripción: Esta clase representa el repositorio de lecturas del sensor DHT11.
 */
package com.computing.projapi.repositories;

import java.time.LocalDate;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.computing.projapi.entities.DHT11ReadingsEnt;

@Repository
public interface DHT11ReadingsRep extends JpaRepository<DHT11ReadingsEnt, Long> {
    
    //Get the latest reading
    DHT11ReadingsEnt findTopByOrderByIdDesc();

    //Get 10 latest readings
    List<DHT11ReadingsEnt> findTop10ByOrderByIdDesc();

    @Query(nativeQuery = true, value = "SELECT * FROM dht11_readings d WHERE d.date::DATE = CURRENT_DATE AND d.temperature = (SELECT MAX(d2.temperature) FROM dht11_readings d2 WHERE d2.date::DATE = CURRENT_DATE) LIMIT 1")
    DHT11ReadingsEnt findMaxTemperatureByDate(@Param("date") LocalDate date);

    @Query(nativeQuery = true, value = "SELECT * FROM dht11_readings d WHERE d.date::DATE = CURRENT_DATE AND d.temperature = (SELECT MIN(d2.temperature) FROM dht11_readings d2 WHERE d2.date::DATE = CURRENT_DATE) LIMIT 1")
    DHT11ReadingsEnt findMinTemperatureDate(@Param("date") LocalDate date);
}