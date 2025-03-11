package com.futbol.api_party.mapper;

import com.futbol.api_party.mapper.dto.MatchDTO;
import com.futbol.api_party.persistence.entity.Match;
import com.futbol.api_party.persistence.entity.Team;
import org.springframework.stereotype.Component;

@Component
public class MatchMapper {

    public Match toEntity(MatchDTO dto, Team homeTeam, Team awayTeam) {
        Match match = new Match();
        match.setHomeTeam(homeTeam);
        match.setAwayTeam(awayTeam);
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
        MatchDTO dto = new MatchDTO();
        dto.setHomeTeamId(match.getHomeTeam().getId());
        dto.setAwayTeamId(match.getAwayTeam().getId());
        dto.setStartFirstTime(match.getStartFirstTime());
        dto.setEndFirstTime(match.getEndFirstTime());
        dto.setStartSecondTime(match.getStartSecondTime());
        dto.setEndSecondTime(match.getEndSecondTime());
        dto.setStartFirstExtraTime(match.getStartFirstExtraTime());
        dto.setEndFirstExtraTime(match.getEndFirstExtraTime());
        dto.setStartSecondExtraTime(match.getStartSecondExtraTime());
        dto.setEndSecondExtraTime(match.getEndSecondExtraTime());
        return dto;
    }
}
