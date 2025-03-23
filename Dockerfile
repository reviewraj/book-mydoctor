# Use official Java 17 base image
FROM eclipse-temurin:17-jdk-jammy

# Set working directory
WORKDIR /app

# Copy JAR file to the container
COPY target/bookmydoctor-0.0.1-SNAPSHOT.jar app.jar

# Expose the application port
EXPOSE 8080

# Set environment variables to ensure Spring Boot binds to the correct host
ENV SPRING_PROFILES_ACTIVE=prod \
    SERVER_PORT=8080 \
    SERVER_ADDRESS=0.0.0.0

# Run the application
ENTRYPOINT ["java", "-jar", "/app/app.jar"]
