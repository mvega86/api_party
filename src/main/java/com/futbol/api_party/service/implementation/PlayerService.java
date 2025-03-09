package com.futbol.api_party.service.implementation;

import com.futbol.api_party.exception.EntityNotFoundException;
import com.futbol.api_party.mapper.PlayerMapper;
import com.futbol.api_party.mapper.dto.PlayerDTO;
import com.futbol.api_party.persistence.entity.Player;
import com.futbol.api_party.persistence.entity.Team;
import com.futbol.api_party.persistence.repository.PlayerRepository;
import com.futbol.api_party.persistence.repository.TeamRepository;
import com.futbol.api_party.service.IPlayerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;
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

        Team team = null;

        // Checking if the DTO has an associated equipment ID and look for it in the DB
        if (playerDTO.getTeamId() != null) {
            team = teamRepository.findById(playerDTO.getTeamId()).orElseThrow(
                    () -> new EntityNotFoundException("Team with ID " + playerDTO.getTeamId() + " was not found.")
            );
        }

        // Converting the DTO to an entity by passing it the equipment found
        Player player = playerMapper.toEntity(playerDTO, team);

        // Saving the player and return the DTO
        return playerMapper.toDTO(playerRepository.save(player));

    }

    @Override
    public List<PlayerDTO> getAll() {
        return playerRepository.findAll().stream().map(playerMapper::toDTO).collect(Collectors.toList());
    }

    @Override
    public PlayerDTO getById(Long id) {
        Player player = playerRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Player with ID " + id + " was not found."));
        return playerRepository.findById(id).map(playerMapper::toDTO).orElse(null);
    }

    @Override
    public void delete(Long id) {
        Player player = playerRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Player with ID " + id + " was not found."));
        playerRepository.deleteById(id);
    }
}
