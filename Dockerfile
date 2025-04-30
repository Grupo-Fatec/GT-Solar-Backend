FROM openjdk:21-jdk-slim

RUN apt-get update && apt-get install -y maven

WORKDIR /app

COPY . .

RUN mvn clean compile

RUN mvn install

RUN mvn package

ARG JAR_FILE=target/*.jar
COPY ${JAR_FILE} /app.jar

EXPOSE 8082

ENTRYPOINT ["java", "-jar", "/app.jar"]
