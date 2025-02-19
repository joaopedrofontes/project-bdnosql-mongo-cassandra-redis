package com.fontes.project_bdnosql.controller;


import com.fontes.project_bdnosql.entity.EventoSensor;
import com.fontes.project_bdnosql.service.EventoSensorService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@RestController
@RequestMapping(value = "/eventos")
public class EventoSensorController {

    @Autowired
    private EventoSensorService eventoSensorService;

    @PostMapping(value = "")
    public ResponseEntity<EventoSensor> saveEventoSensor(@RequestBody EventoSensor eventoSensor) {
        EventoSensor eventoSensorSaved = eventoSensorService.saveEventoSensor(eventoSensor);
        return ResponseEntity.ok(eventoSensorSaved);
    }

    @GetMapping(value = "/{sensorId}")
    public ResponseEntity<EventoSensor> findEventoSensorById(@PathVariable UUID sensorId) {
        return eventoSensorService.findById(sensorId)
                .map(ResponseEntity::ok)
                .orElseGet(() -> ResponseEntity.notFound().build());
    }

    @GetMapping
    public ResponseEntity<List<EventoSensor>> listarEventos() {
        return ResponseEntity.ok(eventoSensorService.findAll());
    }

}
