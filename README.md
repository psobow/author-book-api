# 📚 Author-Book API

Spring Boot 3 REST API for managing **Authors** and **Books**.  
Clean layered architecture (controllers → services → repositories → domain) with DTO mapping, Dockerized PostgreSQL, and
integration tests.

## 🚀 Features

- **Authors** — create, read, update (PUT/PATCH), delete, get by ID
- **Books** — create/update by ISBN, list (paged), get by ISBN, partial update, delete
- **Relations** — `Book -> Author` (`@ManyToOne`)
- **DTO mapping** — centralized with ModelMapper
- **HTTP semantics** — 201/200/204/404/409 where appropriate

## 🛠️ Tech Stack

- **Java 21**
- **Spring Boot 3**
	- Spring Web — REST controllers
	- Spring Data JPA — PostgreSQL persistence
	- Spring Validation — DTO constraints
	- Spring Boot Test — unit & integration testing
- **ModelMapper** — DTO ↔ Entity mapping
- **PostgreSQL** — database (Docker for dev)
- **Docker & Docker Compose**
- **JUnit 5 + MockMvc**

## 📂 Project Structure

```
src/main/java/com/sobow/library
├─ config/              # Mapper configuration (ModelMapper bean)
├─ controllers/         # REST controllers (AuthorController, BookController)
├─ domain/              # Entities (Author, Book) + DTOs
├─ mappers/             # Mapper interfaces/wrappers
├─ repositories/        # Spring Data JPA repositories
├─ services/            # Service interfaces + implementations
└─ DemoApplication.java # Spring Boot entry point
```

```
src/test/java/com/sobow/library
├─ controllers/         # MockMvc integration tests
├─ repositories/        # Repository integration tests
└─ TestDataUtil.java    # Test fixtures
```

## ⚙️ Local Setup

### 1) Start PostgreSQL (Docker Compose)

```bash
docker compose up -d
```

### 2) Configure datasource (example)

Create or edit `src/main/resources/application.properties`:

```properties
spring.datasource.url=jdbc:postgresql://localhost:5432/postgres
spring.datasource.username=postgres
spring.datasource.password=password
spring.jpa.hibernate.ddl-auto=update
spring.jpa.properties.hibernate.jdbc.lob.non_contextual_creation=true
```

### 3) Build & run

```bash
./mvnw clean package
./mvnw spring-boot:run
```

## 🔌 Endpoints

### Authors

- `POST /authors` — create author
- `GET /authors` — list authors
- `GET /authors/{id}` — get by ID
- `PUT /authors/{id}` — full update
- `PATCH /authors/{id}` — partial update
- `DELETE /authors/{id}` — delete

### Books

- `PUT /books/{isbn}` — create or replace by ISBN
- `GET /books` — list books (paged)
- `GET /books/{isbn}` — get by ISBN
- `PATCH /books/{isbn}` — partial update
- `DELETE /books/{isbn}` — delete

## 📖 Example Requests

Create Author:

```http
POST /authors
Content-Type: application/json

{
  "name": "George Orwell",
  "age": 46
}
```

Create/Update Book by ISBN:

```http
PUT /books/9780451524935
Content-Type: application/json

{
  "title": "1984",
  "author": { "id": 1 }
}
```

List Books (paged):

```http
GET /books?page=0&size=10
```

## 🧪 Testing

- Unit & **integration tests** with **JUnit 5** and **Spring Boot Test**
- **MockMvc** for controller/API testing
- Test data helpers (`TestDataUtil`)
- Optional: add **Testcontainers** (PostgreSQL) for production-like integration tests

Run tests:

```bash
./mvnw test
```




