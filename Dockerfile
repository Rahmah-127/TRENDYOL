#
# Build stage
#
FROM openjdk:21-slim AS build

# Install Maven
RUN apt-get update && apt-get install -y maven

# Copy the application source code and pom.xml
COPY src /home/app/src
COPY pom.xml /home/app

# Build the application
WORKDIR /home/app
RUN mvn clean package

#
# Package stage
#
FROM openjdk:21-slim

# Copy the built jar from the build stage
COPY --from=build /home/app/target/rahmah-api-0.0.1-SNAPSHOT.jar /usr/local/lib/rahmah-api-0.0.1-SNAPSHOT.jar

# Expose the port and set the user
EXPOSE 8080
USER 10000

# Start the application
ENTRYPOINT ["java", "-jar", "/usr/local/lib/rahmah-api-0.0.1-SNAPSHOT.jar"]
