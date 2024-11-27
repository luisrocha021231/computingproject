/**
 * Nombre del archivo: DTH11ReadingsServ.java
 * Nombre de la clase: DTH11ReadingsServ
 * Autor: Rocha Ronquillo Luis Angel
 * Fecha de creación: 2024-11-10
 * Descripción: Esta clase representa el servicio de control de las lecturas del sensor DHT11.
 */
package com.computing.projapi.services;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;

import com.computing.projapi.entities.DHT11ReadingsEnt;
import com.computing.projapi.repositories.DHT11ReadingsRep;

@Service
public class DHT11ReadingsServ {

    private static final Logger logger = LoggerFactory.getLogger(DHT11ReadingsServ.class);

    @Autowired
    private DHT11ReadingsRep dht11ReadingsRepository;

    
    public ResponseEntity<List<DHT11ReadingsEnt>> getAllReadings() {
        
        logger.info("Fetching all readings from the database");
        List<DHT11ReadingsEnt> readings = dht11ReadingsRepository.findAll();

        if(readings.isEmpty()){
            logger.warn("No readings found in the database");
            return ResponseEntity.notFound().build();
        }else{
            logger.info("Readings found in the database");
            return ResponseEntity.ok(readings);
        }
    }

    public ResponseEntity<DHT11ReadingsEnt> getReadingById(@PathVariable Long id) {
        if( id <= 0){
            logger.warn("Invalid reading id");
            return ResponseEntity.badRequest().build();
        }

        logger.info("Fetching reading with id: " + id);
        Optional<DHT11ReadingsEnt> reading = dht11ReadingsRepository.findById(id);

        if(reading.isPresent()){
            logger.info("Reading found in the database");
            return ResponseEntity.ok(reading.get());
        }else {
            logger.warn("Reading not found in the database");
            return ResponseEntity.notFound().build();
        }
    }

    public ResponseEntity<DHT11ReadingsEnt> createReading(@RequestBody DHT11ReadingsEnt reading) {
        
        if(reading.getId() !=null){
            logger.warn("Reading id must be null");
            return ResponseEntity.badRequest().build();
        }

        reading.setDate(LocalDate.now());

        DHT11ReadingsEnt newReading = dht11ReadingsRepository.save(reading);
        logger.info("Reading saved in the database");
        return ResponseEntity.ok(newReading);
    }

    public Optional<DHT11ReadingsEnt> updateReading(@PathVariable Long id, @RequestBody DHT11ReadingsEnt reading) {
        
        logger.info("Updating reading with id: " + id);
        return dht11ReadingsRepository.findById(id).map(read -> {

            read.setTemperature(reading.getTemperature());
            read.setHumidity(reading.getHumidity());
            read.setDate(LocalDate.now());

            logger.info("Reading updated in the database");
            return dht11ReadingsRepository.save(reading);
        });
    }

    public ResponseEntity<DHT11ReadingsEnt> deleteAllReadings(){
        logger.info("Deleting all readings from the database");
        dht11ReadingsRepository.deleteAll();
        logger.info("All readings deleted from the database");
        return ResponseEntity.noContent().build();
    }

    public ResponseEntity<DHT11ReadingsEnt> deleteReadingById(@PathVariable Long id) {
        
        if( id <= 0){
            logger.warn("Invalid reading id");
            return ResponseEntity.badRequest().build();
        }

        logger.info("Deleting reading with id: " + id);
        Optional<DHT11ReadingsEnt> reading = dht11ReadingsRepository.findById(id);

        if(reading.isPresent()){
            dht11ReadingsRepository.deleteById(id);
            logger.info("Reading deleted from the database");
            return ResponseEntity.noContent().build();
        }else {
            logger.warn("Reading not found in the database");
            return ResponseEntity.notFound().build();
        }
    }

    public ResponseEntity<DHT11ReadingsEnt> getCurrentReading(){

        DHT11ReadingsEnt latestReading = dht11ReadingsRepository.findTopByOrderByIdDesc();

        if(latestReading != null){
            logger.info("Latest reading found in the database");
            return ResponseEntity.ok(latestReading);
        } else {
            logger.warn("No readings found in the database");
            return ResponseEntity.notFound().build();
        }
    }

    public ResponseEntity<List<DHT11ReadingsEnt>> getLatest10Readings(){
        
        logger.info("Fetching 10 latest readings from the database");
        List<DHT11ReadingsEnt> readings = dht11ReadingsRepository.findTop10ByOrderByIdDesc();

        if(readings.isEmpty()){
            logger.warn("No readings found in the database");
            return ResponseEntity.notFound().build();
        }else{
            logger.info("Readings found in the database");
            return ResponseEntity.ok(readings);
        }
    }

    public ResponseEntity<DHT11ReadingsEnt> getMaxTemperature(LocalDate date){
        logger.info("Find max temperature reading on "+date);
        DHT11ReadingsEnt max = dht11ReadingsRepository.findMaxTemperatureByDate(date);
        return ResponseEntity.ok(max);
    }

    public ResponseEntity<DHT11ReadingsEnt> getMinTemperature(LocalDate date){
        logger.info("Find min temperature reading on "+date);
        DHT11ReadingsEnt min = dht11ReadingsRepository.findMinTemperatureDate(date);
        return ResponseEntity.ok(min);
    }
}