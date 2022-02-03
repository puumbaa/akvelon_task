package ru.akvelon.trainee.task.advices;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import ru.akvelon.trainee.task.exceptions.InvalidDataException;


@ControllerAdvice
public class InvalidDataAdvice {
    @ResponseBody
    @ExceptionHandler(value = {InvalidDataException.class})
    public ResponseEntity<?> entityNotFoundHandler(RuntimeException ex) {
        return new ResponseEntity<>(ex.getMessage(), HttpStatus.BAD_REQUEST);
    }
}
