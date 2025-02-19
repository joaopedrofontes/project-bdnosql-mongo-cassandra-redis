package com.fontes.project_bdnosql.repository;

import com.fontes.project_bdnosql.entity.Viagem;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ViagemRepository extends MongoRepository<Viagem, String> {

}
