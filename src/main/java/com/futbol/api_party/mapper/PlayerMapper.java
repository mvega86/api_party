package com.futbol.api_party.mapper;

import com.futbol.api_party.mapper.dto.PlayerDTO;
import com.futbol.api_party.persistence.entity.Player;
import org.springframework.stereotype.Component;

@Component
public class PlayerMapper {

    public PlayerDTO toDTO(Player player){
        return new PlayerDTO(
                player.getId(),
                player.getNombreCompleto(),
                player.getNombreDorsal(),
                player.getDorsal(),
                player.getFechaNacimiento(),
                player.getEdad()
        );
    }
    public Player toEntity(PlayerDTO playerDTO){
        return new Player(
                playerDTO.getId(),
                playerDTO.getNombreCompleto(),
                playerDTO.getNombreDorsal(),
                playerDTO.getDorsal(),
                playerDTO.getFechaNacimiento(),
                playerDTO.getEdad()
        );
    }
}
