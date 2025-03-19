package com.futbol.api_party.service;

import com.futbol.api_party.mapper.dto.TeamDTO;

import java.util.List;

// =========================
// SERVICIO ITeamService
// =========================
public interface ITeamService {
    List<TeamDTO> getAll();
    TeamDTO getById(Long id);
    TeamDTO save(TeamDTO teamDTO);
    void delete(Long id);
    TeamDTO updateTeam(TeamDTO teamDTO);
}
