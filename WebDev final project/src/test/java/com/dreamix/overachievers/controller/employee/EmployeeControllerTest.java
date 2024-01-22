package com.dreamix.overachievers.controller.employee;

import com.dreamix.overachievers.dto.employee.EmployeeDto;
import com.dreamix.overachievers.dto.employee.EmployeeInfoDto;
import com.dreamix.overachievers.dto.team.TeamDto;
import com.dreamix.overachievers.service.emlpoyee.EmployeeService;
import com.dreamix.overachievers.vo.Position;
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

import java.util.Arrays;
import java.util.List;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

class EmployeeControllerTest {
    private final Long employeeId = 1L;
    private MockMvc mockMvc;
    @Mock
    private EmployeeService employeeService;
    @InjectMocks
    private EmployeeController employeeController;

    @BeforeEach
    public void setUp() {
        MockitoAnnotations.openMocks(this);
        mockMvc = MockMvcBuilders.standaloneSetup(employeeController).build();
    }

    @Test
    void getAllEmployeesTest() throws Exception {
        List<EmployeeDto> employeeList = Arrays.asList(
                new EmployeeDto(
                        1L,
                        "John",
                        "Doe",
                        "johndoe@gmail.com",
                        "url",
                        Position.MIDDLE,
                        new TeamDto()),
                new EmployeeDto()
        );
        when(employeeService.getAllEmployees()).thenReturn(employeeList);

        mockMvc.perform(get("/employees"))
                .andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON))
                .andExpect(jsonPath("$[0].id").value(1))
                .andExpect(jsonPath("$[0].firstName").value("John"))
                .andExpect(jsonPath("$[0].lastName").value("Doe"))
                .andExpect(jsonPath("$[0].email").value("johndoe@gmail.com"))
                .andExpect(jsonPath("$[0].pictureUrl").value("url"))
                .andExpect(jsonPath("$[0].position").value("MIDDLE"));

        verify(employeeService, times(1)).getAllEmployees();
    }

    @Test
    void getEmployeeByIdTest() throws Exception {
        EmployeeInfoDto employeeInfoDto = new EmployeeInfoDto(
                1L,
                "John",
                "Doe",
                "johndoe@gmail.com",
                "url",
                Position.MIDDLE,
                new TeamDto(),
                null,
                null);
        TeamDto teamDto = new TeamDto();
        teamDto.setTeamName("John");
        when(employeeService.getEmployeeInfoDtoByEmployeeId(employeeId, teamDto)).thenReturn(employeeInfoDto);

        mockMvc.perform(get("/employees/{id}", employeeId)
                    .contentType(MediaType.APPLICATION_JSON)
                    .content(new ObjectMapper().writeValueAsString(teamDto)))
                .andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON))
                .andExpect(jsonPath("$.id").value(employeeId))
                .andExpect(jsonPath("$.firstName").value("John"))
                .andExpect(jsonPath("$.lastName").value("Doe"))
                .andExpect(jsonPath("$.email").value("johndoe@gmail.com"))
                .andExpect(jsonPath("$.pictureUrl").value("url"))
                .andExpect(jsonPath("$.position").value("MIDDLE"));

        verify(employeeService, times(1)).getEmployeeInfoDtoByEmployeeId(employeeId, teamDto);
        verifyNoMoreInteractions(employeeService);
    }

    @Test
    void createEmployeeTest() throws Exception {
        EmployeeDto employeeDto = new EmployeeDto(
                null,
                "John",
                "Doe",
                "johndoe@gmail.com",
                "url",
                Position.MIDDLE,
                new TeamDto());

        when(employeeService.createEmployee(any(EmployeeDto.class)))
                .thenReturn(ResponseEntity.ok("Employee created successfully!"));

        mockMvc.perform(post("/employees")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(new ObjectMapper().writeValueAsString(employeeDto)))
                .andExpect(status().isOk())
                .andExpect(content().string("Employee created successfully!"));

        verify(employeeService, times(1)).createEmployee(any(EmployeeDto.class));
        verifyNoMoreInteractions(employeeService);
    }
    
    @Test
    void deleteEmployeeTest() throws Exception{
        when(employeeService.deleteEmployeeById(employeeId)).thenReturn(
                new ResponseEntity<>("Deleted employee successfully!", HttpStatus.OK)
        );

        mockMvc.perform(delete("/employees/{id}", employeeId))
                .andExpect(status().isOk())
                .andExpect(content().string("Deleted employee successfully!"));

        verify(employeeService, times(1)).deleteEmployeeById(employeeId);
        verifyNoMoreInteractions(employeeService);
    }
}