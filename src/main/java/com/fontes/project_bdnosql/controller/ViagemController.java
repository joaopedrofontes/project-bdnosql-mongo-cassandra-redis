package com.fontes.project_bdnosql.controller;


import com.fontes.project_bdnosql.entity.Viagem;
import com.fontes.project_bdnosql.service.ViagemService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping(value = "/viagens")
public class ViagemController {

    private final ViagemService viagemService;


    public ViagemController(ViagemService viagemService) {
        this.viagemService = viagemService;
    }

    @PostMapping
    public ResponseEntity<Viagem> saveViagem(@RequestBody Viagem viagem) {
        Viagem savedViagem = viagemService.saveViagem(viagem);

        return ResponseEntity.ok(savedViagem);
    }

    @GetMapping
    public ResponseEntity<List<Viagem>> findAllViagem() {
        List<Viagem> viagens = viagemService.findAll();
        return ResponseEntity.ok(viagens);
    }

    @GetMapping(value = "/{id}")
    public ResponseEntity<Viagem> findViagemById(@PathVariable("id") String id) {
        Optional<Viagem> viagem = viagemService.findById(id);
        return viagem.map(ResponseEntity::ok).orElseGet(() -> ResponseEntity.notFound().build());
    }

    @GetMapping(value = "/user/{id}")
    public ResponseEntity<List<Viagem>> findViagemByUser(@PathVariable("id") String userId) {
        List<Viagem> viagens = viagemService.findByUserId(userId);
        return ResponseEntity.ok(viagens);
    }
}
