package com.dreamix.overachievers.mapper;

import com.dreamix.overachievers.dto.employee.EmployeeDto;
import com.dreamix.overachievers.dto.team.TeamDto;
import com.dreamix.overachievers.entity.Employee;
import com.dreamix.overachievers.entity.Team;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Named;
import org.mapstruct.NullValueCheckStrategy;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Mapper(nullValueCheckStrategy = NullValueCheckStrategy.ALWAYS, componentModel = "spring")
@Component
public interface TeamMapper {
    @Mapping(source = "employees", target = "employees", qualifiedByName = "employeeToEmployeeDto")
    TeamDto teamEntityToTeamDto(Team src);

    List<TeamDto> teamEntityToTeamDto(List<Team> src);

    Team teamDtoToTeamEntity(TeamDto src);

    List<Team> teamDtoToTeamEntity(List<TeamDto> src);

    @Named("employeeToEmployeeDto")
    default List<EmployeeDto> employeeToEmployeeDto(List<Employee> employees) {
        List<EmployeeDto> result = new ArrayList<>();

        for (Employee employee : employees) {
            result.add(MapperHelper.toEmployeeDto(employee));
        }

        return result;
    }
}
