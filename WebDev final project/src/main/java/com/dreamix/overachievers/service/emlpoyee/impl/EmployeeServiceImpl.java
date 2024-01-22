package com.dreamix.overachievers.service.emlpoyee.impl;

import com.dreamix.overachievers.dto.employee.EmployeeDto;
import com.dreamix.overachievers.dto.employee.EmployeeInfoDto;
import com.dreamix.overachievers.dto.team.TeamDto;
import com.dreamix.overachievers.entity.Employee;
import com.dreamix.overachievers.entity.Team;
import com.dreamix.overachievers.exception.EntityAlreadyExistsException;
import com.dreamix.overachievers.exception.EntityNotFoundException;
import com.dreamix.overachievers.mapper.DailyUpdateMapper;
import com.dreamix.overachievers.mapper.EmployeeMapper;
import com.dreamix.overachievers.mapper.ReviewMapper;
import com.dreamix.overachievers.repository.DailyUpdateRepository;
import com.dreamix.overachievers.repository.EmployeeRepository;
import com.dreamix.overachievers.repository.ReviewRepository;
import com.dreamix.overachievers.service.emlpoyee.EmployeeService;
import com.dreamix.overachievers.util.DateUtil;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@Slf4j
public class EmployeeServiceImpl implements EmployeeService {
    private static final String EMPLOYEE = "Employee";
    private final EmployeeRepository employeeRepository;
    private final DailyUpdateRepository dailyUpdateRepository;
    private final ReviewRepository reviewRepository;
    private final EmployeeMapper employeeMapper;
    private final DailyUpdateMapper dailyUpdateMapper;
    private final ReviewMapper reviewMapper;

    @Autowired
    public EmployeeServiceImpl(EmployeeRepository employeeRepository, DailyUpdateRepository dailyUpdateRepository, ReviewRepository reviewRepository, EmployeeMapper employeeMapper, DailyUpdateMapper dailyUpdateMapper, ReviewMapper reviewMapper) {
        this.employeeRepository = employeeRepository;
        this.dailyUpdateRepository = dailyUpdateRepository;
        this.reviewRepository = reviewRepository;
        this.employeeMapper = employeeMapper;
        this.dailyUpdateMapper = dailyUpdateMapper;
        this.reviewMapper = reviewMapper;
    }

    @Override
    public List<EmployeeDto> getAllEmployees() {
        return employeeMapper.employeeEntityToEmployeeDto(employeeRepository.findAll());
    }
    @Override
    public EmployeeInfoDto getEmployeeInfoDtoByEmployeeId(Long id, TeamDto teamDto) {
        EmployeeInfoDto employeeDto =  employeeMapper
                .employeeEntityToEmployeeInfoDto(
                        employeeRepository
                                .findById(id)
                                .orElseThrow(() -> new EntityNotFoundException(EMPLOYEE, id)));

        if(teamDto.getTeamName().equals(employeeDto.getTeam().getTeamName())) {
            employeeDto.setDailyUpdate(dailyUpdateMapper.dailyUpdateEntityToDailyUpdateInfoDto(
                    dailyUpdateRepository.findBySenderIdAndCreatedAt(
                            id,
                            DateUtil.getStartOfTheDay(),
                            DateUtil.getEndOfTheDay()
                    ).orElse(null)
            ));

            employeeDto.setReviews(reviewMapper.reviewEntityToReviewInfoDto(
                    reviewRepository.findAllByReceiverId(id)));
        }
        return employeeDto;
    }

    @Override
    public EmployeeDto getEmployeeDtoById(Long id) {
        return employeeMapper
                .employeeEntityToEmployeeDto(
                        employeeRepository
                                .findById(id)
                                .orElseThrow(() -> new EntityNotFoundException(EMPLOYEE, id)));
    }

    @Override
    public ResponseEntity<String> createEmployee(EmployeeDto employeeDto) {
        Optional<Employee> employeeOptional = employeeRepository.findByEmail(employeeDto.getEmail());

        if (employeeOptional.isPresent()) {
            throw new EntityAlreadyExistsException("Employee with email: "
                    + employeeDto.getEmail() + " already exists!");
        }

        Employee employee = employeeMapper.employeeDtoToEmployeeEntity(employeeDto);

        employeeRepository.save(employee);
        return new ResponseEntity<>( "Successfully created employee!", HttpStatus.OK);
    }

    @Override
    public ResponseEntity<String> deleteEmployeeById(Long id) {
        Employee employee =
                employeeRepository
                        .findById(id)
                        .orElseThrow(() -> new EntityNotFoundException(EMPLOYEE, id));

        employeeRepository.delete(employee);
        return new ResponseEntity<>("Deleted employee successfully!", HttpStatus.OK);
    }

    @Override
    public List<Employee> getEmployeeEntitiesByTeamEntity(Team team) {
        return employeeRepository.findByTeamId(team.getId());
    }
}
