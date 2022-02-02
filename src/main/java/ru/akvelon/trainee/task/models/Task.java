package ru.akvelon.trainee.task.models;

import lombok.*;
import ru.akvelon.trainee.task.enums.TaskStatus;
import javax.persistence.*;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
@ToString(exclude = "project")
@EqualsAndHashCode(exclude = "project")
public class Task {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String name;
    private String description;
    private TaskStatus status;
    private int priority;
    @ManyToOne
    @JoinColumn(name = "project_id")
    private Project project;

}
