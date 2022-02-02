package ru.akvelon.trainee.task.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import ru.akvelon.trainee.task.models.Task;

public interface TaskRepositoryJpaImpl extends JpaRepository<Task,Long> {
}
