package com.dreamix.overachievers.mapper;

import com.dreamix.overachievers.dto.employee.EmployeeDto;
import com.dreamix.overachievers.dto.team.TeamDto;
import com.dreamix.overachievers.entity.Employee;

import javax.naming.OperationNotSupportedException;

public class MapperHelper {
    private MapperHelper() throws OperationNotSupportedException {
        throw new OperationNotSupportedException("MapperHelper cannot be instantiated!");
    }
    public static EmployeeDto toEmployeeDto(Employee employee) {
        EmployeeDto employeeDto = new EmployeeDto();
        employeeDto.setId(employee.getId());
        employeeDto.setFirstName(employee.getFirstName());
        employeeDto.setLastName(employee.getLastName());
        employeeDto.setEmail(employee.getEmail());
        employeeDto.setPictureUrl(employee.getPictureUrl());
        employeeDto.setPosition(employee.getPosition());
        return employeeDto;
    }

    public static EmployeeDto toEmployeeDtoWithTeam(Employee employee) {
        EmployeeDto employeeDto = toEmployeeDto(employee);
        TeamDto teamDto = new TeamDto();
        teamDto.setId(employee.getTeam().getId());
        teamDto.setTeamName(employee.getTeam().getTeamName());
        employeeDto.setTeam(teamDto);
        return employeeDto;
    }
}
