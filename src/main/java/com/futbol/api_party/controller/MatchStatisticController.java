package com.futbol.api_party.controller;

import com.futbol.api_party.mapper.dto.MatchStatisticDTO;
import com.futbol.api_party.exception.EntityNotFoundException;
import com.futbol.api_party.service.IMatchStatisticService;
import jakarta.validation.Valid;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/match-statistics")
@Slf4j
public class MatchStatisticController {

    private final IMatchStatisticService matchStatisticService;

    public MatchStatisticController(IMatchStatisticService matchStatisticService) {
        this.matchStatisticService = matchStatisticService;
    }

    @PostMapping
    public MatchStatisticDTO createMatchStatistic(@Valid @RequestBody MatchStatisticDTO matchStatisticDTO) {
        log.info("Request to create statistic '{}' for player match {}",
                matchStatisticDTO.getStatisticName(), matchStatisticDTO.getPlayerMatchId());
        return matchStatisticService.createMatchStatistic(matchStatisticDTO);
    }

    @GetMapping("/{playerMatchId}")
    public List<MatchStatisticDTO> getStatisticsByPlayerMatch(@PathVariable Long playerMatchId) {
        log.info("Request to fetch statistics for player match ID: {}", playerMatchId);
        return matchStatisticService.getStatisticsByPlayerMatch(playerMatchId);
    }
}

