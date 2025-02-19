package com.fontes.project_bdnosql.repository;

import com.fontes.project_bdnosql.entity.Viagem;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ViagemRepository extends MongoRepository<Viagem, String> {
    List<Viagem> findByUserId(String userId);
}
