package com.fontes.project_bdnosql.service;


import com.fontes.project_bdnosql.entity.Viagem;
import com.fontes.project_bdnosql.repository.ViagemRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class ViagemService {

    private final ViagemRepository viagemRepository;

    public ViagemService(ViagemRepository viagemRepository) {
        this.viagemRepository = viagemRepository;
    }

    public Viagem saveViagem(Viagem viagem) {
        return viagemRepository.save(viagem);
    }

    public Optional<Viagem> findById(String id) {
        return viagemRepository.findById(id);
    }

    public List<Viagem> findByUserId(String userId) {
        return viagemRepository.findByUserId(userId);
    }

    public List<Viagem> findAll() {
        return viagemRepository.findAll();
    }

}
