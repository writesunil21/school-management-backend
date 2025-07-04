# Use official JDK 17 base image
//FROM eclipse-temurin:17-jdk
FROM gradle:8.4.0-jdk17 as builder


# Set working directory inside the container
WORKDIR /app

# Copy Gradle wrapper scripts and set execute permission
COPY gradlew .
COPY gradle gradle
RUN chmod +x gradlew

# Copy the rest of the project files
COPY . .

# Build the Spring Boot project
RUN ./gradlew build --no-daemon --stacktrace --info

# Start the application using the generated jar
CMD ["java", "-jar", "build/libs/demo-0.0.1-SNAPSHOT.jar"]
