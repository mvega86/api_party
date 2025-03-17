package com.futbol.api_party.persistence.entity;

import com.futbol.api_party.persistence.audit.AuditModel;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDateTime;
import java.util.List;

@Entity
@Getter
@Setter
@NoArgsConstructor
public class Match extends AuditModel {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "home_team_id", nullable = false)
    private Team homeTeam;

    @ManyToOne
    @JoinColumn(name = "away_team_id", nullable = false)
    private Team awayTeam;

    private LocalDateTime startFirstTime;
    private LocalDateTime endFirstTime;
    private LocalDateTime startSecondTime;
    private LocalDateTime endSecondTime;
    private LocalDateTime startFirstExtraTime;
    private LocalDateTime endFirstExtraTime;
    private LocalDateTime startSecondExtraTime;
    private LocalDateTime endSecondExtraTime;

    @OneToMany(mappedBy = "match", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<PlayerMatch> playerMatches;
}

