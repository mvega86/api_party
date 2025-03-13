package com.futbol.api_party.service;

import com.futbol.api_party.mapper.dto.MatchStatisticDTO;

import java.util.List;

public interface IMatchStatisticService {
    MatchStatisticDTO createMatchStatistic(MatchStatisticDTO matchStatisticDTO);
    List<MatchStatisticDTO> getStatisticsByPlayerMatch(Long playerMatchId);
}
