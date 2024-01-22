package com.dreamix.overachievers.service.team;

import com.dreamix.overachievers.dto.team.TeamDto;
import com.dreamix.overachievers.entity.Team;
import com.dreamix.overachievers.exception.EntityAlreadyExistsException;
import com.dreamix.overachievers.mapper.TeamMapper;
import com.dreamix.overachievers.repository.TeamRepository;
import com.dreamix.overachievers.service.team.impl.TeamServiceImpl;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import static org.junit.jupiter.api.Assertions.*;

class TeamServiceTest {
    private final static Long id = 1L;
    @Mock
    private TeamRepository teamRepository;
    @Mock
    private TeamMapper teamMapper;
    @InjectMocks
    private TeamServiceImpl teamService;

    @BeforeEach
    void setUp()
    {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void getAllTeamsTest() {
        List<Team> teams = Arrays.asList(new Team(), new Team());
        when(teamRepository.orderTeamByTeamName()).thenReturn(teams);

        teamService.getAllTeams();

        verify(teamRepository).orderTeamByTeamName();
    }

    @Test
    void createTeamTest_WhenValidName_ThenRespondOk() {
        TeamDto teamDto = new TeamDto();
        Team team = new Team();
        when(teamRepository.findByTeamName(teamDto.getTeamName())).thenReturn(Optional.empty());
        when(teamMapper.teamDtoToTeamEntity(teamDto)).thenReturn(team);

        ResponseEntity<String> responseEntity = teamService.createTeam(teamDto);

        assertEquals(HttpStatus.OK, responseEntity.getStatusCode());
        assertEquals("Team created successfully!" , responseEntity.getBody());

        verify(teamRepository).findByTeamName(teamDto.getTeamName());
        verify(teamRepository).save(team);
    }

    @Test
    void createTeamTest_WhenInvalidName_ThenRespondBadRequest() {
        TeamDto teamDto = new TeamDto();
        when(teamRepository.findByTeamName(teamDto.getTeamName())).thenReturn(Optional.of(new Team()));

        assertThrows(EntityAlreadyExistsException.class, () -> teamService.createTeam(teamDto));
    }

    @Test
    void deleteTeamTest_WhenValidId_ThenRespondOk() {
        Team team = new Team();
        when(teamRepository.findById(id)).thenReturn(Optional.of(team));

        ResponseEntity<String> responseEntity = teamService.deleteTeam(id);

        assertEquals(HttpStatus.OK, responseEntity.getStatusCode());
        assertEquals("Deleted team successfully!", responseEntity.getBody());

        verify(teamRepository).findById(id);
        verify(teamRepository).delete(team);
    }

    @Test
    void deleteTeamTest_WhenInvalidId_ThenThrowException() {
        when(teamRepository.findById(id)).thenReturn(Optional.empty());

        Exception exception = assertThrows(Exception.class, () -> teamService.deleteTeam(id));

        assertEquals("Team with ID " + id + " is not found!", exception.getMessage());

        verify(teamRepository).findById(id);
    }
}
