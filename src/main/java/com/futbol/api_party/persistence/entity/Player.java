package com.futbol.api_party.persistence.entity;

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

    @NotBlank(message = "Full name is required")
    @Column(name = "full_name", nullable = false)
    private String full_name;

    @Column(name = "jersey_name")
    private String jersey_name;

    @Min(value = 1, message = "The dorsal must be a positive number")
    @Column(name = "jersey_number")
    private Integer jersey_number;

    @Past(message = "The date of birth must be a date in the past")
    @Column(name = "birth_date", nullable = false)
    private LocalDate birth_date;

    @Formula("(EXTRACT(YEAR FROM AGE(CURRENT_DATE, birth_date)))")
    private Integer age;

    @ManyToOne
    @JoinColumn(name = "team_id", nullable = true)
    private Team team;

}
