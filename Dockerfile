# Этап 1: сборка
FROM maven:3.9-eclipse-temurin-21 AS builder
WORKDIR /app
COPY pom.xml .
COPY src ./src
RUN mvn clean package -DskipTests

# Этап 2: запуск
FROM openjdk:26-ea-slim
WORKDIR /app
COPY --from=builder /app/target/*.jar pgs-backend-0.0.1-SNAPSHOT.jar
EXPOSE 8080
ENTRYPOINT ["java", "-jar", "pgs-backend-0.0.1-SNAPSHOT.jar"]
