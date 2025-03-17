package com.futbol.api_party.service.implementation;

import com.futbol.api_party.mapper.StatisticMapper;
import com.futbol.api_party.mapper.dto.StatisticDTO;
import com.futbol.api_party.persistence.entity.Statistic;
import com.futbol.api_party.persistence.repository.StatisticRepository;
import com.futbol.api_party.service.IStatisticService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class StatisticService implements IStatisticService{

    private final StatisticRepository statisticRepository;
    private final StatisticMapper statisticMapper;

    public StatisticService(StatisticRepository statisticRepository, StatisticMapper statisticMapper) {
        this.statisticRepository = statisticRepository;
        this.statisticMapper = statisticMapper;
    }

    @Override
    @Transactional
    public StatisticDTO createStatistic(StatisticDTO statisticDTO) {
        // Validar si la estadística ya existe por nombre
        if (statisticRepository.existsByName(statisticDTO.getName())) {
            throw new RuntimeException("Statistic with name '" + statisticDTO.getName() + "' already exists.");
        }

        Statistic statistic = new Statistic();
        statistic.setName(statisticDTO.getName());
        statistic.setDescription(statisticDTO.getDescription());
        statistic.setUnit(statisticDTO.getUnit());

        return statisticMapper.toDTO(statisticRepository.save(statistic));
    }

    @Override
    public List<StatisticDTO> getAllStatistics() {
        return statisticRepository.findAll().stream()
                .map(statisticMapper::toDTO)
                .toList();
    }
}

