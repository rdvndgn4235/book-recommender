# 1. Temel image: Maven yüklü bir JDK (Java 17)
FROM maven:3.9.4-eclipse-temurin-17 AS build

# 2. Uygulama kodlarını konteyner içine kopyala
WORKDIR /app
COPY . .

# 3. Maven ile uygulamayı derle (testleri opsiyonel olarak atlayabilirsin)
RUN mvn clean install -DskipTests

# 4. Daha küçük bir JDK image ile çalışma ortamı
FROM eclipse-temurin:17-jdk-jammy

# 5. Uygulama dosyasını çalışma dizinine kopyala
WORKDIR /app
COPY --from=build /app/target/*.jar app.jar

# 6. Docker ortamında "docker" profilini kullanarak uygulamayı başlat
ENTRYPOINT ["java", "-jar", "app.jar", "--spring.profiles.active=docker"]
