# Этап 1: сборка
FROM maven:3.9-eclipse-temurin-21 AS builder
WORKDIR /app
COPY pom.xml .
COPY src ./src
COPY docs ./docs
RUN mvn clean package -DskipTests

# Этап 2: запуск
FROM openjdk:26-ea-slim
WORKDIR /app
COPY --from=builder /app/target/*.jar bank-rest-1.jar
COPY --from=builder /app/docs/openapi.yaml /app/docs/openapi.yaml
EXPOSE 8080
ENTRYPOINT ["java", "-jar", "bank-rest-1.jar"]
