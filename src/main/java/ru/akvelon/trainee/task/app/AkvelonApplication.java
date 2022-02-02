package ru.akvelon.trainee.task.app;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;


@SpringBootApplication
@EntityScan(basePackages = "ru.akvelon.trainee.task.models")
@EnableJpaRepositories(basePackages = "ru.akvelon.trainee.task.repositories")
public class AkvelonApplication {

    public static void main(String[] args) {
        SpringApplication.run(AkvelonApplication.class, args);
    }

}
