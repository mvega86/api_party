package com.futbol.api_party.controller;

import com.futbol.api_party.mapper.dto.MatchDTO;
import com.futbol.api_party.exception.EntityNotFoundException;
import com.futbol.api_party.service.IMatchService;
import jakarta.validation.Valid;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/api/v1/matches")
@Slf4j
public class MatchController {

    private final IMatchService matchService;

    public MatchController(IMatchService matchService) {
        this.matchService = matchService;
    }

    @PostMapping
    public ResponseEntity<Map<String, Object>> createMatch(@Valid @RequestBody MatchDTO matchDTO) {
        log.info("Request to create match: {}", matchDTO);
        MatchDTO saved = matchService.createMatch(matchDTO);
        return ResponseEntity.ok(Map.of(
                "message", "Successfully saved match!!!",
                "data", saved
        ));
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

    @PutMapping("/{matchId}")
    public ResponseEntity<Map<String, Object>> updateMatch(@PathVariable @RequestBody MatchDTO matchDTO) {
        log.info("Request to update match: {}", matchDTO.getHomeTeam()+" vs "+matchDTO.getAwayTeam());
        MatchDTO updated = matchService.updateMatchTimes(matchDTO);
        log.info("Match updated.");
        return ResponseEntity.ok(Map.of(
                "message", "Successfully updated match!!!",
                "data", updated
        ));
    }
}
