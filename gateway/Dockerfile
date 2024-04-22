FROM docker.arvancloud.ir/maven:3.8.8-eclipse-temurin-21-alpine
COPY ./gateway /app
WORKDIR /app
RUN --mount=type=cache,target=/root/.m2 mvn -f /app/pom.xml clean package

COPY  /app/target/*.jar  /app/app.jar
WORKDIR /app
EXPOSE 8080
ENTRYPOINT ["java","-jar","app.jar"]