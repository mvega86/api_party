package com.futbol.api_party.service;

import com.futbol.api_party.mapper.dto.MatchDTO;

import java.util.List;

public interface IMatchService {
    MatchDTO createMatch(MatchDTO matchDTO);
    List<MatchDTO> getAllMatches();
    MatchDTO getMatchById(Long matchId);
}

