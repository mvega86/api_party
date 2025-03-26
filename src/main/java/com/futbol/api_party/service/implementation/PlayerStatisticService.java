package com.futbol.api_party.service.implementation;

import com.futbol.api_party.exception.EntityNotFoundException;
import com.futbol.api_party.mapper.dto.PlayerStatisticDTO;
import com.futbol.api_party.mapper.PlayerStatisticMapper;
import com.futbol.api_party.persistence.entity.PlayerStatistic;
import com.futbol.api_party.persistence.entity.PlayerMatch;
import com.futbol.api_party.persistence.entity.Statistic;
import com.futbol.api_party.persistence.repository.MatchStatisticRepository;
import com.futbol.api_party.persistence.repository.PlayerMatchRepository;
import com.futbol.api_party.persistence.repository.StatisticRepository;
import com.futbol.api_party.service.IPlayerStatisticService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Slf4j
public class PlayerStatisticService implements IPlayerStatisticService {

    private final MatchStatisticRepository matchStatisticRepository;
    private final PlayerMatchRepository playerMatchRepository;
    private final PlayerStatisticMapper playerStatisticMapper;
    private final StatisticRepository statisticRepository;

    public PlayerStatisticService(MatchStatisticRepository matchStatisticRepository,
                                  PlayerMatchRepository playerMatchRepository,
                                  PlayerStatisticMapper playerStatisticMapper,
                                  StatisticRepository statisticRepository) {
        this.matchStatisticRepository = matchStatisticRepository;
        this.playerMatchRepository = playerMatchRepository;
        this.playerStatisticMapper = playerStatisticMapper;
        this.statisticRepository = statisticRepository;
    }

    @Override
    @Transactional
    public PlayerStatisticDTO createPlayerStatistic(PlayerStatisticDTO playerStatisticDTO) {
        log.info("Creating match statistic...");
        PlayerMatch playerMatch = playerMatchRepository.findById(playerStatisticDTO.getPlayerMatch().getId())
                .orElseThrow(() -> {
                    log.error("PlayerMatch, with id {}, not found.", playerStatisticDTO.getPlayerMatch().getId());
                    return new EntityNotFoundException("Player match not found.");
                });

        Statistic statistic = statisticRepository.findById(playerStatisticDTO.getStatistic().getId())
                .orElseThrow(() -> new EntityNotFoundException("Statistic not found"));



        try {
            PlayerStatistic playerStatistic = playerStatisticMapper.toEntity(playerStatisticDTO, playerMatch, statistic);
            playerStatistic = matchStatisticRepository.save(playerStatistic);
            return playerStatisticMapper.toDTO(playerStatistic);
        }catch (Exception e){
            log.error("Error creating match statistic: {}", e.getMessage());
            throw new RuntimeException("Error creating match statistic.");
        }
    }

    @Override
    public List<PlayerStatisticDTO> getStatisticsByPlayerMatch(Long playerMatchId) {
        log.info("Searching playermatch with id {}...", playerMatchId);
        return matchStatisticRepository.findByPlayerMatchId(playerMatchId).stream()
                .map(playerStatisticMapper::toDTO).toList();
    }
}

