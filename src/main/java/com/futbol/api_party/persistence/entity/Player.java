package com.futbol.api_party.persistence.entity;

import jakarta.persistence.*;
import jakarta.validation.constraints.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import java.time.LocalDate;
import java.time.Period;

@Entity
@Table(name = "players")
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class Player {
    @Id
    @Getter
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotBlank(message = "El nombre completo es obligatorio")
    @Getter
    @Column(name = "nombre_completo", nullable = false)
    private String nombreCompleto;

    @Column(name = "nombre_dorsal")
    @Getter
    private String nombreDorsal;

    @Min(value = 1, message = "El dorsal debe ser un número positivo")
    @Column(name = "dorsal")
    @Getter
    private Integer dorsal;

    @Past(message = "La fecha de nacimiento debe ser una fecha pasada")
    @Column(name = "fecha_nacimiento", nullable = false)
    @Getter
    private LocalDate fechaNacimiento;

    @Transient
    private Integer edad;

    public Integer getEdad() {
        return fechaNacimiento != null ? Period.between(fechaNacimiento, LocalDate.now()).getYears() : null;
    }
}
