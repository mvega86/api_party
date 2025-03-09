package com.futbol.api_party.mapper.dto;

import com.futbol.api_party.persistence.entity.Player;
import lombok.*;
import java.util.List;

// =========================
// DTO TeamDTO
// =========================
@Getter
@Setter
public class TeamDTO {
    private Long id;
    private String name;
    private String acronym;
    private List<Long> playerIds;
}
