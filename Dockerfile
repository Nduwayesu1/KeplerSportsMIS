# Use an official OpenJDK 23 image as a base
FROM openjdk:23-jdk

# Set the working directory inside the container
WORKDIR /app

# Copy the Spring Boot JAR file into the container
COPY target/KeplerSportsMIS-0.0.1-SNAPSHOT.jar KeplerSportsMIS.jar

# Expose the application port
EXPOSE 8005

# Run the application
ENTRYPOINT ["java", "-jar", "KeplerSportsMIS.jar"]
