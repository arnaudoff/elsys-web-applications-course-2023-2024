package com.dreamix.overachievers.mapper;

import com.dreamix.overachievers.dto.daily_update.DailyUpdateDto;
import com.dreamix.overachievers.dto.daily_update.DailyUpdateInfoDto;
import com.dreamix.overachievers.dto.employee.EmployeeDto;
import com.dreamix.overachievers.entity.DailyUpdate;
import com.dreamix.overachievers.entity.Employee;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Named;
import org.mapstruct.NullValueCheckStrategy;
import org.springframework.stereotype.Component;

import java.util.List;

@Mapper(nullValueCheckStrategy = NullValueCheckStrategy.ALWAYS, componentModel = "spring")
@Component
public interface DailyUpdateMapper {
    DailyUpdate dailyUpdateDtoToDailyUpdateEntity(DailyUpdateDto src);

    List<DailyUpdateDto> dailyUpdateEntityToDailyUpdateDto(List<DailyUpdate> src);

    @Mapping(source = "sender", target = "sender", qualifiedByName = "employeeToEmployeeDto")
    DailyUpdateDto dailyUpdateEntityToDailyUpdateDto(DailyUpdate src);

    @Named("employeeToEmployeeDto")
    default EmployeeDto employeeToEmployeeDto(Employee employee) {
        return MapperHelper.toEmployeeDtoWithTeam(employee);
    }

    DailyUpdateInfoDto dailyUpdateEntityToDailyUpdateInfoDto(DailyUpdate src);

}