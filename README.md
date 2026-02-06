# User Service

Spring Boot microservice responsible for managing users.

## 🚀 Tech Stack
- Java
- Spring Boot
- MySQL
- Hibernate  JPA
- WebClient
- Maven

## 📌 Features
- User CRUD APIs
- Soft Delete and restore
- Service-to-service communication with Order Service

## 🛠 Setup

1. Clone repository

git clone repo-url

2. Configure database


3. Run application

Create file:

src/main/resources/application.properties

Use application.properties.example as reference.

Server starts on

http://localhost:9091

## 🔗 Related Service

Order Service communicates with this service using WebClient.

