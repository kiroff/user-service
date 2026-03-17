# user-service

Spring Boot user service with JPA persistence and security utilities.

## Stack
- Java 25
- Spring Boot 4.0.3
- Spring Data JPA
- Spring Security
- H2 in-memory database
- Maven

## Project Structure
- `src/main/java/org/kiroff/user_service/UserServiceApplication.java` - application entry point
- `src/main/resources/application.yaml` - application configuration

## Running Locally
```bash
./mvnw spring-boot:run
```

## Tests
```bash
./mvnw test
```

## Configuration
`application.yaml` is configured for an in-memory H2 database:

```yaml
spring:
  datasource:
    url: jdbc:h2:mem:userdb;DB_CLOSE_DELAY=-1;DB_CLOSE_ON_EXIT=FALSE
    driver-class-name: org.h2.Driver
    username: admin
    password: q
  jpa:
    hibernate:
      ddl-auto: create-drop
```

## Build
```bash
./mvnw clean package
```

## Notes
The POM pins Spring Boot 4.0.3 and Spring Cloud 2025.1.1, sets Java 25 as the target, and includes Keycloak server libraries.
