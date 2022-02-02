package ru.akvelon.trainee.task.models;

import lombok.*;
import ru.akvelon.trainee.task.enums.ProjectStatus;

import javax.persistence.*;
import java.time.Instant;
import java.util.List;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
@ToString(exclude = "tasks")
@EqualsAndHashCode(exclude = "tasks")
public class Project {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String name;
    @Column(name = "start_date")
    private Instant startDate;
    @Column(name = "completion_date")
    private Instant completionDate;
    private ProjectStatus status;
    private int priority;

    @OneToMany(mappedBy = "project")
    private List<Task> tasks;

}
