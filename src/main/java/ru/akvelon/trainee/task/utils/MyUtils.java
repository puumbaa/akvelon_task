package ru.akvelon.trainee.task.utils;

import ru.akvelon.trainee.task.enums.ProjectStatus;
import ru.akvelon.trainee.task.exceptions.InvalidDataException;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

public class MyUtils {
    private static final String DATE_PATTERN = "yyyy-MM-dd";

    public static LocalDate parseDate(String date) {
        return LocalDate.parse(date, DateTimeFormatter.ofPattern(DATE_PATTERN));
    }

    public static void validateProjectData(LocalDate start, LocalDate end, int priority, String status) {
        if (start.compareTo(end) >= 0 || priority < 0 || !isEnumConstant(ProjectStatus.class,status)) {
            throw new InvalidDataException("Invalid input data");
        }
    }

    public static<T extends Enum<T>> boolean isEnumConstant(Class<T> clazz, String value) {
        try {
            Enum.valueOf(clazz,value);
            return true;
        }catch (RuntimeException ex){
            return false;
        }
    }
}
