FROM openjdk:8-jdk-alpine
ARG JAR_FILE=target/SpringMock-0.0.1-SNAPSHOT.jar
COPY ${JAR_FILE} app.jar
ENTRYPOINT ["java","-jar","/SpringMock-0.0.1-SNAPSHOT.jar"]