package com.futbol.api_party.service.implementation;

import com.futbol.api_party.exception.EntityNotFoundException;
import com.futbol.api_party.mapper.PlayerMapper;
import com.futbol.api_party.mapper.dto.PlayerDTO;
import com.futbol.api_party.persistence.entity.Player;
import com.futbol.api_party.persistence.entity.Team;
import com.futbol.api_party.persistence.repository.PlayerRepository;
import com.futbol.api_party.persistence.repository.TeamRepository;
import com.futbol.api_party.service.IPlayerService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.CrossOrigin;

import java.util.List;
import java.util.stream.Collectors;

@Slf4j
@Service
public class PlayerService implements IPlayerService {
    @Autowired
    private PlayerRepository playerRepository;
    @Autowired
    private TeamRepository teamRepository;
    @Autowired
    private PlayerMapper playerMapper;
    @Override
    @Transactional
    public PlayerDTO save(PlayerDTO playerDTO) {
        /*if (playerDTO.getFullName().equals("error")) {
            throw new RuntimeException("Simulación de error inesperado");
        }*/
        log.info("Saving player: {}", playerDTO.getFullName());

        Team team = null;

        // Checking if the DTO has an associated equipment ID and look for it in the DB
        if (playerDTO.getTeam() != null) {
            team = teamRepository.findById(playerDTO.getTeam().getId()).orElseThrow(
                    () -> {
                        log.error("Team with ID {} not found", playerDTO.getTeam().getId());
                        return new EntityNotFoundException("Team with ID " + playerDTO.getTeam().getId() + " was not found.");
                    });
        }

        try {
            // Converting the DTO to an entity by passing it the equipment found
            Player player = playerMapper.toEntity(playerDTO);
            // Saving the player and return the DTO
            player = playerRepository.save(player);
            log.info("Player saved successfully");
            return playerMapper.toDTO(player);
        } catch (Exception e){
            log.error("Unexpected error saving player: {}", e.getMessage());
            throw new RuntimeException("Error saving player.");
        }

    }

    @Override
    public List<PlayerDTO> getAll() {
        return playerRepository.findAll().stream().map(playerMapper::toDTO).collect(Collectors.toList());
    }

    @Override
    public PlayerDTO getById(Long id) {
        log.info("Searching player with id {}...", id);
        Player player = playerRepository.findById(id)
                .orElseThrow(() -> {
                    log.error("Player with ID {} not found", id);
                    return new EntityNotFoundException("Player with ID " + id + " was not found.");
                });
        log.info("Player found: {}", player.getFullName());
        return playerRepository.findById(id).map(playerMapper::toDTO).orElse(null);
    }

    @Override
    public void delete(Long id) {
        log.info("Searching player with id {} to delete...", id);
        Player player = playerRepository.findById(id)
                .orElseThrow(() -> {
                    log.error("Player with ID {} not found", id);
                    return new EntityNotFoundException("Player with ID " + id + " was not found.");
                });
        playerRepository.deleteById(id);
        log.info("Player {} delete successfully.", player.getFullName());
    }

    @Override
    public PlayerDTO updateStatistic(PlayerDTO playerDTO) {
        log.info("Updating player: {}", playerDTO.getFullName());

        Team team = null;

        // Checking if the DTO has an associated equipment ID and look for it in the DB
        if (playerDTO.getTeam() != null) {
            team = teamRepository.findById(playerDTO.getTeam().getId()).orElseThrow(
                    () -> {
                        log.error("Team with ID {} not found", playerDTO.getTeam().getId());
                        return new EntityNotFoundException("Team with ID " + playerDTO.getTeam().getId() + " was not found.");
                    });
        }

        try {
            // Converting the DTO to an entity by passing it the equipment found
            Player player = playerMapper.toEntity(playerDTO);
            // Saving the player and return the DTO
            player = playerRepository.save(player);
            log.info("Player updated successfully");
            return playerMapper.toDTO(player);
        } catch (Exception e){
            log.error("Unexpected error updating player: {}", e.getMessage());
            throw new RuntimeException("Error updating player.");
        }
    }
}
