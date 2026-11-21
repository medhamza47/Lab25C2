FROM maven:3.9.11-eclipse-temurin-17 AS builder
WORKDIR /app
COPY pom.xml .
COPY src ./src
RUN mvn -B -DskipTests clean package

FROM eclipse-temurin:17-jre
WORKDIR /app
COPY --from=builder /app/target/gateway-0.0.1-SNAPSHOT.jar gateway-service.jar
EXPOSE 8888
ENTRYPOINT ["java", "-jar", "/app/gateway-service.jar"]
