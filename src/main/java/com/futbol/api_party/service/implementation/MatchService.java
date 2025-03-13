package com.futbol.api_party.service.implementation;

import com.futbol.api_party.exception.EntityNotFoundException;
import com.futbol.api_party.mapper.MatchMapper;
import com.futbol.api_party.mapper.dto.MatchDTO;
import com.futbol.api_party.persistence.entity.Match;
import com.futbol.api_party.persistence.entity.Team;
import com.futbol.api_party.persistence.repository.MatchRepository;
import com.futbol.api_party.persistence.repository.TeamRepository;
import com.futbol.api_party.service.IMatchService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Slf4j
public class MatchService implements IMatchService {

    private final MatchRepository matchRepository;
    private final TeamRepository teamRepository;
    private final MatchMapper matchMapper;

    public MatchService(MatchRepository matchRepository, TeamRepository teamRepository, MatchMapper matchMapper) {
        this.matchRepository = matchRepository;
        this.teamRepository = teamRepository;
        this.matchMapper = matchMapper;
    }

    @Override
    @Transactional
    public MatchDTO createMatch(MatchDTO matchDTO) {
        log.info("Creating match...");
        Team homeTeam = teamRepository.findById(matchDTO.getHomeTeamId())
                .orElseThrow(() -> {
                    log.error("Home team, with id {}, not found", matchDTO.getHomeTeamId());
                    return new EntityNotFoundException("Home team not found");
                });
        Team awayTeam = teamRepository.findById(matchDTO.getAwayTeamId())
                .orElseThrow(() -> {
                    log.error("Away team, with id {}, not found", matchDTO.getAwayTeamId());
                    return new EntityNotFoundException("Away team not found");
                });

        try {
            Match match = matchMapper.toEntity(matchDTO, homeTeam, awayTeam);
            match = matchRepository.save(match);
            log.info("Match created successfully");
            return matchMapper.toDTO(match);
        }catch (Exception e){
            log.error("Error creating match: {}", e.getMessage());
            throw new RuntimeException("Error creating match.");
        }
    }

    @Override
    public List<MatchDTO> getAllMatches() {
        return matchRepository.findAll().stream().map(matchMapper::toDTO).toList();
    }

    @Override
    public MatchDTO getMatchById(Long matchId) {
        log.info("Searching match with id {}...", matchId);
        Match match = matchRepository.findById(matchId)
                .orElseThrow(() -> {
                    log.error("Match with ID {} not found", matchId);
                    return new EntityNotFoundException("Match not found");
                });
        log.info("Match found: {}", match.getHomeTeam().getAcronym()+" - "+match.getAwayTeam().getAcronym());
        return matchMapper.toDTO(match);
    }
}

