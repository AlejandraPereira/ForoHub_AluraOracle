ForoHub - Challenge Backend ONE Alura

ForoHub is a REST API developed using Spring Boot 3, designed to simulate the functionality of a forum.

Features

Login: By entering a valid email and password, a JWT token is generated that allows access to the other functionalities in a secure manner.

Create a topic: Users can generate new discussion topics in the forum.

List topics: A list of all created topics is displayed.

Edit topics: Allows you to modify a specific topic.

Delete topics: Allows you to delete a topic from the forum.

Installation

Prerequisites

Java 17.0.7 or higher installed.

Maven installed.

PostgreSQL configured and running.

Steps:

1- Create a database and connect to it using environment variables or by modifying the application.properties file.

2- Clone this repository:

    git clone https://github.com/JuanJoseVillamizar/ForoHub.git
    cd foro-hub
3- Configure your environment variables or update application.properties:

    spring.datasource.url= jdbc:postgresql://{DB_HOST}/{DB_NAME_FORO}
    spring.datasource.username={DB_USER}
    spring.datasource.password={DB_PASSWORD}
    security.jwt.key.private=${PRIVATE_KEY}
    security.jwt.user.generator=${USER_GENERATOR}
    security.authjwt.backend=${YOUR_AUTHORIZATION}
    
4- Compile and run project

    mvn clean install
    ./mvnw spring-boot:run

5- Access the application at http://localhost:8080.

Technologies Used

Backend

Java 17: Main programming language.

Spring Boot 3.4: Framework for backend development.

Spring Security: Handles authentication and authorization.

Spring Data JPA: Data persistence.

Spring Validation: Input validations.

Spring test: Test.

Database

PostgreSQL: Relational database.

Flyway: Database migration and versioning.

Tools and Utilities

Swagger: Interactive API documentation.

Lombok: Simplifies Java code.

Maven: Dependency management.

Interactive Documentation

You can explore and test the API endpoints using the Swagger-generated documentation. Once the project is running, access it at:

[Swagger](http://localhost:8080/swagger-ui/index.html)

Security

The backend implements authentication and authorization using:

Spring Security: General app security and authentication.

JWT Tokens: To protect API endpoints.

BCrypt: For password encryption.

Contributions

¡Contributions are welcomed! to contribute:


Fork the repository.

Create a new branch for your feature or your fix: git checkout -b feature/new feature.

Make the changes and confirms commits: git commit -m "adding new feature".

Send a Pull Requets describe your changes.
