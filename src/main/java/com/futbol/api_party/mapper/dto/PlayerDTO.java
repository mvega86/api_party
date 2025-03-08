package com.futbol.api_party.mapper.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import java.time.LocalDate;
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class PlayerDTO {
    private Long id;
    private String nombreCompleto;
    private String nombreDorsal;
    private Integer dorsal;
    private LocalDate fechaNacimiento;
    private Integer edad;
}
