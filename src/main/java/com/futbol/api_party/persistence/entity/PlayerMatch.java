package com.futbol.api_party.persistence.entity;

import com.futbol.api_party.persistence.audit.AuditModel;
import jakarta.persistence.*;

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

        private Integer startMinute;
        private Integer endMinute;

}
