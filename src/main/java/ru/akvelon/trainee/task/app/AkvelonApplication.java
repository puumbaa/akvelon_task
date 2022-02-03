package ru.akvelon.trainee.task.app;

import io.swagger.v3.oas.annotations.OpenAPIDefinition;
import io.swagger.v3.oas.annotations.info.Contact;
import io.swagger.v3.oas.annotations.info.Info;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.transaction.annotation.EnableTransactionManagement;

@OpenAPIDefinition(
        info = @Info(
                title = "Task tracker",
                version = "0.1",
                description = "Test project for intern position",
                contact = @Contact(name = "Send email", email = "sardarovelnur@gmail.com")))
@EnableTransactionManagement
@SpringBootApplication(scanBasePackages = "ru.akvelon.trainee.task")
@EntityScan(basePackages = "ru.akvelon.trainee.task.models")
@EnableJpaRepositories(basePackages = "ru.akvelon.trainee.task.repositories")
public class AkvelonApplication {

    public static void main(String[] args) {
        SpringApplication.run(AkvelonApplication.class, args);
    }

}

