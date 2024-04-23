FROM docker.arvancloud.ir/maven:3.8.8-eclipse-temurin-21-alpine as builder
COPY . /app
WORKDIR /app
RUN --mount=type=cache,target=/root/.m2 mvn -f /app/pom.xml clean package

FROM docker.arvancloud.ir/openjdk:21-slim
COPY --from=builder /app/target/*.jar  /app/app.jar
WORKDIR /app
EXPOSE 8080
ENTRYPOINT ["java","-jar","app.jar"]
