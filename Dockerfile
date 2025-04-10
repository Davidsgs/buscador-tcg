# Usa Maven oficial para construir
FROM maven:3.9.6-eclipse-temurin-23 AS builder
WORKDIR /app
COPY pom.xml .
COPY src ./src
RUN mvn clean package -DskipTests

# Imagen final liviana
FROM eclipse-temurin:23-jre
WORKDIR /app
COPY --from=builder /app/target/buscador-criollo-mtg-1.0.0-SNAPSHOT.jar app.jar
EXPOSE 8080
ENTRYPOINT ["java", "-jar", "app.jar"]