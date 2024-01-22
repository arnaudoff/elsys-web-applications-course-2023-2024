package com.dreamix.overachievers.controller.team;

import com.dreamix.overachievers.dto.exception.ExceptionDto;
import com.dreamix.overachievers.dto.team.TeamDto;
import com.dreamix.overachievers.entity.Team;
import com.dreamix.overachievers.service.team.TeamService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.Parameters;
import io.swagger.v3.oas.annotations.media.ArraySchema;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Tag(name = "Team", description = "Teams endpoints")
@RestController
@RequestMapping("/teams")
@CrossOrigin
public class TeamController {
    private final TeamService teamService;

    @Autowired
    public TeamController(TeamService teamService) {
        this.teamService = teamService;
    }

    @Operation(
            summary = "Get all teams",
            description = "Get all teams as a list",
            tags = { "team", "get", "all" })
    @ApiResponses({
            @ApiResponse(responseCode = "200",
                    description = "Successfully fetched all teams",
                    content = { @Content(
                            array = @ArraySchema(schema = @Schema(implementation = Team.class)),
                            mediaType = "application/json") }),
            @ApiResponse(responseCode = "500",
                    description = "Incorrect request or serverside error",
                    content = { @Content() }) })
    @GetMapping
    public List<TeamDto> getAllTeams() {
        return teamService.getAllTeams();
    }

    @Operation(
            summary = "Create team",
            description = "Create a team by its DTO (no id, unique name)",
            tags = { "team", "post", "create" })
    @ApiResponses({
            @ApiResponse(responseCode = "200",
                    description = "Successfully created team",
                    content = { @Content() }),
            @ApiResponse(responseCode = "409",
                    description = "Team with this name already exists",
                    content = { @Content(
                            schema = @Schema(implementation = ExceptionDto.class),
                            mediaType = "application/json") }),
            @ApiResponse(responseCode = "500",
                    description = "Incorrect request or serverside error",
                    content = { @Content() }) })
    @Parameters(value = { @Parameter(name = "teamDto",
            description = "The team DTO (without id, unique name) for the team entity") })
    @PostMapping
    public ResponseEntity<String> createTeam(@RequestBody TeamDto teamDto) {
        return teamService.createTeam(teamDto);
    }

    @Operation(
            summary = "Delete team",
            description = "Delete a team by its id",
            tags = { "team", "delete", "by id" })
    @ApiResponses({
            @ApiResponse(responseCode = "200",
                    description = "Successfully deleted team",
                    content = { @Content() }),
            @ApiResponse(responseCode = "404",
                    description = "Team with this id doesn't exist",
                    content = { @Content(
                            schema = @Schema(implementation = ExceptionDto.class),
                            mediaType = "application/json") }),
            @ApiResponse(responseCode = "500",
                    description = "Incorrect request or serverside error",
                    content = { @Content() }) })
    @Parameters(value = { @Parameter(name = "id", description = "The team id") })
    @DeleteMapping("/{id}")
    public ResponseEntity<String> deleteTeam(@PathVariable Long id) {
        return teamService.deleteTeam(id);
    }
}
