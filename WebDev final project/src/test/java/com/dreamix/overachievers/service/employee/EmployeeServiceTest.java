package com.dreamix.overachievers.service.employee;

import com.dreamix.overachievers.dto.daily_update.DailyUpdateInfoDto;
import com.dreamix.overachievers.dto.employee.EmployeeDto;
import com.dreamix.overachievers.dto.employee.EmployeeInfoDto;
import com.dreamix.overachievers.dto.review.ReviewInfoDto;
import com.dreamix.overachievers.dto.team.TeamDto;
import com.dreamix.overachievers.entity.DailyUpdate;
import com.dreamix.overachievers.entity.Employee;
import com.dreamix.overachievers.entity.Review;
import com.dreamix.overachievers.entity.Team;
import com.dreamix.overachievers.exception.EntityAlreadyExistsException;
import com.dreamix.overachievers.mapper.DailyUpdateMapper;
import com.dreamix.overachievers.mapper.EmployeeMapper;
import com.dreamix.overachievers.mapper.ReviewMapper;
import com.dreamix.overachievers.repository.DailyUpdateRepository;
import com.dreamix.overachievers.repository.EmployeeRepository;
import com.dreamix.overachievers.repository.ReviewRepository;
import com.dreamix.overachievers.service.emlpoyee.impl.EmployeeServiceImpl;
import com.dreamix.overachievers.util.DateUtil;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import java.util.*;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

class EmployeeServiceTest {
    private final Long id = 1L;
    @Mock
    private EmployeeRepository employeeRepository;
    @Mock
    private DailyUpdateRepository dailyUpdateRepository;
    @Mock
    private ReviewRepository reviewRepository;
    @Mock
    private DailyUpdateMapper dailyUpdateMapper;
    @Mock
    private ReviewMapper reviewMapper;
    @Mock
    private EmployeeMapper employeeMapper;
    @InjectMocks
    private EmployeeServiceImpl employeeService;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void getAllEmployeesTest() {

        List<Employee> employees = Arrays.asList(new Employee(), new Employee());
        when(employeeRepository.findAll()).thenReturn(employees);

        employeeService.getAllEmployees();

        verify(employeeRepository).findAll();
    }
    @Test
    void getEmployeeByIdTest_WhenValidId_ThenReturnEmployeeDto() {
        
        Employee employee = new Employee();
        DailyUpdate dailyUpdate = new DailyUpdate();
        TeamDto team = new TeamDto();
        EmployeeInfoDto employeeInfoDto = new EmployeeInfoDto();
        team.setTeamName("John");
        employeeInfoDto.setTeam(team);
        List<Review> reviewList = Arrays.asList(new Review(), new Review());
        when(employeeRepository.findById(id)).thenReturn(Optional.of(employee));
        when(employeeMapper.employeeEntityToEmployeeInfoDto(employee)).thenReturn(employeeInfoDto);
        when(reviewRepository.findAllByReceiverId(id)).thenReturn(reviewList);
        when(dailyUpdateRepository.findBySenderIdAndCreatedAt(
                id,
                DateUtil.getStartOfTheDay(),
                DateUtil.getEndOfTheDay())).thenReturn(Optional.of(dailyUpdate));
        when(dailyUpdateMapper.dailyUpdateEntityToDailyUpdateInfoDto(dailyUpdate))
                .thenReturn(new DailyUpdateInfoDto());
        when(reviewMapper.reviewEntityToReviewInfoDto(reviewList))
                .thenReturn(Arrays.asList(new ReviewInfoDto(), new ReviewInfoDto()));
        employeeService.getEmployeeInfoDtoByEmployeeId(id, team);

        verify(employeeRepository).findById(id);
    }

    @Test
    void getEmployeeByIdTest_WhenInvalidId_ThenThrowException() {
        
        when(employeeRepository.findById(id)).thenReturn(Optional.empty());

        Exception exception = assertThrows(Exception.class, () -> employeeService.getEmployeeInfoDtoByEmployeeId(id, null));
        assertEquals("Employee with ID " + id + " is not found!", exception.getMessage());

        verify(employeeRepository).findById(id);
    }

    @Test
    void createEmployeeTest_WhenValidEmail_ThenRespondOk() {
        EmployeeDto employeeDto = new EmployeeDto();
        Employee employee = new Employee();
        when(employeeRepository.findByEmail(employeeDto.getEmail())).thenReturn(Optional.empty());
        when(employeeMapper.employeeDtoToEmployeeEntity(employeeDto)).thenReturn(employee);

        ResponseEntity<String> result = employeeService.createEmployee(employeeDto);

        assertEquals(HttpStatus.OK, result.getStatusCode());
        assertEquals("Successfully created employee!", result.getBody());

        verify(employeeRepository).save(employee);
        verify(employeeRepository).findByEmail(employeeDto.getEmail());
    }

    @Test
    void createEmployeeTest_WhenInvalidEmail_ThenRespondBadRequest() {
        when(employeeRepository.findByEmail(new EmployeeDto().getEmail())).thenReturn(Optional.of(new Employee()));
        assertThrows(EntityAlreadyExistsException.class, () -> employeeService.createEmployee(new EmployeeDto()));
    }

    @Test
    void deleteEmployeeTest_WhenValidId_ThenRespondOk() {

        Employee employee = new Employee();
        when(employeeRepository.findById(id)).thenReturn(Optional.of(employee));

        ResponseEntity<String> result = employeeService.deleteEmployeeById(id);

        assertEquals(HttpStatus.OK, result.getStatusCode());
        assertEquals("Deleted employee successfully!", result.getBody());
    }

    @Test
    void deleteEmployeeTest_WhenInvalidId_ThenThrowException() {
        
        when(employeeRepository.findById(id)).thenReturn(Optional.empty());

        assertThrows(Exception.class, () -> employeeService.deleteEmployeeById(id));
    }

    @Test
    void getEmployeeDtoTest()
    {
        EmployeeDto employeeDto = new EmployeeDto();
        EmployeeInfoDto employeeInfoDto = new EmployeeInfoDto();
        when(employeeMapper.employeeInfoDtoToEmployeeDto(employeeInfoDto)).thenReturn(employeeDto);

        assertEquals(employeeDto, employeeService.getEmployeeDto(employeeInfoDto));
    }

    @Test
    void getEmployeeEntitiesByTeamEntityTest()
    {
        Team team = new Team();
        team.setId(id);
        employeeService.getEmployeeEntitiesByTeamEntity(team);

        verify(employeeRepository).findByTeamId(any(Long.class));
    }

}
