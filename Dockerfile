FROM adoptopenjdk/openjdk18:latest
WORKDIR /app
COPY build/libs/*.jar app.jar
CMD ["java", "-jar", "app.jar"]