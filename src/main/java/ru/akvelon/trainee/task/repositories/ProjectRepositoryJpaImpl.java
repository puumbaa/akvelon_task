package ru.akvelon.trainee.task.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import ru.akvelon.trainee.task.models.Project;

public interface ProjectRepositoryJpaImpl extends JpaRepository<Project, Long> {
}
