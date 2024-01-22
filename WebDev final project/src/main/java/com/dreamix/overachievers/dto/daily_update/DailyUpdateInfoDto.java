package com.dreamix.overachievers.dto.daily_update;

import lombok.Data;

import javax.validation.constraints.NotBlank;
import java.time.LocalDateTime;

@Data
public class DailyUpdateInfoDto {
    private Long id;

    private LocalDateTime createdAt;

    private LocalDateTime lastUpdatedAt;

    @NotBlank
    private String videoUrl;
}
