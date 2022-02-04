package ru.akvelon.trainee.task.advices;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;
import ru.akvelon.trainee.task.exceptions.ProjectNotFoundException;
import ru.akvelon.trainee.task.exceptions.TaskNotFoundException;

import javax.persistence.EntityNotFoundException;

@ControllerAdvice
public class EntityNotFoundAdvice {
    @ResponseBody
    @ExceptionHandler(value = {ProjectNotFoundException.class, TaskNotFoundException.class, EntityNotFoundException.class})
    public ResponseEntity<?> entityNotFoundHandler(RuntimeException ex) {
        return new ResponseEntity<>(ex.getMessage(), HttpStatus.NOT_FOUND);
    }
}
