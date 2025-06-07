# Appluni API

**Appluni API** is a server-side application built with **Spring Boot** that provides a RESTful interface for managing universities, academic programs, and student applications.  
The project uses JWT-based authentication and stores data in PostgreSQL.

## Technologies
- Java 21  
- Spring Boot 3 (Web, Security, Data JPA, Validation)  
- PostgreSQL  
- JJWT (JWT generation and validation)  
- Springdoc OpenAPI (Swagger UI)  
- Lombok  
- dotenv-java  
- Maven  
- Docker

## Running Locally
1. Clone the repository:
   ```bash
   git clone <repo_url>
   cd Appluni-API
   ```
2. Create a `.env` file and specify environment variables:
   ```dotenv
   SPRING_DATASOURCE_URL=jdbc:postgresql://localhost:5432/appluni
   SPRING_DATASOURCE_USERNAME=user
   SPRING_DATASOURCE_PASSWORD=secret
   TOKEN_SIGNING_KEY=<Base64-key>
   ```
   Or set them as system environment variables.

3. Build and run the application:
   ```bash
   ./mvnw spring-boot:run
   ```
   By default, the server listens at `http://localhost:8080`.

4. Swagger UI is available for API exploration:  
   `http://localhost:8080/swagger-ui/index.html`

## Running with Docker
The project includes a Dockerfile for building a containerized version of the application:
```bash
docker build . \
  --build-arg SPRING_DATASOURCE_URL=jdbc:postgresql://db:5432/appluni \
  --build-arg SPRING_DATASOURCE_USERNAME=user \
  --build-arg SPRING_DATASOURCE_PASSWORD=secret \
  --build-arg TOKEN_SIGNING_KEY=<Base64-key> \
  -t appluni-backend:latest

docker run -p 8080:8080 appluni-backend:latest
```

## Tests
To run the tests:
```bash
mvn test
```

## License
This project is licensed under the MIT License.  
See the [LICENSE](LICENSE) file for details.
