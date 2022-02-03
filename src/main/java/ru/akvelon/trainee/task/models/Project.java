package ru.akvelon.trainee.task.models;

import com.fasterxml.jackson.annotation.JsonManagedReference;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.fasterxml.jackson.databind.ser.std.ToStringSerializer;
import lombok.*;
import ru.akvelon.trainee.task.enums.ProjectStatus;

import javax.persistence.*;
import java.time.LocalDate;
import java.util.List;

@Data
@Entity
@Builder
@AllArgsConstructor
@NoArgsConstructor
@ToString(exclude = "tasks")
@EqualsAndHashCode(exclude = "tasks")
public class Project {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String name;
    @Column(name = "start_date", nullable = false)
    @JsonSerialize(using = ToStringSerializer.class)
    private LocalDate startDate;
    @Column(name = "completion_date", nullable = false)
    @JsonSerialize(using = ToStringSerializer.class)
    private LocalDate completionDate;
    @Column(nullable = false)
    private ProjectStatus status;
    private int priority;
    @OneToMany(mappedBy = "project", cascade = CascadeType.ALL)
    @JsonManagedReference
    private List<Task> tasks;

}
