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
                player.getFull_name(),
                player.getJersey_name(),
                player.getJersey_number(),
                player.getBirth_date(),
                player.getAge(),
                player.getTeam() != null ? player.getTeam().getId() : null // Ahora almacena solo el ID del equipo
        );
    }
    public Player toEntity(PlayerDTO playerDTO, Team team){
        return new Player(
                playerDTO.getId(),
                playerDTO.getFull_name(),
                playerDTO.getJersey_name(),
                playerDTO.getJersey_number(),
                playerDTO.getBirth_date(),
                playerDTO.getAge(),
                team
        );
    }
}
