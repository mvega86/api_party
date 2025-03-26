package com.futbol.api_party.controller;

import com.futbol.api_party.mapper.dto.PlayerStatisticDTO;
import com.futbol.api_party.service.IPlayerStatisticService;
import jakarta.validation.Valid;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/api/v1/player-statistics")
@Slf4j
public class PlayerStatisticController {

    private final IPlayerStatisticService playerStatisticService;

    public PlayerStatisticController(IPlayerStatisticService playerStatisticService) {
        this.playerStatisticService = playerStatisticService;
    }

    @PostMapping
    public ResponseEntity<Map<String, Object>> createPlayerStatistics(@Valid @RequestBody List<PlayerStatisticDTO> playerStatisticDTOS) {
        log.info("Assigning {} statistics to players", playerStatisticDTOS.size());

        List<PlayerStatisticDTO> createdStats = playerStatisticDTOS.stream()
                .map(dto -> {
                    log.info("Adding statistic with id '{}' for playerMatch with id {}", dto.getStatistic().getId(), dto.getPlayerMatch().getId());
                    return playerStatisticService.createPlayerStatistic(dto);
                })
                .toList();
        log.info("Statistics player assigned successfully!!!");
        Map<String, Object> response = new HashMap<>();
        response.put("message", "Statistics assigned successfully!!!");
        response.put("data", createdStats);

        return ResponseEntity.ok(response);
    }

    @GetMapping("/{playerMatchId}")
    public List<PlayerStatisticDTO> getStatisticsByPlayerMatch(@PathVariable Long playerMatchId) {
        log.info("Request to fetch statistics for player match ID: {}", playerMatchId);
        return playerStatisticService.getStatisticsByPlayerMatch(playerMatchId);
    }
}

