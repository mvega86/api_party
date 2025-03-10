package com.futbol.api_party.mapper.dto;

import jakarta.validation.constraints.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDateTime;

@Getter
@Setter
@NoArgsConstructor
public class MatchStatisticDTO {

    @NotNull(message = "PlayerMatch ID cannot be null.")
    private Long playerMatchId;

    @NotBlank(message = "The name of the statistic is required.")
    @Size(max = 50, message = "The statistic name cannot exceed 50 characters.")
    private String statisticName; // Example: "goals", "assists", "yellow_cards"

    @NotNull(message = "The value of statistics is required.")
    @DecimalMin(value = "0.0", message = "The value of the statistic cannot be negative.")
    private Double value;

    @Size(max = 10, message = "The unit of measurement cannot exceed 10 characters.")
    private String unit; // Example: "km", "seconds", "meters"

    @NotNull(message = "The statistics timestamp is required.")
    private LocalDateTime timestamp; // Exact date and time the statistic occurred

    public Integer getRelativeMinute(LocalDateTime matchStartTime) {
        if (matchStartTime != null && timestamp != null) {
            return (int) java.time.Duration.between(matchStartTime, timestamp).toMinutes();
        }
        return null;
    }
}

