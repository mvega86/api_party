package com.futbol.api_party.persistence.repository;

import com.futbol.api_party.persistence.entity.Team;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

// =========================
// REPOSITORIO TeamRepository
// =========================
@Repository
public interface TeamRepository extends JpaRepository<Team, Long> {
}
