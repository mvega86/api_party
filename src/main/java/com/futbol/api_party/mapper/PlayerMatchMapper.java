package com.futbol.api_party.mapper;

import com.futbol.api_party.mapper.dto.PlayerDTO;
import com.futbol.api_party.mapper.dto.PlayerMatchDTO;
import com.futbol.api_party.persistence.entity.Match;
import com.futbol.api_party.persistence.entity.Player;
import com.futbol.api_party.persistence.entity.PlayerMatch;
import com.futbol.api_party.utils.MatchTimeUtil;
import org.springframework.context.annotation.Lazy;
import org.springframework.stereotype.Component;

@Component
public class PlayerMatchMapper {

    private final MatchMapper matchMapper;
    private final PlayerMapper playerMapper;

    public PlayerMatchMapper(@Lazy MatchMapper matchMapper, PlayerMapper playerMapper) {
        this.matchMapper = matchMapper;
        this.playerMapper = playerMapper;
    }
    public PlayerMatch toEntity(PlayerMatchDTO dto, Match match, Player player) {
        PlayerMatch playerMatch = new PlayerMatch();
        playerMatch.setMatch(match);
        playerMatch.setPlayer(player);
        playerMatch.setIn(dto.getIn());
        playerMatch.setOut(dto.getOut());
        return playerMatch;
    }

    public PlayerMatchDTO toDTO(PlayerMatch playerMatch) {
        PlayerMatchDTO dto = new PlayerMatchDTO();
        dto.setId(playerMatch.getId());

        // 🔹 Se evita anidar `MatchDTO` completo para prevenir recursividad
        dto.setMatch(null);

        // 🔹 Se evita anidar `PlayerDTO` completo para prevenir recursividad
        PlayerDTO playerDTO = playerMapper.toDTO(playerMatch.getPlayer());
        playerDTO.setTeam(null); // Evita ciclos recursivos
        dto.setPlayer(playerDTO);

        dto.setIn(playerMatch.getIn());
        dto.setOut(playerMatch.getOut());

        return dto;
    }
}

