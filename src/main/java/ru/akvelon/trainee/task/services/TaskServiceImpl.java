package ru.akvelon.trainee.task.services;

import org.springframework.stereotype.Service;
import ru.akvelon.trainee.task.dto.TaskDto;
import ru.akvelon.trainee.task.enums.TaskStatus;
import ru.akvelon.trainee.task.exceptions.InvalidDataException;
import ru.akvelon.trainee.task.exceptions.TaskNotFoundException;
import ru.akvelon.trainee.task.models.Task;
import ru.akvelon.trainee.task.repositories.ProjectRepositoryJpaImpl;
import ru.akvelon.trainee.task.repositories.TaskRepositoryJpaImpl;

import java.util.List;

import static ru.akvelon.trainee.task.dto.TaskDto.from;
import static ru.akvelon.trainee.task.utils.MyUtils.isEnumConstant;

@Service
public class TaskServiceImpl implements TaskService {

    private final TaskRepositoryJpaImpl taskRepository;
    private final ProjectRepositoryJpaImpl projectRepository;

    public TaskServiceImpl(TaskRepositoryJpaImpl taskRepository, ProjectRepositoryJpaImpl projectRepository) {
        this.taskRepository = taskRepository;
        this.projectRepository = projectRepository;
    }


    @Override
    public List<TaskDto> findAll() {
        return from(taskRepository.findAll());
    }

    @Override
    public TaskDto findById(Long id) {
        return from(taskRepository.findById(id)
                .orElseThrow(() -> new TaskNotFoundException(id)));
    }

    @Override
    public TaskDto save(TaskDto newTask) {
        if (!isEnumConstant(TaskStatus.class, newTask.getStatus())){
            throw new InvalidDataException("No enum constant!");
        }
        Task task = Task.builder()
                .name(newTask.getName())
                .description(newTask.getDescription())
                .project(projectRepository.findById(newTask.getProjectId()).orElse(null))
                .priority(newTask.getPriority())
                .status(TaskStatus.valueOf(newTask.getStatus()))
                .build();
        return from(taskRepository.save(task));
    }

    @Override
    public boolean remove(Long id) {
        return taskRepository.removeById(id) != null;
    }

    @Override
    public TaskDto update(TaskDto newTask, Long id) {
        Task task = taskRepository.findById(id)
                .orElseThrow(() -> new TaskNotFoundException(id));

        task.setName(newTask.getName());
        task.setDescription(newTask.getDescription());
        task.setPriority(newTask.getPriority());
        task.setStatus(TaskStatus.valueOf(newTask.getStatus()));
        task.setProject(projectRepository.findById(newTask.getProjectId()).orElse(null));

        return from(taskRepository.save(task));
    }
}
