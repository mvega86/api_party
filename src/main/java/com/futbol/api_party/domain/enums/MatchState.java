package com.futbol.api_party.domain.enums;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public enum MatchState {
    PENDING("Pendiente"),
    IN_PROGRESS("En progreso"),
    STARTED("Iniciado"),
    FINISHED("Finalizado");

    private final String description;
}
