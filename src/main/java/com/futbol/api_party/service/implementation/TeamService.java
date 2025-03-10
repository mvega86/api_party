package com.futbol.api_party.service.implementation;

import com.futbol.api_party.exception.EntityNotFoundException;
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
    @Autowired
    private TeamRepository teamRepository;

    @Autowired
    private PlayerRepository playerRepository;

    @Autowired
    private TeamMapper teamMapper;

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
        List<Player> players = new ArrayList<>();

        // If the DTO has player IDs, we look them up in the DB
        if (teamDTO.getPlayerIds() != null) {
            players = playerRepository.findAllById(teamDTO.getPlayerIds());
        }

        // Check for missing IDs in the response
        if (players.size() != teamDTO.getPlayerIds().size()) {
            List<Long> foundIds = players.stream().map(Player::getId).toList();
            List<Long> missingIds = teamDTO.getPlayerIds().stream()
                    .filter(id -> !foundIds.contains(id))
                    .toList();
            log.error("Some players do not exist: {}", missingIds);
            throw new EntityNotFoundException("The following players do not exist: " + missingIds);
        }

        try {
            // Converting the DTO to an entity with the players found
            Team team = teamMapper.toEntity(teamDTO, players);
            team = teamRepository.save(team);

            // Assigning the team to each player and update them
            for (Player player : players) {
                player.setTeam(team);
            }
            playerRepository.saveAll(players);
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
