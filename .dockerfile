# Use an official Gradle image to build the project
FROM gradle:8.5.0-jdk21 AS build
WORKDIR /app
COPY . .

# Ensure gradlew is executable
RUN chmod +x ./gradlew

# Build the Micronaut application using the Gradle wrapper
RUN ./gradlew shadowJar

# Use an official OpenJDK image to run the application
FROM openjdk:21

WORKDIR /app

# Copy the built application JAR file
COPY --from=build /app/build/libs/*.jar /app/app.jar

# Expose the port the app will run on
EXPOSE 8080

# Run the application
ENTRYPOINT ["java", "-jar", "/app/app.jar"]
