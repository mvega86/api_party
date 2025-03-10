package com.futbol.api_party.mapper.dto;

import jakarta.validation.constraints.FutureOrPresent;
import jakarta.validation.constraints.NotNull;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDateTime;

@Getter
@Setter
@NoArgsConstructor
public class MatchDTO {

    @NotNull(message = "Home team is required.")
    private Long homeTeamId;

    @NotNull(message = "Away team is required.")
    private Long awayTeamId;

    @NotNull(message = "The date and time of the match is required.")
    @FutureOrPresent(message = "The date of the match cannot be earlier than the current time.")
    private LocalDateTime matchDate;
}

