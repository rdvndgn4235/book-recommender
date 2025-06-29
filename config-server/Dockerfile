# 1. Maven + JDK ile derleme
FROM maven:3.9.4-eclipse-temurin-17 AS build
WORKDIR /app
COPY . .
RUN mvn clean package -DskipTests

# 2. Daha hafif JDK ile runtime
FROM eclipse-temurin:17-jdk-jammy
WORKDIR /app
COPY --from=build /app/target/*.jar app.jar

# 3. Başlangıç komutu
ENTRYPOINT ["java", "-jar", "app.jar"]
