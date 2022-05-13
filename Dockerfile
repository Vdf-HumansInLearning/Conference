# define base docker image
FROM openjdk:17-jdk-alpine
ADD target/conference-0.0.1-SNAPSHOT.jar conference-app.jar
EXPOSE 8081
#ARG JAR_FILE=target/*.jar
#COPY ${JAR_FILE} conference-app.jar
#ENTRYPOINT ["java","-jar","/app.jar"]
ENTRYPOINT ["java", "-jar", "conference-app.jar"]