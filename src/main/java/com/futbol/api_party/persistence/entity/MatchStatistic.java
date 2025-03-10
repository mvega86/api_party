package com.futbol.api_party.persistence.entity;

import com.futbol.api_party.persistence.audit.AuditModel;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDateTime;

@Entity
@Getter
@Setter
@NoArgsConstructor
public class MatchStatistic extends AuditModel {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "player_match_id", nullable = false)
    private PlayerMatch playerMatch;

    private String statisticName;
    private Double value;
    private String unit; // Optional(ex: "km", "seconds", "meters", null not apply)

    private LocalDateTime timestamp; // Just moment at statistic
}

