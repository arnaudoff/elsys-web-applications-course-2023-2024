package com.dreamix.overachievers.dto.review;

import com.dreamix.overachievers.dto.employee.EmployeeDto;
import com.dreamix.overachievers.vo.ReviewType;
import lombok.Data;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import java.time.LocalDateTime;

@Data
public class ReviewDto {
    private Long id;

    @NotNull
    private EmployeeDto receiver;
    @NotNull
    private EmployeeDto sender;

    private LocalDateTime createdAt;

    private LocalDateTime lastUpdatedAt;

    @NotBlank
    private String videoUrl;

    @NotNull
    private ReviewType type;

}