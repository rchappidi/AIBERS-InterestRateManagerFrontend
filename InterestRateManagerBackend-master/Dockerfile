# Use an official JDK 22 runtime as a parent image
FROM eclipse-temurin:22-jdk-alpine

# Set a working directory
WORKDIR /app

# Copy the JAR file into the container
COPY target/irm.jar irm.jar

# Expose application port (optional, change as needed)
EXPOSE 8080

# Default command to run the app
ENTRYPOINT ["java", "-jar", "/app/irm.jar"]
