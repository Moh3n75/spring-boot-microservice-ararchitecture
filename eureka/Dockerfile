FROM docker.arvancloud.ir/maven:3.9.6-eclipse-temurin-21
COPY . /app
WORKDIR /app
RUN --mount=type=cache,target=/root/.m2 mvn -f /app/pom.xml clean package

COPY  /app/target/*.jar  /app/app.jar
WORKDIR /app
EXPOSE 8761
ENTRYPOINT ["java","-jar","app.jar"]