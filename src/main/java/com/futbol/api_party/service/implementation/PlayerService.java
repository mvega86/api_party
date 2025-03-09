package com.futbol.api_party.service.implementation;

import com.futbol.api_party.mapper.PlayerMapper;
import com.futbol.api_party.mapper.dto.PlayerDTO;
import com.futbol.api_party.persistence.entity.Player;
import com.futbol.api_party.persistence.entity.Team;
import com.futbol.api_party.persistence.repository.PlayerRepository;
import com.futbol.api_party.persistence.repository.TeamRepository;
import com.futbol.api_party.service.IPlayerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
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
    public PlayerDTO save(PlayerDTO playerDTO) {
        Team team = null;

        // Verificamos si el DTO tiene un ID de equipo asociado y lo buscamos en la BD
        if (playerDTO.getTeamId() != null) {
            team = teamRepository.findById(playerDTO.getTeamId()).orElse(null);
        }

        // Convertimos el DTO a entidad pasándole el equipo encontrado
        Player player = playerMapper.toEntity(playerDTO, team);

        // Guardamos el jugador y devolvemos el DTO
        return playerMapper.toDTO(playerRepository.save(player));

    }

    @Override
    public List<PlayerDTO> getAll() {
        return playerRepository.findAll().stream().map(playerMapper::toDTO).collect(Collectors.toList());
    }

    @Override
    public PlayerDTO getById(Long id) {
        return playerRepository.findById(id).map(playerMapper::toDTO).orElse(null);
    }

    @Override
    public void delete(Long id) {
        playerRepository.deleteById(id);
    }
}
