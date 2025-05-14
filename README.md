# Book Recommender Microservice Architecture

This is a microservice-based system for book recommendation, built using Java Spring Boot.

## 📦 Microservices

- `user-service`: Manages user information.
- `book-service`: Manages book details.
- `review-service`: Handles reviews and ratings.
- `recommendation-service`: Generates recommendations using Kafka & Redis.
- `api-gateway`: Routes requests to services.
- `config-server`: Centralized configuration (Spring Cloud Config).

## 🛠 Tech Stack

- Java 17
- Spring Boot 3.4.5
- PostgreSQL, MongoDB, Redis
- Kafka
- Springdoc OpenAPI (Swagger)
- Docker (later)
- Maven

---

## 🚀 Run a Service

Example for user-service:

```bash
cd user-service
mvn spring-boot:run
