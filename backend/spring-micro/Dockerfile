# Use a basic Java image
FROM openjdk:17-jdk-slim AS build

# Install Maven manually
RUN apt-get update && \
    apt-get install -y wget unzip && \
    wget https://repo.maven.apache.org/maven2/org/apache/maven/apache-maven/3.9.7/apache-maven-3.9.7-bin.zip && \
    unzip apache-maven-3.9.7-bin.zip -d /opt && \
    ln -s /opt/apache-maven-3.9.7 /opt/maven && \
    rm apache-maven-3.9.7-bin.zip

# Set up Maven environment variables
ENV MAVEN_HOME=/opt/maven
ENV PATH=${MAVEN_HOME}/bin:${PATH}

# Set the working directory0
WORKDIR /app

# Copy the source code and pom.xml
COPY . /app

# Build the application
RUN mvn package -DskipTests

# List the files in the target directory for debugging
RUN ls -la /app/target

# Use the OpenJDK runtime image to run the app
FROM openjdk:17-jdk-slim

# Set the working directory
WORKDIR /app

# Copy the jar file from the build stage
COPY --from=build /app/target/spring-micro-0.0.1-SNAPSHOT.jar /app/app.jar

# Expose port 8080
EXPOSE 8080

# Run the Spring Boot application
CMD ["java", "-jar", "/app/app.jar"]