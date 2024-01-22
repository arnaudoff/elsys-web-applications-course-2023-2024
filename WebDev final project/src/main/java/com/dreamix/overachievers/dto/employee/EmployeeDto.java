package com.dreamix.overachievers.dto.employee;

import com.dreamix.overachievers.dto.team.TeamDto;
import com.dreamix.overachievers.vo.Position;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class EmployeeDto {
    private Long id;

    @NotBlank
    private String firstName;

    @NotBlank
    private String lastName;

    @NotBlank
    private String email;

    private String pictureUrl;

    @NotNull
    private Position position;

    private TeamDto team;
}
