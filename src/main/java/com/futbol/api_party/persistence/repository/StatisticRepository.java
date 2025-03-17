package com.futbol.api_party.persistence.repository;

import com.futbol.api_party.persistence.entity.Statistic;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface StatisticRepository extends JpaRepository<Statistic, Long> {
    boolean existsByName(String name);
}

