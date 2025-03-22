package com.futbol.api_party.controller;

import com.futbol.api_party.mapper.dto.StatisticDTO;
import com.futbol.api_party.mapper.dto.TeamDTO;
import com.futbol.api_party.service.ITeamService;
import jakarta.validation.Valid;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

// =========================
// CONTROLADOR TeamController
// =========================
@Slf4j
@CrossOrigin(origins = "http://localhost:5173")
@RestController
@RequestMapping("/api/v1/teams")
public class TeamController {

    private ITeamService teamService;

    public TeamController(ITeamService teamService) {
        this.teamService = teamService;
    }

    @GetMapping
    public ResponseEntity<List<TeamDTO>> getAll() {
        log.info("Request received to search teams...");
        return ResponseEntity.ok(teamService.getAll());
    }

    @GetMapping("/{id}")
    public ResponseEntity<TeamDTO> getById(@PathVariable Long id) {
        log.debug("Request received to search team with ID: {}", id);
        TeamDTO team = teamService.getById(id);
        return team != null ? ResponseEntity.ok(team) : ResponseEntity.notFound().build();
    }

    @PostMapping
    public ResponseEntity<TeamDTO> save(@Valid @RequestBody TeamDTO teamDTO) {
        log.info("Request received to save team: {}", teamDTO.getName());
        return ResponseEntity.ok(teamService.save(teamDTO));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable Long id) {
        log.debug("Request received to delete team with ID: {}", id);
        teamService.delete(id);
        return ResponseEntity.noContent().build();
    }

    @PutMapping
    public TeamDTO update(@Valid @RequestBody TeamDTO teamDTO) {
        log.info("Request to update team...");
        TeamDTO teamDTOOut = teamService.updateTeam(teamDTO);
        log.info("Team updated.");
        return teamDTOOut;
    }
}
