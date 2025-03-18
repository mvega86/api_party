package com.futbol.api_party.persistence.entity;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.futbol.api_party.persistence.audit.AuditModel;
import jakarta.persistence.*;
import jakarta.validation.constraints.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.annotations.Formula;

import java.time.LocalDate;
import java.time.Period;

@Entity
@Table(name = "players")
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class Player extends AuditModel {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "full_name", nullable = false)
    private String fullName;

    @Column(name = "jersey_name")
    private String jerseyName;

    @Column(name = "jersey_number")
    private Integer jerseyNumber;

    @Column(name = "birth_date", nullable = false)
    private LocalDate birthDate;

    @Formula("(EXTRACT(YEAR FROM AGE(CURRENT_DATE, birth_date)))")
    private Integer age;

    @ManyToOne
    @JoinColumn(name = "team_id", nullable = true)
    @JsonBackReference
    private Team team;

}
