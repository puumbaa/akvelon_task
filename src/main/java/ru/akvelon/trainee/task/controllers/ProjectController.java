package ru.akvelon.trainee.task.controllers;

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

    @GetMapping("/{id}")
    public ProjectDto getProjectById(@PathVariable("id") Long id) {
        return projectService.findById(id);
    }

    @GetMapping
    public List<ProjectDto> getProjects(@RequestParam(value = "from", required = false)
                                        @DateTimeFormat(pattern = "yyyy-MM-dd") LocalDate from,
                                        @RequestParam(value = "to", required = false)
                                        @DateTimeFormat(pattern = "yyyy-MM-dd") LocalDate to,
                                        @RequestParam(value = "sort", required = false, defaultValue = SORT_DEFAULT)
                                                    String fieldName) {
        return projectService.findAllByStartDateBetween(from, to, fieldName);
    }


    @GetMapping("/search")
    public List<ProjectDto> getProjectsByNameLike(@RequestParam(value = "name",required = false, defaultValue = "") String name,
                                                  @RequestParam(value = "sort", required = false, defaultValue = SORT_DEFAULT) String fieldName) {
        return projectService.findAllByNameLike(name, fieldName);
    }

    @GetMapping("/{id}/tasks")
    public List<TaskDto> getTasksByProjectId(@PathVariable("id") Long id) {
        return projectService.findTasksById(id);
    }

    @PostMapping
    public ProjectDto addProject(@RequestBody ProjectDto project) {
        return projectService.save(project);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> removeProject(@PathVariable("id") Long id) {
        if (projectService.remove(id)) {
            return new ResponseEntity<>("Project with id: " + id + " successfully deleted", HttpStatus.OK);
        }
        return new ResponseEntity<>("Something went wrong...", HttpStatus.BAD_REQUEST);
    }

    @PutMapping("/{id}")
    public ProjectDto updateProject(@RequestBody ProjectDto newProject, @PathVariable("id") Long id) {
        return projectService.update(newProject, id);
    }
}
