package com.dreamix.overachievers.service.emlpoyee;

import com.dreamix.overachievers.dto.employee.EmployeeDto;
import com.dreamix.overachievers.dto.employee.EmployeeInfoDto;
import com.dreamix.overachievers.dto.team.TeamDto;
import com.dreamix.overachievers.entity.Employee;
import com.dreamix.overachievers.entity.Team;
import org.springframework.http.ResponseEntity;

import java.util.List;

public interface EmployeeService {
    List<EmployeeDto> getAllEmployees();

    EmployeeInfoDto getEmployeeInfoDtoByEmployeeId(Long id, TeamDto teamDto);

    EmployeeDto getEmployeeDtoById(Long id);

    ResponseEntity<String> createEmployee(EmployeeDto employeeDto);

    ResponseEntity<String> deleteEmployeeById(Long id);

    List<Employee> getEmployeeEntitiesByTeamEntity(Team team);
}
