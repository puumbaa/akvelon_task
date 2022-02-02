package ru.akvelon.trainee.task.services;

import org.springframework.stereotype.Service;
import ru.akvelon.trainee.task.exceptions.ProjectNotFoundException;
import ru.akvelon.trainee.task.models.Project;
import ru.akvelon.trainee.task.models.Task;
import ru.akvelon.trainee.task.repositories.ProjectRepositoryJpaImpl;
import ru.akvelon.trainee.task.repositories.TaskRepositoryJpaImpl;

import java.util.List;

@Service
public class ProjectServiceImpl implements ProjectService {

    private final ProjectRepositoryJpaImpl projectRepository;
    private final TaskRepositoryJpaImpl taskRepository;

    public ProjectServiceImpl(ProjectRepositoryJpaImpl projectRepository, TaskRepositoryJpaImpl taskRepository) {
        this.projectRepository = projectRepository;
        this.taskRepository = taskRepository;
    }

    @Override
    public List<Project> findAll() {
        return projectRepository.findAll();
    }

    @Override
    public Project findById(Long id) {
        return projectRepository.findById(id)
                .orElseThrow(() -> new ProjectNotFoundException(id));
    }

    @Override
    public List<Task> findTasksById(Long id) {
        return taskRepository.findAllByProjectId(id);
    }

    @Override
    public Project save(Project project) {
        return projectRepository.save(project);
    }

    @Override
    public boolean remove(Long id) {
        return projectRepository.removeById(id) == 1;
    }

    @Override
    public Project update(Project newProject, Long id) {
        Project project = projectRepository.findById(id)
                .orElseThrow(() -> new ProjectNotFoundException(id));

        project.setName(newProject.getName());
        project.setStartDate(newProject.getStartDate());
        project.setCompletionDate(newProject.getCompletionDate());
        project.setStatus(newProject.getStatus());
        project.setPriority(newProject.getPriority());
        project.setTasks(newProject.getTasks());

        return projectRepository.save(project);
    }
}
