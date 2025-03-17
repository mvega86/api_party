package com.futbol.api_party.mapper.dto;

import jakarta.validation.constraints.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDateTime;

@Getter
@Setter
@NoArgsConstructor
public class PlayerStatisticDTO {

    @NotNull(message = "PlayerMatch ID cannot be null.")
    private Long playerMatchId;

    @NotNull(message = "The id of the statistic is required.")
    private Long statisticId;

    @NotNull(message = "The value of statistics is required.")
    @DecimalMin(value = "0.0", message = "The value of the statistic cannot be negative.")
    private Double value;

    @NotNull(message = "The statistics timestamp is required.")
    private LocalDateTime timestamp;

    private String relativeMinuteFormatted; // Relative minute the statistic occurred

}
