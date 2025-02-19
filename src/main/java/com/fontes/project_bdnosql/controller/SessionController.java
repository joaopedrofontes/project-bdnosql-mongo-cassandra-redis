package com.fontes.project_bdnosql.controller;

import com.fontes.project_bdnosql.entity.UserSession;
import com.fontes.project_bdnosql.service.SessionService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;
import java.util.Optional;

@RestController
@RequestMapping(value = "/sessions")
public class SessionController {
    private final SessionService sessionService;

    public SessionController(SessionService sessionService) {
        this.sessionService = sessionService;
    }

    @PostMapping("/create")
    public ResponseEntity<String> createSession(@RequestParam String userId, @RequestParam String username, @RequestParam String device) {
        sessionService.createSession(userId, username, device);
        return ResponseEntity.ok("Sessão criada para o usuário " + userId);
    }

    // Listar todas as sessões ativas
    @GetMapping("/active")
    public ResponseEntity<List<Map<String, String>>> getActiveSessions() {
        return ResponseEntity.ok(sessionService.getAllSessions());
    }

    // Encerrar sessão manualmente
    @DeleteMapping("/delete/{userId}")
    public ResponseEntity<String> deleteSession(@PathVariable String userId) {
        boolean deleted = sessionService.deleteSession(userId);
        if (deleted) {
            return ResponseEntity.ok("Sessão do usuário " + userId + " encerrada.");
        }
        return ResponseEntity.badRequest().body("Sessão não encontrada.");
    }
}
