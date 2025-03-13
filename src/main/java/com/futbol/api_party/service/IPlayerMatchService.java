package com.futbol.api_party.service;

import com.futbol.api_party.mapper.dto.PlayerMatchDTO;

import java.util.List;

public interface IPlayerMatchService {
    PlayerMatchDTO assignPlayerToMatch(PlayerMatchDTO playerMatchDTO);
    List<PlayerMatchDTO> getPlayersByMatch(Long matchId);
}

