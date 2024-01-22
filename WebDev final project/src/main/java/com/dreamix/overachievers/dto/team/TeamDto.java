package com.dreamix.overachievers.dto.team;

import com.dreamix.overachievers.dto.employee.EmployeeDto;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotBlank;
import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class TeamDto {
    private Long id;

    @NotBlank
    private String teamName;

    private List<EmployeeDto> employees;
}
