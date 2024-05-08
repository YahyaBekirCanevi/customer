FROM openjdk:17
COPY target/time-*.jar time.jar
EXPOSE 8080
CMD ["java", "-Dmicronaut.environments=docker", "-Dmicronaut.config.files=/some/external/location/application-docker.yml", "-Dcom.sun.management.jmxremote", "-Xmx128m", "-jar"]