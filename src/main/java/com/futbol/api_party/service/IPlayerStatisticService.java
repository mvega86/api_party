package com.futbol.api_party.service;

import com.futbol.api_party.mapper.dto.PlayerStatisticDTO;

import java.util.List;

public interface IPlayerStatisticService {
    PlayerStatisticDTO createPlayerStatistic(PlayerStatisticDTO playerStatisticDTO);
    List<PlayerStatisticDTO> getStatisticsByPlayerMatch(Long playerMatchId);
}
