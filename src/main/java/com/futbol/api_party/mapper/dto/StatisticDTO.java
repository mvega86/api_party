package com.futbol.api_party.mapper.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class StatisticDTO {

    private Long id;

    @NotBlank(message = "The statistic name cannot be empty.")
    @Size(max = 50, message = "The statistic name cannot exceed 50 characters.")
    private String name;

    @Size(max = 255, message = "The description cannot exceed 255 characters.")
    private String description;

    @Size(max = 10, message = "The unit of measurement cannot exceed 10 characters.")
    private String unit;
}

