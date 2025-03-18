package com.futbol.api_party.service.implementation;

import com.futbol.api_party.exception.EntityNotFoundException;
import com.futbol.api_party.mapper.PlayerMapper;
import com.futbol.api_party.mapper.TeamMapper;
import com.futbol.api_party.mapper.dto.TeamDTO;
import com.futbol.api_party.persistence.entity.Player;
import com.futbol.api_party.persistence.entity.Team;
import com.futbol.api_party.persistence.repository.PlayerRepository;
import com.futbol.api_party.persistence.repository.TeamRepository;
import com.futbol.api_party.service.ITeamService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

// =========================
// SERVICIO TeamService
// =========================
@Slf4j
@Service
public class TeamService implements ITeamService {

    private TeamRepository teamRepository;

    private PlayerRepository playerRepository;

    private TeamMapper teamMapper;

    private PlayerMapper playerMapper;

    public TeamService(TeamRepository teamRepository, PlayerRepository playerRepository, TeamMapper teamMapper, PlayerMapper playerMapper) {
        this.teamRepository = teamRepository;
        this.playerRepository = playerRepository;
        this.teamMapper = teamMapper;
        this.playerMapper = playerMapper;
    }

    @Override
    public List<TeamDTO> getAll() {
        return teamRepository.findAll().stream().map(teamMapper::toDTO).collect(Collectors.toList());
    }

    @Override
    public TeamDTO getById(Long id) {
        log.info("Searching team with id {}...", id);
        Team team = teamRepository.findById(id)
                .orElseThrow(() -> {
                    log.error("Team with ID {} not found", id);
                    return new EntityNotFoundException("Team with ID " + id + " was not found.");
                });
        log.info("Team found: {}...", team.getName());
        return teamRepository.findById(id).map(teamMapper::toDTO).orElse(null);
    }

    @Override
    @Transactional
    public TeamDTO save(TeamDTO teamDTO) {
        log.info("Saving team: {}", teamDTO.getName());

        try {
            // Converting the DTO to an entity with the players found
            Team team = teamMapper.toEntity(teamDTO);
            team = teamRepository.save(team);

            log.info("Team saved successfully");
            return teamMapper.toDTO(team);
        } catch (Exception e){
            log.error("Unexpected error saving team: {}", e.getMessage(), e);
            throw new RuntimeException("Error saving team.");
        }

    }

    @Override
    public void delete(Long id) {
        log.info("Searching team with id {} to delete...", id);
        Team team = teamRepository.findById(id)
                .orElseThrow(() -> {
                    log.error("Team with ID {} not found", id);
                    return new EntityNotFoundException("Team with ID " + id + " was not found.");

                });
        teamRepository.deleteById(id);
        log.info("Team {} delete successfully.", team.getName());
    }
}
