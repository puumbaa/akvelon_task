package ru.akvelon.trainee.task.controllers;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
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

    @GetMapping
    public List<TaskDto> getAllTasks(){
        return taskService.findAll();
    }

    @GetMapping("/{id}")
    public TaskDto getTaskById(@PathVariable("id") Long id){
        return taskService.findById(id);
    }

    @PostMapping
    public TaskDto addTask(@RequestBody TaskDto task){
        return taskService.save(task);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> removeTask(@PathVariable("id") Long id){
        if (taskService.remove(id)) {
            return new ResponseEntity<>("Project with id: " + id + " successfully deleted", HttpStatus.OK);
        }
        return new ResponseEntity<>("Something went wrong...",HttpStatus.BAD_REQUEST);
    }

    @PutMapping("/{id}")
    public TaskDto updateTask(@PathVariable("id") Long id, TaskDto newTask){
        return (taskService.update(newTask,id));
    }
}
