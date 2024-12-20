# the base image
FROM amazoncorretto:17

# the JAR file path

# Copy the JAR file from the build context into the Docker image
COPY franchise-technical-test-0.0.1-SNAPSHOT.jar application.jar

CMD apt-get update -y

# Set the default command to run the Java application
ENTRYPOINT ["java", "-Xmx2048M", "-jar", "/application.jar"]