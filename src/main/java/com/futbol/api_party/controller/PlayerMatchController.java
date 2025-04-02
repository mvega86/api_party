package com.futbol.api_party.controller;

import com.futbol.api_party.mapper.dto.PlayerMatchDTO;
import com.futbol.api_party.exception.EntityNotFoundException;
import com.futbol.api_party.service.IPlayerMatchService;
import jakarta.validation.Valid;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/api/v1/player-matches")
@Slf4j
public class PlayerMatchController {

    private final IPlayerMatchService playerMatchService;

    public PlayerMatchController(IPlayerMatchService playerMatchService) {
        this.playerMatchService = playerMatchService;
    }

    @PostMapping
    public ResponseEntity<Map<String, Object>> assignPlayersToMatch(@Valid @RequestBody List<PlayerMatchDTO> playerMatchDTOs) {
        log.info("Assigning {} players to matches", playerMatchDTOs.size());

        List<PlayerMatchDTO> playerMatchDTOList  = playerMatchDTOs.stream()
                .map(dto -> {
                    log.info("Assigning player {} to match {}", dto.getPlayer().getId(), dto.getMatch().getId());
                    return playerMatchService.assignPlayerToMatch(dto);
                })
                .toList();
        log.info("Player match assigned successfully");
        Map<String, Object> response = new HashMap<>();
        response.put("message", "Player match assigned successfully");
        response.put("data", playerMatchDTOList);

        return ResponseEntity.ok(response);
    }

    @GetMapping("/{id}")
    public ResponseEntity<PlayerMatchDTO> getById(@PathVariable Long id) {
        log.info("Request to fetch players for match ID: {}", id);
        return ResponseEntity.ok(playerMatchService.getById(id));
    }

    @PutMapping()
    public ResponseEntity<Map<String, Object>> updatePlayerMatch(@RequestBody PlayerMatchDTO playerMatchDTO) {
        log.info("Request to update playerMatch, ID: {}", playerMatchDTO.getId());
        PlayerMatchDTO playerMatchDTO1 = playerMatchService.updatePlayerMatch(playerMatchDTO);
        log.info("Player match updated.");
        return ResponseEntity.ok(Map.of(
                "message", "Player match updated successfully!!!",
                "data", playerMatchDTO1
        ));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable Long id) {
        log.debug("Request received to delete player match with ID: {}", id);
        playerMatchService.delete(id);
        return ResponseEntity.noContent().build();
    }

}

