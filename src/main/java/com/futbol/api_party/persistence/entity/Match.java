package com.futbol.api_party.persistence.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import com.futbol.api_party.domain.enums.MatchState;
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

    private String location;

    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private MatchState state = MatchState.PENDING;

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
    @JsonManagedReference
    private List<PlayerMatch> playerMatches;
}

