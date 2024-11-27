/**
 * Nombre del archivo: DHT11ReadingsCont.java
 * Nombre de la clase: DHT11ReadingsCont
 * Autor: Rocha Ronquillo Luis Angel
 * Fecha de creación: 2024-11-10
 * Descripción: Esta clase representa el controlador de las lecturas del sensor DHT11.
 */
package com.computing.projapi.controllers;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.computing.projapi.entities.DHT11ReadingsEnt;
import com.computing.projapi.services.DHT11ReadingsServ;

@CrossOrigin
@RequestMapping("/api/v1/dht11readings")
@RestController
public class DHT11ReadingsCont {

    @Autowired
    private DHT11ReadingsServ dht11ReadingsService;

    @GetMapping
    public ResponseEntity<List<DHT11ReadingsEnt>> getAllReadings() {
        return dht11ReadingsService.getAllReadings();
    }

    @GetMapping("/{id}")
    public ResponseEntity<DHT11ReadingsEnt> getReadingById(@PathVariable Long id) {
        return dht11ReadingsService.getReadingById(id);
    }

    @PostMapping
    public ResponseEntity<DHT11ReadingsEnt> createReading(@RequestBody DHT11ReadingsEnt reading) {
        return dht11ReadingsService.createReading(reading);
    }

    @PutMapping("/{id}")
    public ResponseEntity<DHT11ReadingsEnt> updateReading(@PathVariable Long id, @RequestBody DHT11ReadingsEnt reading) {
         Optional<DHT11ReadingsEnt> updatedReading = dht11ReadingsService.updateReading(id, reading);
         return updatedReading.map(ResponseEntity::ok).orElseGet(() -> ResponseEntity.notFound().build());
    }

    @DeleteMapping
    public ResponseEntity<DHT11ReadingsEnt> deleteAllReadings(){
        return dht11ReadingsService.deleteAllReadings();
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<DHT11ReadingsEnt> deleteReadingById(@PathVariable Long id) {
        return dht11ReadingsService.deleteReadingById(id);
    }

    @GetMapping("/latest")
    public ResponseEntity<DHT11ReadingsEnt> getLatestReading() {
        return dht11ReadingsService.getCurrentReading();
    }

    @GetMapping("/latest10")
    public ResponseEntity<List<DHT11ReadingsEnt>> getLatest10Readings() {
        return dht11ReadingsService.getLatest10Readings();
    }

    @GetMapping("/max")
    public ResponseEntity<DHT11ReadingsEnt> getMaxTemperature(LocalDate date){
        return dht11ReadingsService.getMaxTemperature(date);
    }

    @GetMapping("/min")
    public ResponseEntity<DHT11ReadingsEnt> getMinTemperature(LocalDate date){
        return dht11ReadingsService.getMinTemperature(date);
    }
    
}