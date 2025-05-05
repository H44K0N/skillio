FROM eclipse-temurin:21-jdk AS build

WORKDIR /app
COPY . .

# Bygg Spring Boot-prosjektet uten å kjøre tester
RUN ./mvnw clean package -DskipTests

# Bruk en minimal JRE for å kjøre appen
FROM eclipse-temurin:21-jre
WORKDIR /app

# Kopier den bygde JAR-filen
COPY --from=build /app/target/*.jar app.jar

# Start applikasjonen
ENTRYPOINT ["java", "-jar", "app.jar"]
