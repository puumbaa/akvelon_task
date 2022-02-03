package ru.akvelon.trainee.task.controllers;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import ru.akvelon.trainee.task.dto.ProjectDto;
import ru.akvelon.trainee.task.dto.TaskDto;
import ru.akvelon.trainee.task.services.TaskService;

import java.util.List;

@RestController
@RequestMapping("/tasks")
public class TaskController {
    private final TaskService taskService;

    public TaskController(TaskService taskService) {
        this.taskService = taskService;
    }


    @Operation(summary = "Get all tasks")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Tasks found",
                    content = @Content(schema = @Schema(implementation = TaskDto.class),
                            mediaType = "application/json"
                    )),
            @ApiResponse(responseCode = "404", description = "Not found")})
    @GetMapping
    public List<TaskDto> getAllTasks() {
        return taskService.findAll();
    }


    @Operation(summary = "Get task by id")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Task found",
                    content = @Content(schema = @Schema(implementation = TaskDto.class),
                            mediaType = "application/json"
                    )),
            @ApiResponse(responseCode = "404", description = "Task not found")})
    @GetMapping("/{id}")
    public TaskDto getTaskById(@PathVariable("id") Long id) {
        return taskService.findById(id);
    }


    @Operation(summary = "Add task")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Task successfully added",
                    content = @Content(schema = @Schema(implementation = TaskDto.class),
                            mediaType = "application/json"
                    )),
            @ApiResponse(responseCode = "400", description = "Incorrect request parameters")})
    @PostMapping
    public TaskDto addTask(@RequestBody TaskDto task) {
        return taskService.save(task);
    }


    @Operation(summary = "Remove task")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Task successfully removed",
                    content = @Content(schema = @Schema(implementation = TaskDto.class),
                            mediaType = "application/json"
                    )),
            @ApiResponse(responseCode = "404", description = "Task not found")})
    @DeleteMapping("/{id}")
    public ResponseEntity<?> removeTask(@PathVariable("id") Long id) {
        if (taskService.remove(id)) {
            return new ResponseEntity<>("Project with id: " + id + " successfully deleted", HttpStatus.OK);
        }
        return new ResponseEntity<>("Something went wrong...", HttpStatus.BAD_REQUEST);
    }

    @Operation(summary = "Update task")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Task successfully updated",
                    content = @Content(schema = @Schema(implementation = TaskDto.class),
                            mediaType = "application/json"
                    )),
            @ApiResponse(responseCode = "400", description = "Incorrect request parameters"),
            @ApiResponse(responseCode = "404", description = "Task not found")})
    @PutMapping("/{id}")
    public TaskDto updateTask(@PathVariable("id") Long id, @RequestBody TaskDto newTask) {
        return (taskService.update(newTask, id));
    }
}
