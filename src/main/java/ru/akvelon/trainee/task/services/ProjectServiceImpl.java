package ru.akvelon.trainee.task.services;

import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import ru.akvelon.trainee.task.dto.ProjectDto;
import ru.akvelon.trainee.task.dto.TaskDto;
import ru.akvelon.trainee.task.enums.ProjectStatus;
import ru.akvelon.trainee.task.exceptions.InvalidDataException;
import ru.akvelon.trainee.task.exceptions.ProjectNotFoundException;
import ru.akvelon.trainee.task.models.Project;
import ru.akvelon.trainee.task.repositories.ProjectRepositoryJpaImpl;
import ru.akvelon.trainee.task.repositories.TaskRepositoryJpaImpl;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.List;

import static ru.akvelon.trainee.task.dto.ProjectDto.from;
import static ru.akvelon.trainee.task.utils.MyUtils.*;


@Service
public class ProjectServiceImpl implements ProjectService {

    private final ProjectRepositoryJpaImpl projectRepository;
    private final TaskRepositoryJpaImpl taskRepository;


    public ProjectServiceImpl(ProjectRepositoryJpaImpl projectRepository, TaskRepositoryJpaImpl taskRepository) {
        this.projectRepository = projectRepository;
        this.taskRepository = taskRepository;
    }


    @Override
    public List<ProjectDto> findAll() {
        return from(projectRepository.findAll(Sort.by("-priority")));
    }

    @Override
    public ProjectDto findById(Long id) {
        return from(projectRepository.findById(id)
                .orElseThrow(() -> new ProjectNotFoundException(id)));
    }

    @Override
    public List<TaskDto> findTasksById(Long id) {
        return TaskDto.from(taskRepository.findAllByProjectId(id));
    }

    @Override
    public ProjectDto save(ProjectDto newProject) {
        LocalDate start = parseDate(newProject.getStartDate());
        LocalDate end = parseDate(newProject.getCompletionDate());
        int priority = newProject.getPriority();
        String status = newProject.getStatus();

        validateProjectData(start, end, priority, status);

        Project project = Project.builder()
                .name(newProject.getName())
                .startDate(start)
                .completionDate(end)
                .status(ProjectStatus.valueOf(status))
                .priority(priority)
                .build();
        return from(projectRepository.save(project));
    }

    @Override
    public boolean remove(Long id) {
        return projectRepository.removeById(id) == 1;
    }

    @Override
    public ProjectDto update(ProjectDto newProject, Long id) {
        Project project = projectRepository.findById(id)
                .orElseThrow(() -> new ProjectNotFoundException(id));

        LocalDate start = parseDate(newProject.getStartDate());
        LocalDate end = parseDate(newProject.getCompletionDate());
        int priority = newProject.getPriority();
        String status = newProject.getStatus();

        validateProjectData(start, end, priority, status);

        project.setName(newProject.getName());
        project.setStartDate(start);
        project.setCompletionDate(end);
        project.setStatus(ProjectStatus.valueOf(status));
        project.setPriority(priority);

        return from(projectRepository.save(project));
    }

    @Override
    public List<ProjectDto> findAllByStartDateBetween(LocalDate from, LocalDate to, String fieldName) {

        Sort sort = fieldName.startsWith("-") ? Sort.by(fieldName.substring(1)).descending() : Sort.by(fieldName);


        if (from != null && to != null) {
            if (from.compareTo(to) >= 0) throw new InvalidDataException("Invalid date period");
            return from(projectRepository.findAllByStartDateBetween(from, to, sort));
        } else if (to == null && from != null) {
            return from(projectRepository.findAllByStartDateGreaterThanEqual(from, sort));
        } else if (to != null) {
            return from(projectRepository.findAllByStartDateLessThanEqual(to, sort));
        } else {
            return from(projectRepository.findAll(sort));
        }


    }

    @Override
    public List<ProjectDto> findAllByNameLike(String name, String fieldName) {
        Sort sort = fieldName.startsWith("-") ? Sort.by(fieldName.substring(1)).descending() : Sort.by(fieldName);
        return from(projectRepository.findAllByNameLike("%" + name + "%", sort));
    }





}
