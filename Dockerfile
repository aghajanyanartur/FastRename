FROM openjdk:18-jdk-slim-buster
WORKDIR /app
COPY build.gradle settings.gradle gradlew /app/
COPY gradle /app/gradle
RUN ./gradlew dependencies
COPY src /app/src
RUN ./gradlew build
ENTRYPOINT ["java", "-jar", "build/libs/FastRename-1.0.jar"]