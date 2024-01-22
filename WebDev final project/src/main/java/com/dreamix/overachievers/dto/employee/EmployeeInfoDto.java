package com.dreamix.overachievers.dto.employee;

import com.dreamix.overachievers.dto.daily_update.DailyUpdateInfoDto;
import com.dreamix.overachievers.dto.review.ReviewInfoDto;
import com.dreamix.overachievers.dto.team.TeamDto;
import com.dreamix.overachievers.vo.Position;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class EmployeeInfoDto {

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

    private DailyUpdateInfoDto dailyUpdate;

    private List<ReviewInfoDto> reviews;
}
