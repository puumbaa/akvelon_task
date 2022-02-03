package ru.akvelon.trainee.task.dto;


import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import ru.akvelon.trainee.task.models.Project;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.List;
import java.util.stream.Collectors;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class ProjectDto {
    private Long id;
    private String name;
    private String startDate;
    private String completionDate;
    private String status;
    private int priority;
    private List<TaskDto> tasks;

    public static ProjectDto from(Project project){

        return ProjectDto.builder()
                .id(project.getId())
                .name(project.getName())
                .startDate(project.getStartDate().toString())
                .completionDate(project.getCompletionDate().toString())
                .status(project.getStatus().name())
                .priority(project.getPriority())
                .tasks(TaskDto.from(project.getTasks()))
                .build();
    }

    public static List<ProjectDto> from(List<Project> projectList){
        return projectList.stream()
                .map(ProjectDto::from)
                .collect(Collectors.toList());
    }


}
