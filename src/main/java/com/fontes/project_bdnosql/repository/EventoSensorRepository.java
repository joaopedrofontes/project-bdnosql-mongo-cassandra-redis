package com.fontes.project_bdnosql.repository;

import com.fontes.project_bdnosql.entity.EventoSensor;
import org.springframework.data.cassandra.repository.CassandraRepository;
import org.springframework.stereotype.Repository;

import java.util.UUID;

@Repository
public interface EventoSensorRepository extends CassandraRepository<EventoSensor, UUID> {

}
