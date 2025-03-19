package com.futbol.api_party.controller;

import com.futbol.api_party.mapper.dto.StatisticDTO;
import com.futbol.api_party.service.IStatisticService;
import jakarta.validation.Valid;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.*;

import java.util.List;
@Slf4j
@CrossOrigin(origins = "http://localhost:3000")
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

    @PutMapping
    public StatisticDTO updateStatistic(@Valid @RequestBody StatisticDTO statisticDTO) {
        log.info("Request to update statistic...");
        StatisticDTO statisticDTOOut = statisticService.updateStatistic(statisticDTO);
        log.info("Statistic updated.");
        return statisticDTOOut;
    }

    @DeleteMapping("/{id}")
    public void deleteStatistic(@PathVariable Long id) {
        log.info("Request to delete statistic...");
        statisticService.deleteStatistic(id);
        log.info("Statistic deleted.");
    }
}

