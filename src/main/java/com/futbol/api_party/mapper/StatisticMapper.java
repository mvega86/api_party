package com.futbol.api_party.mapper;

import com.futbol.api_party.mapper.dto.StatisticDTO;
import com.futbol.api_party.persistence.entity.Statistic;
import org.springframework.stereotype.Component;

@Component
public class StatisticMapper {

    public StatisticDTO toDTO(Statistic statistic) {
        StatisticDTO dto = new StatisticDTO();
        dto.setId(statistic.getId());
        dto.setName(statistic.getName());
        dto.setDescription(statistic.getDescription());
        dto.setUnit(statistic.getUnit());
        return dto;
    }
}

