package com.futbol.api_party.service.implementation;

import com.futbol.api_party.exception.EntityNotFoundException;
import com.futbol.api_party.mapper.MatchMapper;
import com.futbol.api_party.mapper.dto.MatchDTO;
import com.futbol.api_party.persistence.entity.Match;
import com.futbol.api_party.persistence.entity.PlayerMatch;
import com.futbol.api_party.persistence.entity.Team;
import com.futbol.api_party.persistence.repository.MatchRepository;
import com.futbol.api_party.persistence.repository.PlayerMatchRepository;
import com.futbol.api_party.persistence.repository.TeamRepository;
import com.futbol.api_party.service.IMatchService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.List;

@Service
@Slf4j
public class MatchService implements IMatchService {

    private final MatchRepository matchRepository;
    private final TeamRepository teamRepository;
    private final MatchMapper matchMapper;
    private final PlayerMatchRepository playerMatchRepository;

    public MatchService(MatchRepository matchRepository, TeamRepository teamRepository, MatchMapper matchMapper, PlayerMatchRepository playerMatchRepository) {
        this.matchRepository = matchRepository;
        this.teamRepository = teamRepository;
        this.matchMapper = matchMapper;
        this.playerMatchRepository = playerMatchRepository;
    }

    @Override
    @Transactional
    public MatchDTO createMatch(MatchDTO matchDTO) {
        log.info("Logger: Creating match...");
        teamRepository.findById(matchDTO.getHomeTeam().getId())
                .orElseThrow(() -> {
                    log.error("Logger: Home team, with id {}, not found", matchDTO.getHomeTeam().getId());
                    return new EntityNotFoundException("Home team not found");
                });
        teamRepository.findById(matchDTO.getAwayTeam().getId())
                .orElseThrow(() -> {
                    log.error("Logger: Away team, with id {}, not found", matchDTO.getAwayTeam().getId());
                    return new EntityNotFoundException("Away team not found");
                });

        if (matchDTO.getHomeTeam().getId().equals(matchDTO.getAwayTeam().getId())) {
            log.error("Logger: The home and away teams cannot be the same.");
            throw new IllegalArgumentException("The home and away teams cannot be the same.");
        }

        try {
            Match match = matchMapper.toEntity(matchDTO);
            match = matchRepository.save(match);
            log.info("Logger: Match created successfully");
            return matchMapper.toDTO(match);
        }catch (Exception e){
            log.error("Logger: Error creating match: {}", e.getMessage());
            throw new RuntimeException("Error creating match.");
        }
    }

    @Override
    public List<MatchDTO> getAllMatches() {
        return matchRepository.findAllByOrderByStartFirstTimeAsc().stream().map(matchMapper::toDTO).toList();
    }

    @Override
    public MatchDTO getMatchById(Long matchId) {
        log.info("Logger: Searching match with id {}...", matchId);
        Match match = matchRepository.findById(matchId)
                .orElseThrow(() -> {
                    log.error("Logger: Match with ID {} not found", matchId);
                    return new EntityNotFoundException("Match not found");
                });
        log.info("Logger: Match found: {}", match.getHomeTeam().getAcronym()+" - "+match.getAwayTeam().getAcronym());
        return matchMapper.toDTO(match);
    }

    @Override
    @Transactional
    public MatchDTO updateMatch(MatchDTO matchDTO) {
        matchRepository.findById(matchDTO.getId())
                .orElseThrow(() -> new EntityNotFoundException("Match not found"));

        if (matchDTO.getHomeTeam().getId().equals(matchDTO.getAwayTeam().getId())) {
            throw new IllegalArgumentException("El equipo local y visitante no pueden ser el mismo.");
        }

        log.info("Logger: Updating match times for match ID: {}", matchDTO.getId());

        Match match = matchMapper.toEntity(matchDTO);
        matchRepository.save(match);
        log.info("Logger: Match times updated.");
        return matchMapper.toDTO(match);
    }

    @Override
    public void delete(Long id) {
        log.info("Searching match with id {} to delete...", id);
        Match match = matchRepository.findById(id)
                .orElseThrow(() -> {
                    log.error("Team with ID {} not found", id);
                    return new EntityNotFoundException("Team with ID " + id + " was not found.");

                });
        matchRepository.deleteById(id);
        log.info("Match {} delete successfully.", match.getHomeTeam().getAcronym()+" VS "+match.getAwayTeam().getAcronym());
    }
}

