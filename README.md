# Library Manager

Spring Boot JSP application for managing two related entities: `Author` and `Book`.

## Features

- JPA entities with a one-to-many relationship from authors to books.
- H2 in-memory database with 10 seeded authors and 10 seeded books.
- Create, read, and update operations through JSP pages.
- Repository-layer custom JPQL inner join projection for book and author data.
- Service-layer business logic and repository integration.
- Unit tests for repository and service methods.

## Run Locally

Install Java 17 and Maven, then run:

```bash
mvn spring-boot:run
```

Open:

- Books: `http://localhost:8080/books`
- Authors: `http://localhost:8080/authors`
- H2 Console: `http://localhost:8080/h2-console`

H2 settings:

- JDBC URL: `jdbc:h2:mem:librarydb`
- User: `sa`
- Password: leave blank

## Test

```bash
mvn test
```

## Project Structure

```text
src/main/java/com/example/librarymanager
  controller/   Spring MVC controllers
  dto/          Inner join projection object
  model/        JPA entities
  repository/   JpaRepository interfaces and custom query
  service/      Business logic

src/main/resources
  application.properties
  data.sql
  static/css/styles.css

src/main/webapp/WEB-INF/jsp
  authors/
  books/
  fragments/
```

