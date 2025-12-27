
# 🛒 SmartShop – AI-Powered E-Commerce Backend

SmartShop is a **Spring Boot–based e-commerce backend** enhanced with **Gemini AI** for intelligent search routing.
It supports **products, users, carts, orders, payments**, and **natural-language search** like:

---

## 🚀 Features

* ✅ User registration & role management (Customer / Admin)
* ✅ Product management with categories & pricing
* ✅ Cart operations (add / remove / buy)
* ✅ Order & payment tracking
* ✅ Product view & search logging
* 🤖 **AI-powered search routing using Gemini**
* ☁️ Cloud-ready deployment (Render + Aiven / Render PostgreSQL)

---

## 🧠 AI Search (Key Highlight)

SmartShop uses **Gemini AI as an intent router**, not a database query engine.

### Example

User input:

```
shoes under 300
```

Gemini output:

```json
{
  "function": "searchByKeywordAndMaxPrice",
  "keyword": "shoes",
  "maxPrice": 300
}
```

Backend then calls:

```java
searchByKeywordAndMaxPrice("shoes", 300)
```

✅ AI never touches the database
✅ Backend logic remains safe & deterministic

---

## 🛠️ Tech Stack

* **Java 17**
* **Spring Boot**
* **Spring Data JPA**
* **Hibernate**
* **PostgreSQL / MySQL**
* **Gemini AI (google-genai SDK)**
* **Maven**
* **Render (Deployment)**

---

## 🗂️ Project Structure

```
src/main/java
 ├── controller
 ├── service
 ├── repository
 ├── model
 └── SmartShopApplication.java
```

---

## 🗄️ Database Entities

* User
* Product
* Category
* CartItem
* Orders
* Payment
* ProductView
* SearchLog

---

## ⚙️ Configuration

### application.properties (example)

```properties
spring.datasource.url=jdbc:postgresql://<HOST>:5432/<DB>
spring.datasource.username=<USERNAME>
spring.datasource.password=<PASSWORD>

spring.jpa.hibernate.ddl-auto=update
spring.jpa.show-sql=true
```

---

## ▶️ Run Locally

```bash
./mvnw clean package -DskipTests
java -jar target/*.jar
```

Server runs on:

```
http://localhost:8080
```

---

## ☁️ Deployment on Render

### Build Command

```bash
./mvnw clean package -DskipTests
```

### Start Command

```bash
java -jar target/*.jar
```

> Make sure `mvnw` has execute permission:

```bash
chmod +x mvnw
```

---

## 🔐 Environment Variables (Render)

Set these in Render dashboard:

```
SPRING_DATASOURCE_URL
SPRING_DATASOURCE_USERNAME
SPRING_DATASOURCE_PASSWORD
```

---

## 🧪 Sample API

```http
GET /products/search?q=shoes under 300
```

Returns filtered products based on AI-interpreted intent.

---

