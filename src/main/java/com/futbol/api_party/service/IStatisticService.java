package com.futbol.api_party.service;

import com.futbol.api_party.mapper.dto.StatisticDTO;
import org.springframework.web.bind.annotation.PathVariable;

import java.util.List;

public interface IStatisticService {


    StatisticDTO createStatistic(StatisticDTO statisticDTO);

    List<StatisticDTO> getAllStatistics();

    StatisticDTO updateStatistic(StatisticDTO statisticDTO);

    void deleteStatistic(Long id);

}
