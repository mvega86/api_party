package com.futbol.api_party.controller;

import com.futbol.api_party.mapper.dto.PlayerStatisticDTO;
import com.futbol.api_party.service.IPlayerStatisticService;
import jakarta.validation.Valid;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/player-statistics")
@Slf4j
public class PlayerStatisticController {

    private final IPlayerStatisticService playerStatisticService;

    public PlayerStatisticController(IPlayerStatisticService playerStatisticService) {
        this.playerStatisticService = playerStatisticService;
    }

    @PostMapping
    public List<PlayerStatisticDTO> createPlayerStatistics(@Valid @RequestBody List<PlayerStatisticDTO> playerStatisticDTOS) {
        log.info("Assigning {} statistics to players", playerStatisticDTOS.size());

        return playerStatisticDTOS.stream()
                .map(dto -> {
                    log.info("Adding statistic with id '{}' for playerMatch with id {}", dto.getStatisticId(), dto.getPlayerMatchId());
                    return playerStatisticService.createPlayerStatistic(dto);
                })
                .toList();
    }

    @GetMapping("/{playerMatchId}")
    public List<PlayerStatisticDTO> getStatisticsByPlayerMatch(@PathVariable Long playerMatchId) {
        log.info("Request to fetch statistics for player match ID: {}", playerMatchId);
        return playerStatisticService.getStatisticsByPlayerMatch(playerMatchId);
    }
}

