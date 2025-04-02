package com.futbol.api_party.service.implementation;

import com.futbol.api_party.exception.EntityNotFoundException;
import com.futbol.api_party.mapper.dto.PlayerMatchDTO;
import com.futbol.api_party.mapper.PlayerMatchMapper;
import com.futbol.api_party.persistence.entity.Match;
import com.futbol.api_party.persistence.entity.Player;
import com.futbol.api_party.persistence.entity.PlayerMatch;
import com.futbol.api_party.persistence.entity.Team;
import com.futbol.api_party.persistence.repository.MatchRepository;
import com.futbol.api_party.persistence.repository.PlayerMatchRepository;
import com.futbol.api_party.persistence.repository.PlayerRepository;
import com.futbol.api_party.persistence.repository.TeamRepository;
import com.futbol.api_party.service.IPlayerMatchService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Slf4j
public class PlayerMatchService implements IPlayerMatchService {

    private final PlayerMatchRepository playerMatchRepository;
    private final MatchRepository matchRepository;
    private final TeamRepository teamRepository;
    private final PlayerRepository playerRepository;
    private final PlayerMatchMapper playerMatchMapper;

    public PlayerMatchService(PlayerMatchRepository playerMatchRepository, MatchRepository matchRepository,
                              TeamRepository teamRepository, PlayerRepository playerRepository, PlayerMatchMapper playerMatchMapper) {
        this.playerMatchRepository = playerMatchRepository;
        this.matchRepository = matchRepository;
        this.teamRepository = teamRepository;
        this.playerRepository = playerRepository;
        this.playerMatchMapper = playerMatchMapper;
    }

    @Override
    @Transactional
    public PlayerMatchDTO assignPlayerToMatch(PlayerMatchDTO playerMatchDTO) {
        log.info("Assigning player to match...");
        Match match = matchRepository.findById(playerMatchDTO.getMatch().getId())
                .orElseThrow(() -> {
                    log.error("Match, with id {}, not found.", playerMatchDTO.getMatch().getId());
                    return new EntityNotFoundException("Match not found");
                });
        Player player = playerRepository.findById(playerMatchDTO.getPlayer().getId())
                .orElseThrow(() -> {
                    log.error("Player match, with id {}, not found.", playerMatchDTO.getPlayer().getId());
                    return new EntityNotFoundException("Player not found");
                });

        try {
            PlayerMatch playerMatch = playerMatchMapper.toEntity(playerMatchDTO, match, player);
            if (match.getStartFirstTime()!=null){
                playerMatch.setIn(match.getStartFirstTime());
            }
            playerMatch = playerMatchRepository.save(playerMatch);
            log.info("Player match assigned successfully");
            return playerMatchMapper.toDTO(playerMatch);
        }catch (Exception e){
            log.error("Error assigning player to match: {}", e.getMessage());
            throw new RuntimeException("Error assigning player to match.");
        }
    }

    @Override
    public PlayerMatchDTO getById(Long id) {
        log.info("Searching match with id {}...", id);
        PlayerMatch playerMatch = playerMatchRepository.findById(id)
                .orElseThrow(() -> {
                    log.error("Player, with id {}, not found.", id);
                    return new EntityNotFoundException("PlayerMatch not found");
                });
        return playerMatchMapper.toDTO(playerMatch);
    }

    @Override
    @Transactional
    public PlayerMatchDTO updatePlayerMatch(PlayerMatchDTO playerMatchDTO) {
        PlayerMatch playerMatch = playerMatchRepository.findById(playerMatchDTO.getId())
                .orElseThrow(() -> new EntityNotFoundException("PlayerMatch not found"));

        log.info("Updating out time for playerMatch ID: {}", playerMatchDTO.getId());

        if (playerMatchDTO.getOut() != null) {
            playerMatch.setOut(playerMatchDTO.getOut());
        }

        playerMatchRepository.save(playerMatch);
        return playerMatchMapper.toDTO(playerMatch);
    }

    public void delete(Long id) {
        log.info("Searching player match with id {} to delete...", id);
        PlayerMatch playerMatch = playerMatchRepository.findById(id)
                .orElseThrow(() -> {
                    log.error("Team with ID {} not found", id);
                    return new EntityNotFoundException("Team with ID " + id + " was not found.");

                });
        playerMatchRepository.deleteById(id);
        log.info("Match {} delete successfully.", playerMatch.getId());
    }
}

