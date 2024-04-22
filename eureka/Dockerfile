FROM docker.arvancloud.ir/openjdk:21-slim
COPY . /app
WORKDIR /app
RUN chmod +x mvnw
RUN ./mvnw clean package -DskipTests

COPY  /app/target/*.jar  /app/app.jar
WORKDIR /app
EXPOSE 8761
ENTRYPOINT ["java","-jar","app.jar"]