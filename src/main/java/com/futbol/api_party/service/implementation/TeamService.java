package com.futbol.api_party.service.implementation;

import com.futbol.api_party.mapper.TeamMapper;
import com.futbol.api_party.mapper.dto.TeamDTO;
import com.futbol.api_party.persistence.entity.Player;
import com.futbol.api_party.persistence.entity.Team;
import com.futbol.api_party.persistence.repository.PlayerRepository;
import com.futbol.api_party.persistence.repository.TeamRepository;
import com.futbol.api_party.service.ITeamService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

// =========================
// SERVICIO TeamService
// =========================
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
        return teamRepository.findById(id).map(teamMapper::toDTO).orElse(null);
    }

    @Override
    public TeamDTO save(TeamDTO teamDTO) {
        List<Player> players = new ArrayList<>();

        // Si el DTO tiene IDs de jugadores, los buscamos en la BD
        if (teamDTO.getPlayerIds() != null) {
            players = playerRepository.findAllById(teamDTO.getPlayerIds());
        }

        // Convertimos el DTO a entidad con los jugadores encontrados
        Team team = teamMapper.toEntity(teamDTO, players);
        team = teamRepository.save(team);

        // Asignamos el equipo a cada jugador y los actualizamos
        for (Player player : players) {
            player.setTeam(team);
        }
        playerRepository.saveAll(players);

        return teamMapper.toDTO(team);
    }

    @Override
    public void delete(Long id) {
        teamRepository.deleteById(id);
    }
}
