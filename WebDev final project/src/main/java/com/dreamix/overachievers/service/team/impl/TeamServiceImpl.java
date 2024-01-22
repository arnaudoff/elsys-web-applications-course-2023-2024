package com.dreamix.overachievers.service.team.impl;

import com.dreamix.overachievers.dto.team.TeamDto;
import com.dreamix.overachievers.entity.Team;
import com.dreamix.overachievers.exception.EntityAlreadyExistsException;
import com.dreamix.overachievers.exception.EntityNotFoundException;
import com.dreamix.overachievers.mapper.TeamMapper;
import com.dreamix.overachievers.repository.TeamRepository;
import com.dreamix.overachievers.service.team.TeamService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class TeamServiceImpl implements TeamService {
    private static final String TEAM = "Team";
    private final TeamRepository teamRepository;
    private final TeamMapper teamMapper;

    @Autowired
    public TeamServiceImpl(TeamRepository teamRepository, TeamMapper teamMapper) {
        this.teamRepository = teamRepository;
        this.teamMapper = teamMapper;
    }

    @Override
    public List<TeamDto> getAllTeams() {
        return teamMapper.teamEntityToTeamDto(teamRepository.orderTeamByTeamName());
    }

    @Override
    public ResponseEntity<String> createTeam(TeamDto teamDto) {
        Optional<Team> team = teamRepository.findByTeamName(teamDto.getTeamName());
        if (team.isPresent()) {
            throw new EntityAlreadyExistsException("Team with name: " + teamDto.getTeamName() + " already exists!");
        }

        teamRepository.save(teamMapper.teamDtoToTeamEntity(teamDto));
        return new ResponseEntity<>("Team created successfully!", HttpStatus.OK);
    }

    @Override
    public ResponseEntity<String> deleteTeam(Long id) {
        Team team =
                teamRepository
                        .findById(id)
                        .orElseThrow(() -> new EntityNotFoundException(TEAM, id));

        teamRepository.delete(team);
        return new ResponseEntity<>("Deleted team successfully!", HttpStatus.OK);
    }

    @Override
    public Team getTeamEntityByTeamName(String teamName) {
        Optional<Team> team = teamRepository.findByTeamName(teamName);

        if (team.isEmpty()) {
            throw new EntityNotFoundException("No team with name " + teamName);
        }

        return team.get();
    }
}
