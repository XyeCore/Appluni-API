# === Stage 1: Build ===
FROM maven:3.9.5-eclipse-temurin-21-alpine AS build
WORKDIR /app
COPY . .
RUN mvn clean package -DskipTests

# === Stage 2: Run ===
FROM eclipse-temurin:21-jdk-alpine
WORKDIR /app

COPY .env .env

ENV SPRING_DATASOURCE_URL=${SPRING_DATASOURCE_URL}
ENV SPRING_DATASOURCE_USERNAME=${SPRING_DATASOURCE_USERNAME}
ENV SPRING_DATASOURCE_PASSWORD=${SPRING_DATASOURCE_PASSWORD}
ENV TOKEN_SIGNING_KEY=${TOKEN_SIGNING_KEY}


COPY --from=build /app/target/*.jar app.jar

EXPOSE 8080
ENTRYPOINT ["java", "-jar", "app.jar"]