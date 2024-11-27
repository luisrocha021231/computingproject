/**
 * Nombre del archivo: DHT11ReadingsEnt.java
 * Nombre de la clase: DHT11ReadingsEnt
 * Autor: Rocha Ronquillo Luis Angel
 * Fecha de creación: 2024-11-10
 * Descripción: Esta clase representa la entidad de lecturas del sensor DHT11.
 */
package com.computing.projapi.entities;

import java.time.LocalDate;

import jakarta.persistence.*;

@Entity
@Table(name = "dht11_readings")
public class DHT11ReadingsEnt {
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(name = "temperature")
    private String temperature;
    @Column(name = "humidity")
    private String humidity;
    @Column(name = "date")
    private LocalDate date;

    public DHT11ReadingsEnt() {
    }

    public DHT11ReadingsEnt(String temperature, String humidity, LocalDate date) {
        this.temperature = temperature;
        this.humidity = humidity;
        this.date = date;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getTemperature() {
        return temperature;
    }

    public void setTemperature(String temperature) {
        this.temperature = temperature;
    }

    public String getHumidity() {
        return humidity;
    }

    public void setHumidity(String humidity) {
        this.humidity = humidity;
    }

    public LocalDate getDate() {
        return date;
    }

    public void setDate(LocalDate date) {
        this.date = date;
    }

}
