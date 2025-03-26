package com.futbol.api_party.service.implementation;

import com.futbol.api_party.exception.EntityNotFoundException;
import com.futbol.api_party.mapper.StatisticMapper;
import com.futbol.api_party.mapper.dto.StatisticDTO;
import com.futbol.api_party.persistence.entity.Statistic;
import com.futbol.api_party.persistence.entity.Team;
import com.futbol.api_party.persistence.repository.StatisticRepository;
import com.futbol.api_party.service.IStatisticService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Slf4j
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
        log.info("Getting all statistics...");
        List<StatisticDTO> statisticDTOList = statisticRepository.findAll().stream()
                .map(statisticMapper::toDTO)
                .toList();
        log.info("Statistics done.");
        return statisticDTOList;
    }

    @Override
    public StatisticDTO updateStatistic(StatisticDTO statisticDTO) {
        // Check if the statistic exist
        log.info("Searching statisyic with id {} to update...", statisticDTO.getId());
        Optional<Statistic> optionalStatistic = statisticRepository.findById(statisticDTO.getId());
        if (optionalStatistic.isEmpty()) {
            log.error("Statistic {} not found...", statisticDTO.getName());
            throw new EntityNotFoundException("Statistic with name '" + statisticDTO.getName() + "' not exists.");
        }

        Statistic statistic = optionalStatistic.get();
        statistic.setName(statisticDTO.getName());
        statistic.setDescription(statisticDTO.getDescription());
        statistic.setUnit(statisticDTO.getUnit());

        return statisticMapper.toDTO(statisticRepository.save(statistic));
    }

    @Override
    public void deleteStatistic(Long id) {
        log.info("Searching statisyic with id {} to delete...", id);
        Statistic statistic = statisticRepository.findById(id)
                .orElseThrow(() -> {
                    log.error("Statistic with ID {} not found", id);
                    return new EntityNotFoundException("Statistic with ID " + id + " was not found.");

                });
        statisticRepository.deleteById(id);
        log.info("Statistic {} delete successfully.", statistic.getName());
    }
}

