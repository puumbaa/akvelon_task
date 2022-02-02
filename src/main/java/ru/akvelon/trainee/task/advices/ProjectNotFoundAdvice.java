package ru.akvelon.trainee.task.advices;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;
import ru.akvelon.trainee.task.exceptions.ProjectNotFoundException;

@ControllerAdvice
public class ProjectNotFoundAdvice {
    @ResponseBody
    @ExceptionHandler(value = {ProjectNotFoundException.class})
    @ResponseStatus(HttpStatus.NOT_FOUND)
    public String projectNotFoundHandler(ProjectNotFoundException ex){
        return ex.getMessage();
    }

}
