package com.futbol.api_party.mapper.dto;

import jakarta.validation.constraints.NotNull;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDateTime;

@Getter
@Setter
@NoArgsConstructor
public class PlayerMatchDTO {

    @NotNull(message = "Match ID is required.")
    private Long matchId;

    @NotNull(message = "Team ID is required.")
    private Long teamId;

    @NotNull(message = "Player ID is required.")
    private Long playerId;

    @NotNull(message = "In time is required.")
    private LocalDateTime in;

    private LocalDateTime out;
}

