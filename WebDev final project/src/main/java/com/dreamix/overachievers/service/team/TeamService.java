package com.dreamix.overachievers.service.team;

import com.dreamix.overachievers.dto.team.TeamDto;
import com.dreamix.overachievers.entity.Team;
import org.springframework.http.ResponseEntity;

import java.util.List;

public interface TeamService {
    List<TeamDto> getAllTeams();

    ResponseEntity<String> createTeam(TeamDto teamDto);

    ResponseEntity<String> deleteTeam(Long id);

    Team getTeamEntityByTeamName(String teamName);
}
