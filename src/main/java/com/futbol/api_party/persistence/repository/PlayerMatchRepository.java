package com.futbol.api_party.persistence.repository;

import com.futbol.api_party.persistence.entity.PlayerMatch;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface PlayerMatchRepository extends JpaRepository<PlayerMatch, Long> {
    List<PlayerMatch> findByMatchId(Long matchId); // Getting players from a match
    List<PlayerMatch> findByPlayerId(Long playerId); // Get matches from a player
}

