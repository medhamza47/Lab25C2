FROM maven:3.9.11-eclipse-temurin-17 AS builder
WORKDIR /app
COPY pom.xml .
COPY src ./src
RUN mvn -B -DskipTests clean package

FROM eclipse-temurin:17-jre
WORKDIR /app
COPY --from=builder /app/target/client-0.0.1-SNAPSHOT.jar client-service.jar
EXPOSE 8088
ENTRYPOINT ["java", "-jar", "/app/client-service.jar"]
