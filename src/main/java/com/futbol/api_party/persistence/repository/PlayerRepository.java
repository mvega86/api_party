package com.futbol.api_party.persistence.repository;

import com.futbol.api_party.persistence.entity.Player;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface PlayerRepository extends JpaRepository<Player, Long> {
    List<Player> findAllByOrderByUpdatedAtDesc();
}
