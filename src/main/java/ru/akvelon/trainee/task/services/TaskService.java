package ru.akvelon.trainee.task.services;

import ru.akvelon.trainee.task.dto.TaskDto;

import java.util.List;

public interface TaskService {
    List<TaskDto> findAll();

    TaskDto findById(Long id);

    TaskDto save(TaskDto task);

    boolean remove(Long id);

    TaskDto update(TaskDto newTask, Long id);
}
