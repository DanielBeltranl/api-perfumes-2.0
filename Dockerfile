
FROM eclipse-temurin:22-jdk-jammy AS build
WORKDIR /app
COPY . .
RUN chmod +x mvnw
RUN ./mvnw clean package -DskipTests


FROM eclipse-temurin:22-jre-jammy
WORKDIR /app
COPY --from=build /app/target/perfulandia-0.0.1-SNAPSHOT.jar app.jar
EXPOSE 8080
ENTRYPOINT ["java", "-jar", "app.jar"]