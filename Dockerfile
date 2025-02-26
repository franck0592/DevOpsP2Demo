# Use a base image with Java runtime (Alpine is a popular lightweight one made for containers)
FROM openjdk:17-jdk-alpine

# Set the working directory inside the container
WORKDIR /app

# Copy the built JAR file into the working directory, rename it to "app.jar" for ease
COPY target/P1DemoBackend-0.0.1-SNAPSHOT.jar app.jar

# Expose the port that the Spring application listens on
EXPOSE 8080

# Run the Spring Boot application
ENTRYPOINT ["java", "-jar", "app.jar"]