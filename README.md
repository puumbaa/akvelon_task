# Task tracker


This application is Web API for project and task management using Spring boot. 
The project is deployed on heroku. You can find it in the [link](https://akvelon-task-tracker.herokuapp.com/)


## Technology stack:
* Spring boot
* Spring data JPA
* PostgreSQL  
* Hikari CP
* Open API
* Lombok



## Packaging and running the application

The application can be cleaned and packaged using:
```shell script
mvnw clean package
```
It produces the `task-0.1.jar` file in the `target/ ` directory.

The application is now runnable using

```shell script
java -jar target/quarkus-run.jar
```

## API
To get acquainted with api, just follow the path /swagger-ui.html
