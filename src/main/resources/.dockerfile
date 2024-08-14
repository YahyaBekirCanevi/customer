# Use an official Gradle image to build the project
FROM gradle:8.5.0-jdk17 AS build
WORKDIR /app
COPY . .

# Build the Micronaut application
RUN ./gradlew build

# Use an official OpenJDK image to run the application
FROM openjdk:21
WORKDIR /app

# Copy the built application JAR file
COPY --from=build /app/build/libs/*.jar /app/app.jar

# Expose the port the app will run on
EXPOSE 8080

# Run the application
ENTRYPOINT ["java", "-jar", "/app/app.jar"]


# FROM openjdk:17
# COPY target/time-*.jar time.jar
# EXPOSE 8080
# CMD ["java", "-Dmicronaut.environments=docker", "-Dmicronaut.config.files=/some/external/location/application-docker.yml", "-Dcom.sun.management.jmxremote", "-Xmx128m", "-jar"]
