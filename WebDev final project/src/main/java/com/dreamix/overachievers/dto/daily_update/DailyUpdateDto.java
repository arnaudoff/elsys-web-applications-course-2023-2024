package com.dreamix.overachievers.dto.daily_update;

import com.dreamix.overachievers.dto.employee.EmployeeDto;
import lombok.Data;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import java.time.LocalDateTime;

@Data
public class DailyUpdateDto {
    private Long id;

    @NotNull
    private EmployeeDto sender;

    private LocalDateTime createdAt;

    private LocalDateTime lastUpdatedAt;

    @NotBlank
    private String videoUrl;
}
