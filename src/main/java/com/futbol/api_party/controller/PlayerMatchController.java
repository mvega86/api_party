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
@RequestMapping("/api/player-matches")
@Slf4j
public class PlayerMatchController {

    private final IPlayerMatchService playerMatchService;

    public PlayerMatchController(IPlayerMatchService playerMatchService) {
        this.playerMatchService = playerMatchService;
    }

    @PostMapping
    public PlayerMatchDTO assignPlayerToMatch(@Valid @RequestBody PlayerMatchDTO playerMatchDTO) {
        log.info("Request to assign player {} to match {}", playerMatchDTO.getPlayerId(), playerMatchDTO.getMatchId());
        return playerMatchService.assignPlayerToMatch(playerMatchDTO);
    }

    @GetMapping("/{matchId}")
    public List<PlayerMatchDTO> getPlayersByMatch(@PathVariable Long matchId) {
        log.info("Request to fetch players for match ID: {}", matchId);
        return playerMatchService.getPlayersByMatch(matchId);
    }
}

