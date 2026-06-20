# Foyer Backend

A secure REST API for university housing management, built with Spring Boot and JWT authentication.

## Tech Stack
- Java 17 + Spring Boot 3.3.0
- Spring Security with JWT (jjwt 0.11.5)
- JPA / Hibernate + MySQL
- Swagger / OpenAPI (springdoc 2.1.0)
- Lombok + AOP Logging

## Architecture

### Controllers
REST endpoints with role-based access control using @PreAuthorize. Each resource (Foyer, Bloc, Chambre, Etudiant, Reservation, Universite) exposes full CRUD operations plus custom business endpoints.

### Services
Business logic layer implementing domain rules such as room capacity validation per type (SIMPLE/DOUBLE/TRIPLE), reservation lifecycle management, and student-room affectation logic.

### DTOs
Data Transfer Objects decouple the API contract from internal JPA entities. LoginRequest DTO handles authentication input validation, ensuring clean separation between transport and persistence layers.

### AOP (Aspect-Oriented Programming)
Cross-cutting concerns are handled via a dedicated LoggingAspect:
- @Before — logs every service method invocation with method name
- @AfterReturning — confirms successful execution
- @AfterThrowing — captures and logs exceptions at the service layer without polluting business logic

### Security
Stateless JWT filter chain with BCrypt password encoding. JwtAuthenticationFilter intercepts every request, validates the Bearer token, and injects the authenticated principal into the Spring Security context.

### Exception Handling
Global @RestControllerAdvice returns structured JSON error responses:
- 400 Bad Request — validation and business rule violations
- 403 Forbidden — access denied
- 500 Internal Server Error — unexpected failures

## Features
- JWT Authentication (register, login, token validation)
- Role-based access control (ROLE_ADMIN, ROLE_STUDENT)
- University housing management (Foyers, Blocs, Chambres, Etudiants, Reservations)
- Room reservation and release system by student CIN
- Dashboard statistics endpoint
- AOP-based request/error logging across all service methods
- CORS configured for frontend integration

## API Docs
Available at /tpfoyer/swagger-ui/index.html when running locally.
