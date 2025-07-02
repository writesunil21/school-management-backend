# Use official OpenJDK image with JDK 17
FROM eclipse-temurin:17-jdk

# Set the working directory in the container
WORKDIR /app

# Copy Gradle wrapper and project files
COPY . .

# Give gradlew permission to execute
RUN chmod +x ./gradlew

# Build the application
RUN ./gradlew build --no-daemon

# Use the fat jar generated in build/libs
CMD ["java", "-jar", "build/libs/demo-0.0.1-SNAPSHOT.jar"]
