package com.dreamix.overachievers.controller.daily_update;

import com.dreamix.overachievers.dto.daily_update.DailyUpdateDto;
import com.dreamix.overachievers.dto.exception.ExceptionDto;
import com.dreamix.overachievers.dto.team.TeamDto;
import com.dreamix.overachievers.entity.DailyUpdate;
import com.dreamix.overachievers.service.daily_update.DailyUpdateService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.Parameters;
import io.swagger.v3.oas.annotations.media.ArraySchema;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@Tag(name = "Daily Update", description = "Daily updates endpoints")
@RestController
@RequestMapping("/daily_updates")
@Slf4j
@CrossOrigin
public class DailyUpdateController {
    private final DailyUpdateService dailyUpdateService;

    @Autowired
    public DailyUpdateController(DailyUpdateService dailyUpdateService) {
        this.dailyUpdateService = dailyUpdateService;
    }

    @Operation(
            summary = "Create a daily update with its DTO",
            description = "Create a daily update with a JSON request which requires the sender employee DTO (with only one daily update per day allowed per employee) and a video URL",
            tags = { "daily update", "post" })
    @ApiResponses({
            @ApiResponse(responseCode = "200", description = "Successfully creating a daily update"),
            @ApiResponse(responseCode = "500", description = "Incorrect request or serverside error") })
    @Parameters(value = { @Parameter(name = "dailyUpdateDto",
            description = "The daily update DTO with the sender employee DTO & video URL") })
    @PostMapping
    ResponseEntity<String> createDailyUpdate(@RequestBody @Valid DailyUpdateDto dailyUpdateDto) {
        log.info("Creating daily update!");
        return dailyUpdateService.createDailyUpdate(dailyUpdateDto);
    }

    @Operation(
            summary = "Get all daily updates by team DTO",
            description = "Get all the daily updates in a team by a team DTO which includes the teamName",
            tags = { "daily update", "get", "by team" })
    @ApiResponses({
            @ApiResponse(responseCode = "200",
                    description = "Successfully fetched the daily updates",
                    content = { @Content(
                            array = @ArraySchema(schema = @Schema(implementation = DailyUpdate.class)),
                            mediaType = "application/json") }),
            @ApiResponse(responseCode = "404",
                    description = "Team with this name doesn't exist",
                    content = { @Content(
                            schema = @Schema(implementation = ExceptionDto.class),
                            mediaType = "application/json") }),
            @ApiResponse(responseCode = "500",
                    description = "Incorrect request or serverside error",
                    content = { @Content() }) })
    @Parameters(value = { @Parameter(name = "teamDto",
            description = "The team DTO with the team name") })
    @GetMapping
    List<DailyUpdateDto> getAllDailyUpdatesByTeam(@RequestBody @Valid TeamDto teamDto) {
        log.info("Getting all daily updates for team: " + teamDto.getTeamName());
        return dailyUpdateService.getDailyUpdatesByTeamName(teamDto.getTeamName());
    }
}
