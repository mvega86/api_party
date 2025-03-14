package com.futbol.api_party.controller;

import com.futbol.api_party.mapper.dto.MatchDTO;
import com.futbol.api_party.exception.EntityNotFoundException;
import com.futbol.api_party.service.IMatchService;
import jakarta.validation.Valid;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/matches")
@Slf4j
public class MatchController {

    private final IMatchService matchService;

    public MatchController(IMatchService matchService) {
        this.matchService = matchService;
    }

    @PostMapping
    public MatchDTO createMatch(@Valid @RequestBody MatchDTO matchDTO) {
        log.info("Request to create match: {}", matchDTO);
        return matchService.createMatch(matchDTO);
    }

    @GetMapping
    public List<MatchDTO> getAllMatches() {
        log.info("Request to fetch all matches");
        return matchService.getAllMatches();
    }

    @GetMapping("/{matchId}")
    public MatchDTO getMatchById(@PathVariable Long matchId) {
        log.info("Request to fetch match with ID: {}", matchId);
        return matchService.getMatchById(matchId);
    }
}
