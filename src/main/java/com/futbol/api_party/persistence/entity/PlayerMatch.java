package com.futbol.api_party.persistence.entity;

import com.futbol.api_party.persistence.audit.AuditModel;
import jakarta.persistence.*;

import java.time.LocalDateTime;

@Entity
public class PlayerMatch extends AuditModel {
        @Id
        @GeneratedValue(strategy = GenerationType.IDENTITY)
        private Long id;

        @ManyToOne
        @JoinColumn(name = "match_id", nullable = false)
        private Match match;

        @ManyToOne
        @JoinColumn(name = "team_id", nullable = false)
        private Team team;

        @ManyToOne
        @JoinColumn(name = "player_id", nullable = false)
        private Player player;

        private LocalDateTime in; // Exact date and time a player in
        private LocalDateTime out; // Exact date and time a player out

        public Integer getEntryMinute() {
                if (match != null && match.getMatchDate() != null && in != null) {
                        return (int) java.time.Duration.between(match.getMatchDate(), in).toMinutes();
                }
                return null;
        }

        public Integer getExitMinute() {
                if (match != null && match.getMatchDate() != null && out != null) {
                        return (int) java.time.Duration.between(match.getMatchDate(), out).toMinutes();
                }
                return null;
        }

}
