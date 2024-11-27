/**
 * Nombre del archivo: LEDService.java
 * Nombre de la clase: LEDService
 * Autor: Rocha Ronquillo Luis Angel
 * Fecha de creación: 2024-11-10
 * Descripción: Esta clase representa el servicio de control de un LED que simula el 
 * estado de encendido y apagado de un horno.
 */
package com.computing.projapi.services;

import java.util.Map;

import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

@Service
public class LEDService {
    
    private boolean isLEDOn = false;

    public ResponseEntity<Void> setLEDState(Map<String, String> payload) {
        
        String state = payload.get("state");
        if("ON".equalsIgnoreCase(state)){
            isLEDOn = true;
            return ResponseEntity.ok().build();
        } else if("OFF".equalsIgnoreCase(state)){
            isLEDOn = false;
            return ResponseEntity.ok().build();
        } else {
            return ResponseEntity.badRequest().build();
        }
    }

    public ResponseEntity<String> getLEDState() {
        return ResponseEntity.ok(isLEDOn ? "ON" : "OFF");
    }
}