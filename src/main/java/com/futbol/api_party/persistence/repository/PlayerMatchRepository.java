package com.futbol.api_party.persistence.repository;

import com.futbol.api_party.persistence.entity.Match;
import com.futbol.api_party.persistence.entity.PlayerMatch;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.time.LocalDateTime;
import java.util.List;

@Repository
public interface PlayerMatchRepository extends JpaRepository<PlayerMatch, Long> {
    List<PlayerMatch> findByMatchId(Long matchId); // Getting players from a match
    List<PlayerMatch> findByPlayerId(Long playerId); // Get matches from a player
    /*@Query("SELECT pm FROM PlayerMatch pm WHERE pm.match = :match AND pm.in = :previousStart")
    List<PlayerMatch> findByMatchAndIn(@Param("match") Match match, @Param("previousStart") LocalDateTime previousStart);*/
}

