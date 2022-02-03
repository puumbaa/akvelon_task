package ru.akvelon.trainee.task.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import ru.akvelon.trainee.task.enums.TaskStatus;
import ru.akvelon.trainee.task.models.Task;

import java.util.List;
import java.util.stream.Collectors;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class TaskDto {
    private Long id;
    private String name;
    private String description;
    private String status;
    private int priority;
    private Long projectId;

    public static TaskDto from(Task task){
        Long projectId = task.getProject() == null ? null : task.getProject().getId();
        return TaskDto.builder()
                .id(task.getId())
                .name(task.getName())
                .description(task.getDescription())
                .status(task.getStatus().name())
                .priority(task.getPriority())
                .projectId(projectId)
                .build();
    }
    public static List<TaskDto> from(List<Task> tasks){
        return tasks.stream()
                .map(TaskDto::from)
                .collect(Collectors.toList());
    }

}
