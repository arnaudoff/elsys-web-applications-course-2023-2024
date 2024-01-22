package com.dreamix.overachievers.dto.employee;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotBlank;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class UpdateEmployeeDto {
    @NotBlank
    private String firstName;

    @NotBlank
    private String lastName;
}
