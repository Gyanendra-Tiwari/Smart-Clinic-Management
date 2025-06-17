
# Use a lightweight OpenJDK image
FROM openjdk:17-jdk-slim

# Set application JAR name (adjust this to your actual JAR name)
ARG JAR_FILE=target/clinic-management-system.jar

# Set working directory in the container
WORKDIR /app

# Copy the jar file from the build context to the container
COPY ${JAR_FILE} app.jar

# Expose the port your Spring Boot app runs on (usually 8080)
EXPOSE 8080

# Command to run the application
ENTRYPOINT ["java", "-jar", "app.jar"]
