package ru.akvelon.trainee.task.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;
import ru.akvelon.trainee.task.models.Task;

import java.util.List;


public interface TaskRepositoryJpaImpl extends JpaRepository<Task,Long> {
    List<Task> findAllByProjectId(Long id);
    @Transactional
    Long removeById(Long id);
}
