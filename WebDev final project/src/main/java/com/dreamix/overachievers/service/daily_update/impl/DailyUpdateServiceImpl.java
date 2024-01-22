package com.dreamix.overachievers.service.daily_update.impl;

import com.dreamix.overachievers.dto.daily_update.DailyUpdateDto;
import com.dreamix.overachievers.dto.employee.EmployeeDto;
import com.dreamix.overachievers.entity.DailyUpdate;
import com.dreamix.overachievers.entity.Employee;
import com.dreamix.overachievers.entity.Team;
import com.dreamix.overachievers.exception.EntityAlreadyExistsException;
import com.dreamix.overachievers.mapper.DailyUpdateMapper;
import com.dreamix.overachievers.repository.DailyUpdateRepository;
import com.dreamix.overachievers.service.daily_update.DailyUpdateService;
import com.dreamix.overachievers.service.emlpoyee.EmployeeService;
import com.dreamix.overachievers.service.team.TeamService;
import com.dreamix.overachievers.util.DateUtil;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
@Slf4j
public class DailyUpdateServiceImpl implements DailyUpdateService {
    private final DailyUpdateRepository dailyUpdateRepository;
    private final DailyUpdateMapper dailyUpdateMapper;
    private final EmployeeService employeeService;
    private final TeamService teamService;

    @Autowired
    public DailyUpdateServiceImpl(DailyUpdateRepository dailyUpdateRepository,
                                  DailyUpdateMapper dailyUpdateMapper,
                                  EmployeeService employeeService,
                                  TeamService teamService) {
        this.dailyUpdateRepository = dailyUpdateRepository;
        this.dailyUpdateMapper = dailyUpdateMapper;
        this.employeeService = employeeService;
        this.teamService = teamService;
    }

    @Override
    public ResponseEntity<String> createDailyUpdate(DailyUpdateDto dailyUpdateDto) {
        EmployeeDto employeeDto = employeeService.getEmployeeDtoById(dailyUpdateDto.getSender().getId());

        Optional<DailyUpdate> dailyUpdateOptional =
                dailyUpdateRepository.findBySenderIdAndCreatedAt(employeeDto.getId(),
                        DateUtil.getStartOfTheDay(), DateUtil.getEndOfTheDay());

        if (dailyUpdateOptional.isPresent()) {
            throw new EntityAlreadyExistsException("Employee with id: " + employeeDto.getId()
                    + " has already submitted their daily update!");
        }

        DailyUpdate dailyUpdate = dailyUpdateMapper.dailyUpdateDtoToDailyUpdateEntity(dailyUpdateDto);
        dailyUpdateRepository.save(dailyUpdate);

        return new ResponseEntity<>("Daily update created successfully!", HttpStatus.OK);
    }

    @Override
    public List<DailyUpdateDto> getDailyUpdatesByTeamName(String teamName) {
        Team team = teamService.getTeamEntityByTeamName(teamName);

        List<Employee> employees = employeeService.getEmployeeEntitiesByTeamEntity(team);

        List<DailyUpdate> dailyUpdates = new ArrayList<>();

        for (Employee employee : employees) {
            Optional<DailyUpdate> dailyUpdate = dailyUpdateRepository.
                    findBySenderIdAndCreatedAt(employee.getId(),
                            DateUtil.getStartOfTheDay(), DateUtil.getEndOfTheDay());

            dailyUpdate.ifPresent(dailyUpdates::add);
        }

        return dailyUpdateMapper.dailyUpdateEntityToDailyUpdateDto(dailyUpdates);
    }
}