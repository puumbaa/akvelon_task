package ru.akvelon.trainee.task.repositories;

import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.transaction.annotation.Transactional;
import ru.akvelon.trainee.task.models.Project;

import java.time.LocalDate;
import java.util.List;

public interface ProjectRepositoryJpaImpl extends JpaRepository<Project, Long> {
    @Transactional
    Long removeById(Long id);
    List<Project> findAllByStartDateBetween(LocalDate start, LocalDate end, Sort sort);
    List<Project> findAllByNameLike(String name, Sort sort);
    List<Project> findAllByStartDateGreaterThanEqual(LocalDate bound, Sort sort);
    List<Project> findAllByStartDateLessThanEqual(LocalDate bound, Sort sort);
}
