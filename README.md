# Foyer Backend

A secure REST API for university housing management, built with Spring Boot and JWT authentication.

## Tech Stack
- Java 17 + Spring Boot 3.3.0
- Spring Security with JWT (jjwt 0.11.5)
- JPA / Hibernate + MySQL
- Swagger / OpenAPI (springdoc 2.1.0)
- Lombok + AOP Logging

## Architecture
- **Controllers** — REST endpoints with role-based access (@PreAuthorize)
- **Services** — Business logic layer with AOP logging
- **Repositories** — Spring Data JPA
- **Security** — Stateless JWT filter chain, BCrypt password encoding
- **Exception Handling** — Global @RestControllerAdvice returning structured JSON errors

## Features
- JWT Authentication (register, login, token validation)
- Role-based access control (ROLE_ADMIN, ROLE_STUDENT)
- University housing management (Foyers, Blocs, Chambres, Etudiants, Reservations)
- Room reservation and release system
- Dashboard statistics endpoint
- AOP-based request/error logging
- CORS configured for frontend integration

## API Docs
Available at `/tpfoyer/swagger-ui/index.html` when running locally.
