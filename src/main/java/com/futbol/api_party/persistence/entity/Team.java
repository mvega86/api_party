package com.futbol.api_party.persistence.entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;
import java.util.List;
import java.util.stream.Collectors;

// =========================
// ENTIDAD Team
// =========================
@Entity
@Table(name = "teams")
@Getter
@Setter
public class Team {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "name", nullable = false)
    private String name;

    @Column(name = "acronym", nullable = false)
    private String acronym;

    @OneToMany(mappedBy = "team", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<Player> players;
}
