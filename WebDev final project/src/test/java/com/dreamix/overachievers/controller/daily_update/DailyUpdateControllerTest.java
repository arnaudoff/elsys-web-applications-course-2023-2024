package com.dreamix.overachievers.controller.daily_update;

import com.dreamix.overachievers.dto.daily_update.DailyUpdateDto;
import com.dreamix.overachievers.dto.employee.EmployeeDto;
import com.dreamix.overachievers.dto.team.TeamDto;
import com.dreamix.overachievers.service.daily_update.DailyUpdateService;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import java.util.ArrayList;
import java.util.List;

import static org.mockito.Mockito.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

class DailyUpdateControllerTest {
    private MockMvc mvc;
    @Mock
    private DailyUpdateService dailyUpdateService;

    @InjectMocks
    private DailyUpdateController dailyUpdateController;

    @BeforeEach
    public void setUp() {
        MockitoAnnotations.openMocks(this);
        mvc = MockMvcBuilders.standaloneSetup(dailyUpdateController).build();
    }

    @Test
    void createDailyUpdateTest() throws Exception {
        EmployeeDto sender = new EmployeeDto();
        sender.setId(1L);

        DailyUpdateDto dailyUpdateDto = new DailyUpdateDto();
        dailyUpdateDto.setSender(sender);
        dailyUpdateDto.setVideoUrl("randomTestCaseUrl.com");

        mvc.perform(post("/daily_updates")
                        .content(new ObjectMapper().writeValueAsString(dailyUpdateDto))
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk());
    }

@Test
    void getAllDailyUpdatesByTeam() throws Exception {
        TeamDto teamDto = new TeamDto();
        teamDto.setTeamName("Apollo");

        DailyUpdateDto update1 = new DailyUpdateDto();
        DailyUpdateDto update2 = new DailyUpdateDto();
        List<DailyUpdateDto> dailyUpdates = new ArrayList<>();
        dailyUpdates.add(update1);
        dailyUpdates.add(update2);

        when(dailyUpdateService.getDailyUpdatesByTeamName(teamDto.getTeamName())).thenReturn(dailyUpdates);

        ObjectMapper objectMapper = new ObjectMapper();

        mvc.perform(get("/daily_updates")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(teamDto)))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.length()").value(dailyUpdates.size()));
    }
}
