/**
 * Nombre del archivo: LEDSController.java
 * Nombre de la clase: LEDSController
 * Autor: Rocha Ronquillo Luis Angel
 * Fecha de creación: 2024-11-10
 * Descripción: Esta clase representa el controlador de los estados del LED
 * que simulan el estado de encendido y apagado de un horno.
 */
package com.computing.projapi.controllers;

import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.computing.projapi.services.LEDService;

@CrossOrigin
@RequestMapping("/api/v1/dht11readings")
@RestController
public class LEDSController {

    @Autowired
    private LEDService ledService;

    @GetMapping("/state")
    public ResponseEntity<String> getLEDState(){
        return ledService.getLEDState();
    }

    @PostMapping("/state")
    public ResponseEntity<Void> setLEDState(@RequestBody Map<String, String> payload){
        return ledService.setLEDState(payload);
    }

}
