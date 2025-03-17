package com.futbol.api_party.controller;

import com.futbol.api_party.mapper.dto.PlayerMatchDTO;
import com.futbol.api_party.exception.EntityNotFoundException;
import com.futbol.api_party.service.IPlayerMatchService;
import jakarta.validation.Valid;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/player-matches")
@Slf4j
public class PlayerMatchController {

    private final IPlayerMatchService playerMatchService;

    public PlayerMatchController(IPlayerMatchService playerMatchService) {
        this.playerMatchService = playerMatchService;
    }

    @PostMapping
    public List<PlayerMatchDTO> assignPlayersToMatch(@Valid @RequestBody List<PlayerMatchDTO> playerMatchDTOs) {
        log.info("Assigning {} players to matches", playerMatchDTOs.size());

        return playerMatchDTOs.stream()
                .map(dto -> {
                    log.info("Assigning player {} to match {}", dto.getPlayerId(), dto.getMatchId());
                    return playerMatchService.assignPlayerToMatch(dto);
                })
                .toList();
    }

    @GetMapping("/{matchId}")
    public List<PlayerMatchDTO> getPlayersByMatch(@PathVariable Long matchId) {
        log.info("Request to fetch players for match ID: {}", matchId);
        return playerMatchService.getPlayersByMatch(matchId);
    }
}

