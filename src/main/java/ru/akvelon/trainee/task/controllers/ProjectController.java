package ru.akvelon.trainee.task.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import ru.akvelon.trainee.task.models.Project;
import ru.akvelon.trainee.task.models.Task;
import ru.akvelon.trainee.task.services.ProjectService;

import java.util.List;

@RestController
@RequestMapping("/projects")
public class ProjectController {
    private final ProjectService projectService;

    @Autowired
    public ProjectController(ProjectService projectService) {
        this.projectService = projectService;
    }

    @GetMapping
    public List<Project> getAllProjects(){
        return projectService.findAll();
    }

    @GetMapping("/{id}")
    public Project getProjectById(@PathVariable("id") Long id){
        return projectService.findById(id);
    }

    @GetMapping("/{id}/tasks")
    public List<Task> getTasksByProjectId(@PathVariable("id") Long id){
        return projectService.findTasksById(id);
    }

    @PostMapping
    public Project addProject(@RequestBody Project project){
        return projectService.save(project);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<String> removeProject(@PathVariable("id") Long id){
        if (projectService.remove(id)) {
            return new ResponseEntity<>("Project with id: " + id + " successfully deleted", HttpStatus.OK);
        }
        return new ResponseEntity<>("Something went wrong...",HttpStatus.BAD_REQUEST);
    }

    @PutMapping("/{id}")
    public Project updateProject(@RequestBody Project newProject, @PathVariable("id") Long id){
        return projectService.update(newProject,id);
    }
}
