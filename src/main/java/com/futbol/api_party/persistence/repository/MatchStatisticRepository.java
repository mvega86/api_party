package com.futbol.api_party.persistence.repository;

import com.futbol.api_party.persistence.entity.PlayerStatistic;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface MatchStatisticRepository extends JpaRepository<PlayerStatistic, Long> {
    List<PlayerStatistic> findByPlayerMatchId(Long playerMatchId); // Get statistics of a player in a match
}

