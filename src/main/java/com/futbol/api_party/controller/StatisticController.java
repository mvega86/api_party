package com.futbol.api_party.controller;

import com.futbol.api_party.mapper.dto.StatisticDTO;
import com.futbol.api_party.service.IStatisticService;
import jakarta.validation.Valid;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/statistics")
public class StatisticController {

    private final IStatisticService statisticService;

    public StatisticController(IStatisticService statisticService) {
        this.statisticService = statisticService;
    }

    @PostMapping
    public StatisticDTO createStatistic(@Valid @RequestBody StatisticDTO statisticDTO) {
        return statisticService.createStatistic(statisticDTO);
    }

    @GetMapping
    public List<StatisticDTO> getAllStatistics() {
        return statisticService.getAllStatistics();
    }
}

