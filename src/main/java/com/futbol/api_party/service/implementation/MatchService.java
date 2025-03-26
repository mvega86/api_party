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
        log.info("Creating match...");
        Team homeTeam = teamRepository.findById(matchDTO.getHomeTeam().getId())
                .orElseThrow(() -> {
                    log.error("Home team, with id {}, not found", matchDTO.getHomeTeam().getId());
                    return new EntityNotFoundException("Home team not found");
                });
        Team awayTeam = teamRepository.findById(matchDTO.getAwayTeam().getId())
                .orElseThrow(() -> {
                    log.error("Away team, with id {}, not found", matchDTO.getAwayTeam().getId());
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

    @Override
    @Transactional
    public MatchDTO updateMatchTimes(MatchDTO matchDTO) {
        Match match = matchRepository.findById(matchDTO.getId())
                .orElseThrow(() -> new EntityNotFoundException("Match not found"));

        log.info("Updating match times for match ID: {}", matchDTO.getId());

        // Verificar si se está actualizando el tiempo de inicio del primer tiempo
        if (matchDTO.getStartFirstTime() != null && !matchDTO.getStartFirstTime().equals(match.getStartFirstTime())) {
            LocalDateTime previousStart = match.getStartFirstTime();
            match.setStartFirstTime(matchDTO.getStartFirstTime());

            // 🔹 ACTUALIZAR TAMBIÉN EL 'in' DE LOS JUGADORES QUE INGRESARON EN EL INICIO DEL PARTIDO
            List<PlayerMatch> playerMatches = playerMatchRepository.findByMatchAndIn(match, previousStart);
            for (PlayerMatch playerMatch : playerMatches) {
                playerMatch.setIn(matchDTO.getStartFirstTime());
            }
            playerMatchRepository.saveAll(playerMatches);
        }
        if (matchDTO.getEndFirstTime() != null) match.setEndFirstTime(matchDTO.getEndFirstTime());
        if (matchDTO.getStartSecondTime() != null) match.setStartSecondTime(matchDTO.getStartSecondTime());
        if (matchDTO.getEndSecondTime() != null) match.setEndSecondTime(matchDTO.getEndSecondTime());
        if (matchDTO.getStartFirstExtraTime() != null) match.setStartFirstExtraTime(matchDTO.getStartFirstExtraTime());
        if (matchDTO.getEndFirstExtraTime() != null) match.setEndFirstExtraTime(matchDTO.getEndFirstExtraTime());
        if (matchDTO.getStartSecondExtraTime() != null) match.setStartSecondExtraTime(matchDTO.getStartSecondExtraTime());
        if (matchDTO.getEndSecondExtraTime() != null) match.setEndSecondExtraTime(matchDTO.getEndSecondExtraTime());

        matchRepository.save(match);
        log.info("Match times updated.");
        return matchMapper.toDTO(match);
    }
}

