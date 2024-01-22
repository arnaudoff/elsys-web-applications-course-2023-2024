package com.dreamix.overachievers.mapper;

import com.dreamix.overachievers.dto.employee.EmployeeDto;

import com.dreamix.overachievers.dto.employee.EmployeeInfoDto;import com.dreamix.overachievers.dto.team.TeamDto;
import com.dreamix.overachievers.entity.Employee;
import com.dreamix.overachievers.entity.Team;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Named;
import org.mapstruct.NullValueCheckStrategy;
import org.springframework.stereotype.Component;

import java.util.List;

@Mapper(nullValueCheckStrategy = NullValueCheckStrategy.ALWAYS, componentModel = "spring")
@Component
public interface EmployeeMapper {
    List<EmployeeDto> employeeEntityToEmployeeDto(List<Employee> src);

    @Mapping(source = "team", target = "team", qualifiedByName = "teamToTeamDto")
    EmployeeDto employeeEntityToEmployeeDto(Employee src);

    List<Employee> employeeDtoToEmployeeEntity(List<EmployeeDto> src);

    Employee employeeDtoToEmployeeEntity(EmployeeDto src);

    @Named("teamToTeamDto")
    default TeamDto teamToTeamDto(Team team) {
        TeamDto teamDto = new TeamDto();
        teamDto.setId(team.getId());
        teamDto.setTeamName(team.getTeamName());
        return teamDto;
    }

    @Mapping(source = "team", target = "team", qualifiedByName = "teamToTeamDto")
    EmployeeInfoDto employeeEntityToEmployeeInfoDto(Employee src);

    EmployeeDto employeeInfoDtoToEmployeeDto(EmployeeInfoDto src);
}