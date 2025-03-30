package com.futbol.api_party.mapper.dto;

import com.futbol.api_party.persistence.entity.Team;
import jakarta.validation.constraints.FutureOrPresent;
import jakarta.validation.constraints.NotNull;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDateTime;
import java.util.List;

@Getter
@Setter
@NoArgsConstructor
public class MatchDTO {

    private Long id;

    private String location;

    @NotNull(message = "Home team is required.")
    private TeamDTO homeTeam;

    @NotNull(message = "Away team is required.")
    private TeamDTO awayTeam;

    @NotNull(message = "The date and time of the match is required.")
    //@FutureOrPresent(message = "The date of the match cannot be earlier than the current time.")
    private LocalDateTime startFirstTime;
    private LocalDateTime endFirstTime;
    private LocalDateTime startSecondTime;
    private LocalDateTime endSecondTime;
    private LocalDateTime startFirstExtraTime;
    private LocalDateTime endFirstExtraTime;
    private LocalDateTime startSecondExtraTime;
    private LocalDateTime endSecondExtraTime;

    private List<PlayerMatchDTO> playerMatches;
}

