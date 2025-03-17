package com.futbol.api_party.service;

import com.futbol.api_party.mapper.dto.StatisticDTO;

import java.util.List;

public interface IStatisticService {


    StatisticDTO createStatistic(StatisticDTO statisticDTO);

    List<StatisticDTO> getAllStatistics();

}
