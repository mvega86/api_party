package com.futbol.api_party.service.implementation;

import com.futbol.api_party.exception.EntityNotFoundException;
import com.futbol.api_party.mapper.dto.MatchStatisticDTO;
import com.futbol.api_party.mapper.MatchStatisticMapper;
import com.futbol.api_party.persistence.entity.MatchStatistic;
import com.futbol.api_party.persistence.entity.PlayerMatch;
import com.futbol.api_party.persistence.repository.MatchStatisticRepository;
import com.futbol.api_party.persistence.repository.PlayerMatchRepository;
import com.futbol.api_party.service.IMatchStatisticService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Slf4j
public class MatchStatisticService implements IMatchStatisticService {

    private final MatchStatisticRepository matchStatisticRepository;
    private final PlayerMatchRepository playerMatchRepository;
    private final MatchStatisticMapper matchStatisticMapper;

    public MatchStatisticService(MatchStatisticRepository matchStatisticRepository,
                                 PlayerMatchRepository playerMatchRepository,
                                 MatchStatisticMapper matchStatisticMapper) {
        this.matchStatisticRepository = matchStatisticRepository;
        this.playerMatchRepository = playerMatchRepository;
        this.matchStatisticMapper = matchStatisticMapper;
    }

    @Override
    @Transactional
    public MatchStatisticDTO createMatchStatistic(MatchStatisticDTO matchStatisticDTO) {
        log.info("Creating match statistic...");
        PlayerMatch playerMatch = playerMatchRepository.findById(matchStatisticDTO.getPlayerMatchId())
                .orElseThrow(() -> {
                    log.error("PlayerMatch, with id {}, not found.", matchStatisticDTO.getPlayerMatchId());
                    return new EntityNotFoundException("Player match not found.");
                });

        try {
            MatchStatistic matchStatistic = matchStatisticMapper.toEntity(matchStatisticDTO, playerMatch);
            matchStatistic = matchStatisticRepository.save(matchStatistic);
            log.info("Match statistic created successfully");
            return matchStatisticMapper.toDTO(matchStatistic);
        }catch (Exception e){
            log.error("Error creating match statistic: {}", e.getMessage());
            throw new RuntimeException("Error creating match statistic.");
        }
    }

    @Override
    public List<MatchStatisticDTO> getStatisticsByPlayerMatch(Long playerMatchId) {
        log.info("Searching playermatch with id {}...", playerMatchId);
        return matchStatisticRepository.findByPlayerMatchId(playerMatchId).stream()
                .map(matchStatisticMapper::toDTO).toList();
    }
}

