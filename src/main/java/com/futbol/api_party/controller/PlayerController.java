package com.futbol.api_party.controller;

import com.futbol.api_party.mapper.dto.PlayerDTO;
import com.futbol.api_party.service.IPlayerService;
import jakarta.validation.Valid;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

// =========================
// CONTROLADOR PlayerController
// =========================
@Slf4j
@RestController
@RequestMapping("/api/v1/players")
public class PlayerController {

    private IPlayerService playerService;

    public PlayerController(IPlayerService playerService) {
        this.playerService = playerService;
    }

    @GetMapping
    public ResponseEntity<List<PlayerDTO>> getAll() {
        log.info("Request received to search players...");
        return ResponseEntity.ok(playerService.getAll());
    }

    @GetMapping("/{id}")
    public ResponseEntity<PlayerDTO> getById(@PathVariable Long id) {
        log.debug("Request received to search player with ID: {}", id);
        PlayerDTO player = playerService.getById(id);
        return player != null ? ResponseEntity.ok(player) : ResponseEntity.notFound().build();
    }

    @PostMapping
    public ResponseEntity<PlayerDTO> save(@Valid @RequestBody PlayerDTO playerDTO) {
        log.info("Request received to save player: {}", playerDTO.getFullName());
        return ResponseEntity.ok(playerService.save(playerDTO));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable Long id) {
        log.debug("Request received to delete player with ID: {}", id);
        playerService.delete(id);
        return ResponseEntity.noContent().build();
    }
}
