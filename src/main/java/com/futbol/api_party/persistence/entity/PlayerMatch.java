package com.futbol.api_party.persistence.entity;

import com.futbol.api_party.persistence.audit.AuditModel;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.Duration;
import java.time.LocalDateTime;

@Entity
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
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

        @Column(name = "in_")
        private LocalDateTime in; // Fecha y hora exacta de entrada
        private LocalDateTime out; // Fecha y hora exacta de salida

}

