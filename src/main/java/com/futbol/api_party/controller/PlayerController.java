package com.futbol.api_party.controller;

import com.futbol.api_party.mapper.dto.PlayerDTO;
import com.futbol.api_party.service.IPlayerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
// =========================
// CONTROLADOR PlayerController
// =========================
@RestController
@RequestMapping("/api/v1/players")
public class PlayerController {

    @Autowired
    private IPlayerService playerService;

    @GetMapping
    public ResponseEntity<List<PlayerDTO>> getAll() {
        return ResponseEntity.ok(playerService.getAll());
    }

    @GetMapping("/{id}")
    public ResponseEntity<PlayerDTO> getById(@PathVariable Long id) {
        PlayerDTO player = playerService.getById(id);
        return player != null ? ResponseEntity.ok(player) : ResponseEntity.notFound().build();
    }

    @PostMapping
    public ResponseEntity<PlayerDTO> save(@RequestBody PlayerDTO playerDTO) {
        return ResponseEntity.ok(playerService.save(playerDTO));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable Long id) {
        playerService.delete(id);
        return ResponseEntity.noContent().build();
    }
}
