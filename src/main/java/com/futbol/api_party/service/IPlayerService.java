package com.futbol.api_party.service;

import com.futbol.api_party.mapper.dto.PlayerDTO;

import java.util.List;

public interface IPlayerService {
    public PlayerDTO save(PlayerDTO playerDTO);

    List<PlayerDTO> getAll();

    PlayerDTO getById(Long id);

    void delete(Long id);
}
