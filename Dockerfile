# Construcción del JAR
FROM eclipse-temurin:23-jdk AS builder
WORKDIR /app
COPY . .
RUN chmod +x mvnw
RUN ./mvnw clean package -DskipTests

# Imagen final
FROM eclipse-temurin:23-jre
WORKDIR /app
COPY --from=builder /app/target/buscador-criollo-mtg-1.0.0-SNAPSHOT.jar app.jar
EXPOSE 8080
ENTRYPOINT ["java", "-jar", "app.jar"]