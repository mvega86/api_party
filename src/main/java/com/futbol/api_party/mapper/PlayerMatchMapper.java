package com.futbol.api_party.mapper;

import com.futbol.api_party.mapper.dto.PlayerMatchDTO;
import com.futbol.api_party.persistence.entity.Match;
import com.futbol.api_party.persistence.entity.Player;
import com.futbol.api_party.persistence.entity.Team;
import com.futbol.api_party.persistence.entity.PlayerMatch;
import com.futbol.api_party.utils.MatchTimeUtil;
import org.springframework.stereotype.Component;

@Component
public class PlayerMatchMapper {

    public PlayerMatch toEntity(PlayerMatchDTO dto, Match match, Team team, Player player) {
        PlayerMatch playerMatch = new PlayerMatch();
        playerMatch.setMatch(match);
        playerMatch.setTeam(team);
        playerMatch.setPlayer(player);
        playerMatch.setIn(dto.getIn());
        playerMatch.setOut(dto.getOut());
        return playerMatch;
    }

    public PlayerMatchDTO toDTO(PlayerMatch playerMatch) {
        PlayerMatchDTO dto = new PlayerMatchDTO();
        dto.setMatchId(playerMatch.getMatch().getId());
        dto.setTeamId(playerMatch.getTeam().getId());
        dto.setPlayerId(playerMatch.getPlayer().getId());
        dto.setIn(playerMatch.getOut());
        dto.setOut(playerMatch.getOut());

        // Calculate relative minute for display only in API
        dto.setInMinuteFormatted(MatchTimeUtil.calculateRelativeMinuteFormatted(playerMatch.getMatch(), playerMatch.getIn()));
        dto.setOutMinuteFormatted(MatchTimeUtil.calculateRelativeMinuteFormatted(playerMatch.getMatch(), playerMatch.getOut()));

        return dto;
    }
}

