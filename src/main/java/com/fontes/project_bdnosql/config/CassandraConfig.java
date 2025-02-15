package com.fontes.project_bdnosql.config;

import com.datastax.oss.driver.api.core.CqlSession;
import jakarta.annotation.PostConstruct;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.cassandra.config.AbstractCassandraConfiguration;
import org.springframework.data.cassandra.config.SchemaAction;
import org.springframework.data.cassandra.core.cql.keyspace.CreateKeyspaceSpecification;
import org.springframework.data.cassandra.core.cql.keyspace.KeyspaceOption;

import java.util.Collections;
import java.util.List;

@Configuration
public class CassandraConfig extends AbstractCassandraConfiguration {


    @Override
    protected String getKeyspaceName() {
        return "transporte_events";
    }

    @Override
    public SchemaAction getSchemaAction() {
        return SchemaAction.CREATE_IF_NOT_EXISTS;
    }

    @Override
    protected List<CreateKeyspaceSpecification> getKeyspaceCreations() {
        return Collections.singletonList(CreateKeyspaceSpecification.createKeyspace(getKeyspaceName())
                .ifNotExists()
                .with(KeyspaceOption.DURABLE_WRITES, true)
                .withSimpleReplication());
    }

    @PostConstruct
    public void createTableIfNotExists() {

        try (CqlSession session = CqlSession.builder()
                .withKeyspace(getKeyspaceName())
                .build()) {

            String createTableQuery = "CREATE TABLE IF NOT EXISTS eventos_sensores ("
                    + "sensor_id UUID PRIMARY KEY, "
                    + "timestamp TIMESTAMP, "
                    + "velocidade FLOAT, "
                    + "aceleracao FLOAT, "
                    + "tipo_evento TEXT);";

            session.execute(createTableQuery);
        } catch (Exception e) {
            System.err.println("Erro ao criar a tabela: " + e.getMessage());
        }
    }

}