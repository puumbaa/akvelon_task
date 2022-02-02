package ru.akvelon.trainee.task.exceptions;

public class ProjectNotFoundException extends RuntimeException{
    public ProjectNotFoundException(Long id){
        super("Couldn't find project with id " + id);
    }
}
