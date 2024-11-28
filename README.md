## Project Structure

```
.
├── src/ # Source code of the Spring Boot application
├── pom.xml # Maven project configuration file
└── Dockerfile # Dockerfile for building the Docker image
└── docker-compose.yml # Docker compose file for building and running the image
```

## Prerequisites

- [Docker](https://www.docker.com/get-started) installed on your machine.

## Dockerfile Overview

This project uses a multi-stage Dockerfile to build and package the Spring Boot application:

1. **Build Stage**:

   - Uses the `openjdk:21-slim` image to build the project.
   - Copies the source code and `pom.xml` into the container.
   - Runs `mvn clean package` to build the JAR file.

2. **Package Stage**:
   - Uses the `openjdk:21-slim` image to create a lightweight container.
   - Copies the JAR file from the build stage into the container.
   - Exposes port `8080`.
   - Sets the user to `10000` for running the application.
   - Defines the entry point to run the Spring Boot application using the JAR file.

## Building and running the Docker Image using docker compose

To build the Docker image, navigate to the directory containing the Dockerfile and run the following command:

```bash
docker compose up -d
```

This command will run the Spring Boot application in a Docker container and map port 8080 of the container to port 8080 on your host machine.
