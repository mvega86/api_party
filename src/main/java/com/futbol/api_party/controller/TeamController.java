package com.futbol.api_party.controller;

import com.futbol.api_party.mapper.dto.TeamDTO;
import com.futbol.api_party.service.ITeamService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

// =========================
// CONTROLADOR TeamController
// =========================
@RestController
@RequestMapping("/api/v1/teams")
public class TeamController {
    @Autowired
    private ITeamService teamService;

    @GetMapping
    public ResponseEntity<List<TeamDTO>> getAll() {
        return ResponseEntity.ok(teamService.getAll());
    }

    @GetMapping("/{id}")
    public ResponseEntity<TeamDTO> getById(@PathVariable Long id) {
        TeamDTO team = teamService.getById(id);
        return team != null ? ResponseEntity.ok(team) : ResponseEntity.notFound().build();
    }

    @PostMapping
    public ResponseEntity<TeamDTO> save(@RequestBody TeamDTO teamDTO) {
        return ResponseEntity.ok(teamService.save(teamDTO));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable Long id) {
        teamService.delete(id);
        return ResponseEntity.noContent().build();
    }
}
