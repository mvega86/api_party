package com.futbol.api_party.controller;

import com.futbol.api_party.mapper.dto.PlayerDTO;
import com.futbol.api_party.mapper.dto.StatisticDTO;
import com.futbol.api_party.service.IPlayerService;
import jakarta.validation.Valid;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

// =========================
// CONTROLADOR PlayerController
// =========================
@Slf4j
@CrossOrigin(origins = "http://localhost:5173")
@RestController
@RequestMapping("/api/v1/players")
public class PlayerController {

    private IPlayerService playerService;

    public PlayerController(IPlayerService playerService) {
        this.playerService = playerService;
    }

    @GetMapping
    public ResponseEntity<List<PlayerDTO>> getAll(@RequestParam(value = "search", required = false) String search) {
        log.info("Request to get all players with search: {}", search);
        List<PlayerDTO> playerDTOList = playerService.searchPlayers(search);
        return ResponseEntity.ok(playerDTOList);
    }

    @GetMapping("/{id}")
    public ResponseEntity<PlayerDTO> getById(@PathVariable Long id) {
        log.debug("Request received to search player with ID: {}", id);
        PlayerDTO player = playerService.getById(id);
        return player != null ? ResponseEntity.ok(player) : ResponseEntity.notFound().build();
    }

    @PostMapping
    public ResponseEntity<Map<String, Object>> save(@Valid @RequestBody PlayerDTO playerDTO) {
        log.info("Request received to save player: {}", playerDTO.getFullName());
        PlayerDTO saved = playerService.save(playerDTO);
        return ResponseEntity.ok(Map.of(
                "message", "Successfully saved player!!!",
                "data", saved
        ));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable Long id) {
        log.debug("Request received to delete player with ID: {}", id);
        playerService.delete(id);
        return ResponseEntity.noContent().build();
    }

    @PutMapping
    public ResponseEntity<Map<String, Object>> update(@Valid @RequestBody PlayerDTO playerDTO) {
        log.info("Request to update player...");
        PlayerDTO playerDTOOut = playerService.updateStatistic(playerDTO);
        log.info("Player updated.");
        return ResponseEntity.ok(Map.of(
                "message", "Successfully updated player!!!",
                "data", playerDTOOut
        ));
    }
}
