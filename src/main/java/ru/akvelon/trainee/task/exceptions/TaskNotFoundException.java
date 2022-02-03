package ru.akvelon.trainee.task.exceptions;

public class TaskNotFoundException extends RuntimeException {
    public TaskNotFoundException(Long id) {
        super("Couldn't find task with id " + id);
    }
}
