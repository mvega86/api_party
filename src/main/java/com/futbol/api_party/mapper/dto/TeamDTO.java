package com.futbol.api_party.mapper.dto;

import com.futbol.api_party.persistence.entity.Player;
import jakarta.validation.constraints.NotBlank;
import lombok.*;
import java.util.List;

// =========================
// DTO TeamDTO
// =========================
@Getter
@Setter
public class TeamDTO {
    private Long id;
    @NotBlank(message = "Name is required")
    private String name;
    @NotBlank(message = "Acronym is required")
    private String acronym;
    private List<Long> playerIds;
}
