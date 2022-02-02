package ru.akvelon.trainee.task.models;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.fasterxml.jackson.databind.ser.std.ToStringSerializer;
import lombok.*;
import ru.akvelon.trainee.task.enums.ProjectStatus;

import javax.persistence.*;
import java.time.Instant;
import java.time.LocalDate;
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
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd")
    @JsonSerialize(using = ToStringSerializer.class)
    private LocalDate startDate;
    @Column(name = "completion_date")
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd")
    @JsonSerialize(using = ToStringSerializer.class)
    private LocalDate completionDate;
    private ProjectStatus status;
    private int priority;
    @OneToMany(mappedBy = "project")
    private List<Task> tasks;

}
