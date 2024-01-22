package com.dreamix.overachievers.controller.employee;

import com.dreamix.overachievers.dto.employee.EmployeeDto;
import com.dreamix.overachievers.dto.employee.EmployeeInfoDto;
import com.dreamix.overachievers.dto.exception.ExceptionDto;
import com.dreamix.overachievers.dto.team.TeamDto;
import com.dreamix.overachievers.entity.Employee;
import com.dreamix.overachievers.service.emlpoyee.EmployeeService;
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
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@Tag(name = "Employee", description = "Employees endpoints")
@RestController
@RequestMapping("/employees")
@CrossOrigin
public class EmployeeController {
    private final EmployeeService employeeService;

    @Autowired
    public EmployeeController(EmployeeService employeeService) {
        this.employeeService = employeeService;
    }

    @Operation(
            summary = "Get all employees",
            description = "Get all employees as a list",
            tags = { "employee", "get", "all" })
    @ApiResponses({
            @ApiResponse(responseCode = "200",
                    description = "Successfully fetched all employees",
                    content = { @Content(
                            array = @ArraySchema(schema = @Schema(implementation = Employee.class)),
                            mediaType = "application/json") }),
            @ApiResponse(responseCode = "500",
                    description = "Incorrect request or serverside error",
                    content = { @Content() }) })
    @GetMapping
    public List<EmployeeDto> getAllEmployees() {
        return employeeService.getAllEmployees();
    }

    @Operation(
            summary = "Get employee",
            description = "Get an employee by its id",
            tags = { "employee", "get", "by id" })
    @ApiResponses({
            @ApiResponse(responseCode = "200",
                    description = "Successfully fetched employee by id",
                    content = { @Content(
                            schema = @Schema(implementation = Employee.class),
                            mediaType = "application/json") }),
            @ApiResponse(responseCode = "404",
                    description = "Employee with this id doesn't exist",
                    content = { @Content(
                            schema = @Schema(implementation = ExceptionDto.class),
                            mediaType = "application/json") }),
            @ApiResponse(responseCode = "500",
                    description = "Incorrect request or serverside error",
                    content = { @Content() }) })
    @Parameters(value = { @Parameter(name = "id", description = "The employee id"),
            @Parameter(name = "teamDto", description = "The team DTO of the employee")})
    @GetMapping("/{id}")
    public EmployeeInfoDto getEmployeeById(@PathVariable Long id, @RequestBody @Valid TeamDto teamDto) {
        return employeeService.getEmployeeInfoDtoByEmployeeId(id, teamDto);
    }

    @Operation(
            summary = "Create employee",
            description = "Create an employee by its DTO (no id)",
            tags = { "employee", "post", "create" })
    @ApiResponses({
            @ApiResponse(responseCode = "200",
                    description = "Successfully created employee",
                    content = { @Content() }),
            @ApiResponse(responseCode = "409",
                    description = "Employee with this email already exists",
                    content = { @Content(
                            schema = @Schema(implementation = ExceptionDto.class),
                            mediaType = "application/json") }),
            @ApiResponse(responseCode = "500",
                    description = "Incorrect request or serverside error",
                    content = { @Content() }) })
    @Parameters(value = { @Parameter(name = "employeeDto",
            description = "The employee DTO (without id) for the employee entity") })
    @PostMapping
    public ResponseEntity<String> createEmployee(@RequestBody @Validated EmployeeDto employeeDto) {
        return employeeService.createEmployee(employeeDto);
    }

    @Operation(
            summary = "Delete employee",
            description = "Delete an employee by its id",
            tags = { "employee", "delete", "by id" })
    @ApiResponses({
            @ApiResponse(responseCode = "200",
                    description = "Successfully deleted employee",
                    content = { @Content() }),
            @ApiResponse(responseCode = "404",
                    description = "Employee with this id doesn't exist",
                    content = { @Content(
                            schema = @Schema(implementation = ExceptionDto.class),
                            mediaType = "application/json") }),
            @ApiResponse(responseCode = "500",
                    description = "Incorrect request or serverside error",
                    content = { @Content() }) })
    @Parameters(value = { @Parameter(name = "id", description = "The employee id") })
    @DeleteMapping("/{id}")
    public ResponseEntity<String> deleteEmployeeById(@PathVariable Long id) {
        return employeeService.deleteEmployeeById(id);
    }

}
