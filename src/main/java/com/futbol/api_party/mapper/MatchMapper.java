package com.futbol.api_party.mapper;

import com.futbol.api_party.mapper.dto.MatchDTO;
import com.futbol.api_party.mapper.dto.PlayerMatchDTO;
import com.futbol.api_party.persistence.entity.Match;
import com.futbol.api_party.persistence.entity.Team;
import org.springframework.context.annotation.Lazy;
import org.springframework.stereotype.Component;

import java.util.stream.Collectors;

@Component
public class MatchMapper {

    private final TeamMapper teamMapper;
    private final PlayerMatchMapper playerMatchMapper;

    public MatchMapper(TeamMapper teamMapper,@Lazy PlayerMatchMapper playerMatchMapper) {
        this.teamMapper = teamMapper;
        this.playerMatchMapper = playerMatchMapper;
    }
    public Match toEntity(MatchDTO dto) {
        Match match = new Match();
        match.setLocation(dto.getLocation());
        match.setState(dto.getState());
        match.setHomeTeam(teamMapper.toEntity(dto.getHomeTeam()));
        match.setAwayTeam(teamMapper.toEntity(dto.getAwayTeam()));
        match.setStartFirstTime(dto.getStartFirstTime());
        match.setEndFirstTime(dto.getEndFirstTime());
        match.setStartSecondTime(dto.getStartSecondTime());
        match.setEndSecondTime(dto.getEndSecondTime());
        match.setStartFirstExtraTime(dto.getStartFirstExtraTime());
        match.setEndFirstExtraTime(dto.getEndFirstExtraTime());
        match.setStartSecondExtraTime(dto.getStartSecondExtraTime());
        match.setEndSecondExtraTime(dto.getEndSecondExtraTime());
        return match;
    }

    public MatchDTO toDTO(Match match) {
        if (match == null) return null;

        MatchDTO matchDTO = new MatchDTO();
        matchDTO.setId(match.getId());
        matchDTO.setLocation(match.getLocation());
        matchDTO.setState(match.getState());
        matchDTO.setHomeTeam(teamMapper.toDTO(match.getHomeTeam()));
        matchDTO.setAwayTeam(teamMapper.toDTO(match.getAwayTeam()));
        matchDTO.setStartFirstTime(match.getStartFirstTime());
        matchDTO.setEndFirstTime(match.getEndFirstTime());
        matchDTO.setStartSecondTime(match.getStartSecondTime());
        matchDTO.setEndSecondTime(match.getEndSecondTime());
        matchDTO.setStartFirstExtraTime(match.getStartFirstExtraTime());
        matchDTO.setEndFirstExtraTime(match.getEndFirstExtraTime());
        matchDTO.setStartSecondExtraTime(match.getStartSecondExtraTime());
        matchDTO.setEndSecondExtraTime(match.getEndSecondExtraTime());

        matchDTO.setPlayerMatches(
                match.getPlayerMatches() != null ?
                        match.getPlayerMatches().stream()
                                .map(playerMatch -> {
                                    PlayerMatchDTO dto = playerMatchMapper.toDTO(playerMatch);
                                    dto.setMatch(null); // Evita ciclos recursivos
                                    return dto;
                                })
                                .collect(Collectors.toList()) :
                        null
        );

        return matchDTO;
    }
}
