### Tech Stack

- Java 21
- Spring Boot 3.3.3
- PostgreSQL 16

### Setup
- Clone project
- Create database demoauth in PostgreSQL
- Run the project

### Features

- Application secured with Spring Security and JWT
- Authentication and Authorization
- Role Based Access Control
- DataInitializer file contains credentials for admin and user
- admin can access all endpoints
- user can access endpoints without `@PreAuthorize` and those with `@PreAuthorize("hasRole('USER')")`