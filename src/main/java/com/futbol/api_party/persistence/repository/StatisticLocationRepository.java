// StatisticLocationRepository.java
package com.futbol.api_party.persistence.repository;

import com.futbol.api_party.persistence.entity.StatisticLocation;
import org.springframework.data.jpa.repository.JpaRepository;

public interface StatisticLocationRepository extends JpaRepository<StatisticLocation, Long> {
}
