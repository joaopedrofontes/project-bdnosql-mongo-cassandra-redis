package com.fontes.project_bdnosql.service;


import com.fontes.project_bdnosql.entity.EventoSensor;
import com.fontes.project_bdnosql.repository.EventoSensorRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Service
public class EventoSensorService {

    @Autowired
    private EventoSensorRepository eventoSensorRepository;

    public EventoSensor saveEventoSensor(EventoSensor eventoSensor) {
        if(eventoSensor.getSensorId() == null) {
            eventoSensor.setSensorId(UUID.randomUUID());
        }

        if(eventoSensor.getTimestamp() == null) {
            eventoSensor.setTimestamp(new Date());
        }

        return eventoSensorRepository.save(eventoSensor);
    }

    public Optional<EventoSensor> findById(UUID id) {
        return eventoSensorRepository.findById(id);
    }

    public List<EventoSensor> findAll() {
        return eventoSensorRepository.findAll();
    }

}
