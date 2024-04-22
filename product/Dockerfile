FROM openjdk:21-slim AS builder
COPY . /app
WORKDIR /app
RUN ./mvnw clean package -DskipTests

FROM openjdk:21-slim
COPY --from=builder /app/target/*.jar  /app/app.jar
WORKDIR /app
EXPOSE 8080
ENTRYPOINT ["java","-jar","app.jar"]