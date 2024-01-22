package com.dreamix.overachievers.controller.team;

import com.dreamix.overachievers.controller.team.TeamController;
import com.dreamix.overachievers.dto.employee.EmployeeDto;
import com.dreamix.overachievers.dto.team.TeamDto;
import com.dreamix.overachievers.service.team.TeamService;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;

class TeamControllerTest {
    private static final Long teamId = 1L;
    private MockMvc mockMvc;
    @Mock
    private TeamService teamService;

    @InjectMocks
    private TeamController teamController;

    @BeforeEach
    public void setUp() {
        MockitoAnnotations.openMocks(this);
        mockMvc = MockMvcBuilders.standaloneSetup(teamController).build();
    }

    @Test
    void getAllTeamsTest() throws Exception {
        List<TeamDto> teamList = Arrays.asList(
                new TeamDto(
                        1L,
                        "Hustlers",
                        null),
                new TeamDto()
        );
        when(teamService.getAllTeams()).thenReturn(teamList);

        mockMvc.perform(get("/teams"))
                .andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON))
                .andExpect(jsonPath("$[0].id").value(1))
                .andExpect(jsonPath("$[0].teamName").value("Hustlers"));
        verify(teamService, times(1)).getAllTeams();
    }

    @Test
    void createTeamTest() throws Exception {
        TeamDto teamDto = new TeamDto(
                1L,
                "Hustlers",
                null);

        when(teamService.createTeam(any(TeamDto.class)))
                .thenReturn(ResponseEntity.ok("Team created successfully!"));

        mockMvc.perform(post("/teams")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(new ObjectMapper().writeValueAsString(teamDto)))
                .andExpect(status().isOk())
                .andExpect(content().string("Team created successfully!"));

        verify(teamService, times(1)).createTeam(any(TeamDto.class));
        verifyNoMoreInteractions(teamService);
    }

    @Test
    void deleteTeamTest() throws Exception{
        when(teamService.deleteTeam(teamId)).thenReturn(
                new ResponseEntity<>("Deleted team successfully!", HttpStatus.OK)
        );

        mockMvc.perform(delete("/teams/{id}", teamId))
                .andExpect(status().isOk())
                .andExpect(content().string("Deleted team successfully!"));

        verify(teamService, times(1)).deleteTeam(teamId);
        verifyNoMoreInteractions(teamService);
    }
}
