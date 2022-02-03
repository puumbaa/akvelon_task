package ru.akvelon.trainee.task.services;

import ru.akvelon.trainee.task.dto.ProjectDto;
import ru.akvelon.trainee.task.dto.TaskDto;
import ru.akvelon.trainee.task.models.Project;

import java.time.LocalDate;
import java.util.List;

public interface ProjectService {

    List<ProjectDto> findAll();

    ProjectDto findById(Long id);

    List<TaskDto> findTasksById(Long id);

    ProjectDto save(ProjectDto project);

    boolean remove(Long id);

    ProjectDto update(ProjectDto newProject, Long id);

    List<ProjectDto> findAllByStartDateBetween(LocalDate start, LocalDate end, String sort);

    List<ProjectDto> findAllByNameLike(String name, String sort);
}
