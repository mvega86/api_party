package com.futbol.api_party.mapper;

import com.futbol.api_party.mapper.dto.TeamDTO;
import com.futbol.api_party.persistence.entity.Player;
import com.futbol.api_party.persistence.entity.Team;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

// =========================
// MAPPER TeamMapper
// =========================
@Component
public class TeamMapper {
    public TeamDTO toDTO(Team team) {
        TeamDTO dto = new TeamDTO();
        dto.setId(team.getId());
        dto.setName(team.getName());
        dto.setAcronym(team.getAcronym());
        dto.setPlayerIds(
                team.getPlayers() != null
                        ? team.getPlayers().stream().map(Player::getId).collect(Collectors.toList())
                        : new ArrayList<>()
        );
        return dto;
    }

    public Team toEntity(TeamDTO dto, List<Player> players) {
        Team team = new Team();
        team.setId(dto.getId());
        team.setName(dto.getName());
        team.setAcronym(dto.getAcronym());
        team.setPlayers(players);
        return team;
    }
}
