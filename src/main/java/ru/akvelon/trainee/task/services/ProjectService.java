package ru.akvelon.trainee.task.services;

import ru.akvelon.trainee.task.models.Project;
import ru.akvelon.trainee.task.models.Task;

import java.util.List;

public interface ProjectService {

    List<Project> findAll();

    Project findById(Long id);

    List<Task> findTasksById(Long id);

    Project save(Project project);

    boolean remove(Long id);

    Project update(Project newProject, Long id);
}
