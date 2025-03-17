package com.futbol.api_party.mapper;

import com.futbol.api_party.mapper.dto.PlayerStatisticDTO;
import com.futbol.api_party.persistence.entity.PlayerStatistic;
import com.futbol.api_party.persistence.entity.PlayerMatch;
import com.futbol.api_party.persistence.entity.Statistic;
import com.futbol.api_party.utils.MatchTimeUtil;
import org.springframework.stereotype.Component;

@Component
public class PlayerStatisticMapper {

    public PlayerStatistic toEntity(PlayerStatisticDTO dto, PlayerMatch playerMatch, Statistic statistic) {
        PlayerStatistic playerStatistic = new PlayerStatistic();
        playerStatistic.setPlayerMatch(playerMatch);
        playerStatistic.setStatistic(statistic);
        playerStatistic.setValue(dto.getValue());
        playerStatistic.setTimestamp(dto.getTimestamp());
        return playerStatistic;
    }

    public PlayerStatisticDTO toDTO(PlayerStatistic playerStatistic) {
        PlayerStatisticDTO dto = new PlayerStatisticDTO();
        dto.setPlayerMatchId(playerStatistic.getPlayerMatch().getId());
        dto.setStatisticId(playerStatistic.getStatistic().getId());
        dto.setValue(playerStatistic.getValue());
        dto.setTimestamp(playerStatistic.getTimestamp());

        // Calculate relative minute for display only in API
        dto.setRelativeMinuteFormatted(MatchTimeUtil.calculateRelativeMinuteFormatted(playerStatistic.getPlayerMatch().getMatch(), playerStatistic.getTimestamp()));

        return dto;
    }
}


