# Use a lighter base image with Java 17 to run the application
FROM eclipse-temurin:17-jre-alpine

# Set the working directory inside the Docker container
WORKDIR /app

# Copy the pre-built JAR file into the container
COPY entrypoint/target/*.jar application.jar

# Expose the application's port (default is 8080 for Spring Boot apps)
EXPOSE 8080

# Command to run the application
ENTRYPOINT ["java", "-jar", "application.jar"]