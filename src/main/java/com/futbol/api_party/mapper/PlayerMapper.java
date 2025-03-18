package com.futbol.api_party.mapper;

import com.futbol.api_party.mapper.dto.PlayerDTO;
import com.futbol.api_party.persistence.entity.Player;
import com.futbol.api_party.persistence.entity.Team;
import org.springframework.stereotype.Component;

@Component
public class PlayerMapper {

    private TeamMapper teamMapper;

    public PlayerMapper(TeamMapper teamMapper) {
        this.teamMapper = teamMapper;
    }

    public PlayerDTO toDTO(Player player){
        return new PlayerDTO(
                player.getId(),
                player.getFullName(),
                player.getJerseyName(),
                player.getJerseyNumber(),
                player.getBirthDate(),
                player.getAge(),
                player.getTeam() != null ? teamMapper.toDTO(player.getTeam()) : null // Ahora almacena solo el ID del equipo
        );
    }
    public Player toEntity(PlayerDTO playerDTO){
        return new Player(
                playerDTO.getId(),
                playerDTO.getFullName(),
                playerDTO.getJerseyName(),
                playerDTO.getJerseyNumber(),
                playerDTO.getBirthDate(),
                playerDTO.getAge(),
                teamMapper.toEntity(playerDTO.getTeam())
        );
    }
}
