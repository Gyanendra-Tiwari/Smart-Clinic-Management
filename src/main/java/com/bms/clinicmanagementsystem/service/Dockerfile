# Stage 1: Build the application
FROM maven:3.8.6-openjdk-17-slim AS build

# Set working directory
WORKDIR /app

# Copy pom.xml and download dependencies (to leverage Docker cache)
COPY pom.xml .
RUN mvn dependency:go-offline

# Copy source code
COPY src ./src

# Build the Spring Boot jar
RUN mvn clean package -DskipTests

# Stage 2: Run the application
FROM openjdk:17-jdk-slim

WORKDIR /app

# Copy the jar file from the build stage
COPY --from=build /app/target/clinic-management-system.jar app.jar

# Expose the application port
EXPOSE 8080

# Run the jar file
ENTRYPOINT ["java", "-jar", "app.jar"]
