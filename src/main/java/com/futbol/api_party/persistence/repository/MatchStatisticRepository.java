package com.futbol.api_party.persistence.repository;

import com.futbol.api_party.persistence.entity.MatchStatistic;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface MatchStatisticRepository extends JpaRepository<MatchStatistic, Long> {
    List<MatchStatistic> findByPlayerMatchId(Long playerMatchId); // Get statistics of a player in a match
}

