package com.futbol.api_party.persistence.repository;

import com.futbol.api_party.persistence.entity.Match;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface MatchRepository extends JpaRepository<Match, Long> {
    List<Match> findAllByOrderByStartFirstTimeAsc();
}