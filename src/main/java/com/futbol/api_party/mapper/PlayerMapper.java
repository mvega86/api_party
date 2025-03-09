package com.futbol.api_party.mapper;

import com.futbol.api_party.mapper.dto.PlayerDTO;
import com.futbol.api_party.persistence.entity.Player;
import com.futbol.api_party.persistence.entity.Team;
import org.springframework.stereotype.Component;

@Component
public class PlayerMapper {

    public PlayerDTO toDTO(Player player){
        return new PlayerDTO(
                player.getId(),
                player.getFullName(),
                player.getJerseyName(),
                player.getJerseyNumber(),
                player.getBirthDate(),
                player.getAge(),
                player.getTeam() != null ? player.getTeam().getId() : null // Ahora almacena solo el ID del equipo
        );
    }
    public Player toEntity(PlayerDTO playerDTO, Team team){
        return new Player(
                playerDTO.getId(),
                playerDTO.getFullName(),
                playerDTO.getJerseyName(),
                playerDTO.getJerseyNumber(),
                playerDTO.getBirthDate(),
                playerDTO.getAge(),
                team
        );
    }
}
