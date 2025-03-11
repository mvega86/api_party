package com.futbol.api_party.mapper;

import com.futbol.api_party.mapper.dto.MatchStatisticDTO;
import com.futbol.api_party.persistence.entity.MatchStatistic;
import com.futbol.api_party.persistence.entity.PlayerMatch;
import com.futbol.api_party.utils.MatchTimeUtil;
import org.springframework.stereotype.Component;

@Component
public class MatchStatisticMapper {

    public MatchStatistic toEntity(MatchStatisticDTO dto, PlayerMatch playerMatch) {
        MatchStatistic statistic = new MatchStatistic();
        statistic.setPlayerMatch(playerMatch);
        statistic.setStatisticName(dto.getStatisticName());
        statistic.setValue(dto.getValue());
        statistic.setUnit(dto.getUnit());
        statistic.setTimestamp(dto.getTimestamp());
        return statistic;
    }

    public MatchStatisticDTO toDTO(MatchStatistic statistic) {
        MatchStatisticDTO dto = new MatchStatisticDTO();
        dto.setPlayerMatchId(statistic.getPlayerMatch().getId());
        dto.setStatisticName(statistic.getStatisticName());
        dto.setValue(statistic.getValue());
        dto.setUnit(statistic.getUnit());

        // Calculate relative minute for display only in API
        dto.setRelativeMinuteFormatted(MatchTimeUtil.calculateRelativeMinuteFormatted(statistic.getPlayerMatch().getMatch(), statistic.getTimestamp()));

        return dto;
    }
}


