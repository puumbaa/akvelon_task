package ru.akvelon.trainee.task.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.transaction.annotation.Transactional;
import ru.akvelon.trainee.task.models.Project;

public interface ProjectRepositoryJpaImpl extends JpaRepository<Project, Long> {
    @Transactional
    Long removeById(Long id);
}
