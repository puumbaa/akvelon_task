package ru.akvelon.trainee.task.controllers;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.info.Contact;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import ru.akvelon.trainee.task.dto.ProjectDto;
import ru.akvelon.trainee.task.dto.TaskDto;
import ru.akvelon.trainee.task.services.ProjectService;

import java.time.LocalDate;
import java.util.List;


@RestController
@RequestMapping("/projects")
public class ProjectController {
    private final ProjectService projectService;
    private final static String SORT_DEFAULT = "-priority";

    @Autowired
    public ProjectController(ProjectService projectService) {
        this.projectService = projectService;
    }

    @Operation(summary = "Get project by id")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Project not",
                    content = @Content(schema = @Schema(implementation = ProjectDto.class),
                            mediaType = "application/json"
                    )),
            @ApiResponse(responseCode = "404", description = "Project not found")})
    @GetMapping("/{id}")
    public ProjectDto getProjectById(@PathVariable("id") Long id) {
        return projectService.findById(id);
    }


    @Operation(summary = "Get all projects")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Projects found",
                    content = @Content(schema = @Schema(implementation = ProjectDto.class),
                            mediaType = "application/json"
                    )),
            @ApiResponse(responseCode = "404", description = "Projects not found"),
            @ApiResponse(responseCode = "400", description = "Incorrect request parameter")})
    @GetMapping
    public List<ProjectDto> getProjects(@RequestParam(value = "from", required = false)
                                        @DateTimeFormat(pattern = "yyyy-MM-dd") LocalDate from,
                                        @RequestParam(value = "to", required = false)
                                        @DateTimeFormat(pattern = "yyyy-MM-dd") LocalDate to,
                                        @RequestParam(value = "sort", required = false, defaultValue = SORT_DEFAULT)
                                                String fieldName) {
        return projectService.findAllByStartDateBetween(from, to, fieldName);
    }


    @Operation(summary = "Search for a project by name")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "OK",
                    content = @Content(schema = @Schema(implementation = ProjectDto.class),
                            mediaType = "application/json"
                    ))})
    @GetMapping("/search")
    public List<ProjectDto> getProjectsByNameLike(@RequestParam(value = "name", required = false, defaultValue = "") String name,
                                                  @RequestParam(value = "sort", required = false, defaultValue = SORT_DEFAULT) String fieldName) {
        return projectService.findAllByNameLike(name, fieldName);
    }


    @Operation(summary = "Get all tasks in project")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Project found",
                    content = @Content(schema = @Schema(implementation = TaskDto.class),
                            mediaType = "application/json"
                    )),
            @ApiResponse(responseCode = "404", description = "Project not found")})
    @GetMapping("/{id}/tasks")
    public List<TaskDto> getTasksByProjectId(@PathVariable("id") Long id) {
        return projectService.findTasksById(id);
    }


    @Operation(summary = "Add project")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Project successfully added",
                    content = @Content(schema = @Schema(implementation = ProjectDto.class),
                            mediaType = "application/json"
                    )),
            @ApiResponse(responseCode = "400", description = "Incorrect request parameter")})
    @PostMapping
    public ProjectDto addProject(@RequestBody ProjectDto project) {
        return projectService.save(project);
    }


    @Operation(summary = "Remove project")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Project successfully removed",
                    content = @Content(schema = @Schema(implementation = ProjectDto.class),
                            mediaType = "application/json"
                    )),
            @ApiResponse(responseCode = "400", description = "Incorrect project id")})
    @DeleteMapping("/{id}")
    public ResponseEntity<?> removeProject(@PathVariable("id") Long id) {
        if (projectService.remove(id)) {
            return new ResponseEntity<>("Project with id: " + id + " successfully deleted", HttpStatus.OK);
        }
        return new ResponseEntity<>("Something went wrong...", HttpStatus.BAD_REQUEST);
    }


    @Operation(summary = "Update project")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Project successfully updated",
                    content = @Content(schema = @Schema(implementation = ProjectDto.class),
                            mediaType = "application/json"
                    )),
            @ApiResponse(responseCode = "400", description = "Incorrect request parameters"),
            @ApiResponse(responseCode = "404", description = "Project not found")})
    @PutMapping("/{id}")
    public ProjectDto updateProject(@RequestBody ProjectDto newProject, @PathVariable("id") Long id) {
        return projectService.update(newProject, id);
    }
}
