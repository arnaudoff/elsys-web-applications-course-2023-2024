package com.dreamix.overachievers.service.daily_update;

import com.dreamix.overachievers.dto.daily_update.DailyUpdateDto;
import com.dreamix.overachievers.dto.employee.EmployeeDto;
import com.dreamix.overachievers.entity.DailyUpdate;
import com.dreamix.overachievers.entity.Employee;
import com.dreamix.overachievers.entity.Team;
import com.dreamix.overachievers.exception.EntityAlreadyExistsException;
import com.dreamix.overachievers.mapper.DailyUpdateMapper;
import com.dreamix.overachievers.repository.DailyUpdateRepository;
import com.dreamix.overachievers.service.daily_update.impl.DailyUpdateServiceImpl;
import com.dreamix.overachievers.service.emlpoyee.EmployeeService;
import com.dreamix.overachievers.service.team.TeamService;
import com.dreamix.overachievers.util.DateUtil;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import java.time.LocalDateTime;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.*;

class DailyUpdateServiceTest {

    @Mock
    private DailyUpdateRepository dailyUpdateRepository;
    @Mock
    private EmployeeService employeeService;
    @Mock
    private TeamService teamService;
    @Mock
    private DailyUpdateMapper dailyUpdateMapper;
    @InjectMocks
    private DailyUpdateServiceImpl dailyUpdateService;

    @BeforeEach
    void setUp() { MockitoAnnotations.openMocks(this); }

    @Test
    void testCreateDailyUpdate_Success() {

        DailyUpdateDto dailyUpdateDto = new DailyUpdateDto();
        dailyUpdateDto.setSender(new EmployeeDto());

        EmployeeDto employeeDto = new EmployeeDto();
        when(employeeService.getEmployeeDtoById(dailyUpdateDto.getSender().getId())).thenReturn(employeeDto);

        DailyUpdate dailyUpdate = new DailyUpdate();
        when(dailyUpdateRepository.findBySenderIdAndCreatedAt(employeeDto.getId(),
                dailyUpdate.getCreatedAt(), dailyUpdate.getLastUpdatedAt()))
                .thenReturn(Optional.empty());

        ResponseEntity<String> result = dailyUpdateService.createDailyUpdate(dailyUpdateDto);
        assertEquals(HttpStatus.OK, result.getStatusCode());
        assertEquals("Daily update created successfully!", result.getBody());

    }

    @Test
    void testCreateDailyUpdate_AlreadyExists() {

        DailyUpdateDto dailyUpdateDto = new DailyUpdateDto();
        dailyUpdateDto.setSender(new EmployeeDto());

        EmployeeDto employeeDto = new EmployeeDto();
        when(employeeService.getEmployeeDtoById(dailyUpdateDto.getSender().getId())).thenReturn(employeeDto);

        when(dailyUpdateRepository.findBySenderIdAndCreatedAt(employeeDto.getId(),
                DateUtil.getStartOfTheDay(), DateUtil.getEndOfTheDay()))
                .thenReturn(Optional.of(new DailyUpdate()));

        assertThrows(EntityAlreadyExistsException.class, () -> dailyUpdateService.createDailyUpdate(dailyUpdateDto));

    }

    @Test
    void testGetDailyUpdatesByTeamName() {

        Team team = new Team();
        team.setTeamName("");
        when(teamService.getTeamEntityByTeamName("")).thenReturn(team);
        when(employeeService.getEmployeeEntitiesByTeamEntity(team))
                .thenReturn(Arrays.asList(new Employee(), new Employee()));

        DailyUpdate dailyUpdate = new DailyUpdate();
        when(dailyUpdateRepository.
                findBySenderIdAndCreatedAt(eq(null),
                        any(LocalDateTime.class), any(LocalDateTime.class)))
                .thenReturn(Optional.of(dailyUpdate));

        List<DailyUpdate> dailyUpdates = Arrays.asList(new DailyUpdate(), new DailyUpdate());
        List<DailyUpdateDto> dailyUpdateDtoList = Arrays.asList(new DailyUpdateDto(), new DailyUpdateDto());
        when(dailyUpdateMapper.dailyUpdateEntityToDailyUpdateDto(dailyUpdates))
                .thenReturn(dailyUpdateDtoList);

        assertEquals(2, dailyUpdateService.getDailyUpdatesByTeamName("").size());

    }

}