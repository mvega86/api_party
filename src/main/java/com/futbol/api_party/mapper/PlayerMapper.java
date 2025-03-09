package com.futbol.api_party.mapper;

import com.futbol.api_party.mapper.dto.PlayerDTO;
import com.futbol.api_party.persistence.entity.Player;
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
                player.getAge()
        );
    }
    public Player toEntity(PlayerDTO playerDTO){
        return new Player(
                playerDTO.getId(),
                playerDTO.getFull_name(),
                playerDTO.getJersey_name(),
                playerDTO.getJersey_number(),
                playerDTO.getBirth_date(),
                playerDTO.getAge()
        );
    }
}
