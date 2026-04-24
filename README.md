# 🏦 Bank Simulation

[![Spring Boot](https://img.shields.io/badge/Spring%20Boot-3.3-brightgreen)](https://spring.io/projects/spring-boot)
[![Docker](https://img.shields.io/badge/Docker-Compose-blue)](https://www.docker.com/)
[![PostgreSQL](https://img.shields.io/badge/PostgreSQL-16-blue)](https://www.postgresql.org/)

REST API simulating a stock trading bank, built with **Spring Boot 3** and deployed with **Docker Compose** behind an **Nginx** load balancer.

 REST API simulating a stock trading bank, built with **Spring Boot 3** and deployed with **Docker Compose** behind an **Nginx** load balancer.

---

## 📐 Architecture


```text
      Client (Browser/Postman)
                │
                ▼
      ┌───────────────────┐
      │       Nginx       │  ← Reverse Proxy / Load Balancer (Port: XXXX)
      └─────────┬─────────┘
                │
      ┌─────────┴─────────┐
      ▼                   ▼
┌───────────┐       ┌───────────┐
│   App 1   │       │   App 2   │  ← Spring Boot Replicas (Port: 8080 internal)
└─────┬─────┘       └─────┬─────┘
      └─────────┬─────────┘
                ▼
      ┌───────────────────┐
      │    PostgreSQL     │  ← Shared Database
      └───────────────────┘
```

The application runs as **two replicas** behind Nginx, which handles load balancing and automatic failover.

The API will be available at `http://localhost:XXXX`.

---

### 🛠️ Running the Application

| OS | Command | Example |
| :--- | :--- | :--- |
| **Linux / macOS** | `./start.sh <port>` | `./start.sh 8080` |
| **Windows** | `start.bat <port>` | `start.bat 8080` |


The start.sh script automates the environment setup by creating a .env file from a template (if missing), validates the input port, and launches the infrastructure (Nginx, App Replicas, Database) using docker compose with the build flag to ensure the latest version is deployed.

To simplify the setup process, the startup scripts (start.sh / start.bat) automatically handle environment variables:
Zero-Config Start: If no .env file is found, the script automatically creates one using .env.example. This allows the application to run out-of-the-box with default database credentials.
Customization: If you need to use different credentials or settings, simply copy .env.example to .env and modify the values before running the startup script.

## 📡 API Reference

### Bank Stocks

| Method | Endpoint  | Description              |
|--------|-----------|--------------------------|
| `POST` | `/stocks` | Set available bank stocks |
| `GET`  | `/stocks` | Get all bank stocks       |

**Set stocks – request body:**
```json
{
  "stocks": [
    { "name": "AAPL", "quantity": 100 },
    { "name": "TSLA", "quantity": 50 }
  ]
}
```

---

### Wallet & Trading

| Method | Endpoint                                  | Description                      |
|--------|-------------------------------------------|----------------------------------|
| `GET`  | `/wallets/{wallet_id}`                    | Get wallet with all held stocks  |
| `GET`  | `/wallets/{wallet_id}/stocks/{stock_name}`| Get quantity of a specific stock |
| `POST` | `/wallets/{wallet_id}/stocks/{stock_name}`| Buy or sell a stock              |

**Trade – request body:**
```json
{ "type": "buy" }
```
```json
{ "type": "sell" }
```

Wallets are created automatically on the first trade.

---

### Audit Log

| Method | Endpoint | Description                      |
|--------|----------|----------------------------------|
| `GET`  | `/log`   | Get all buy/sell trade history   |

---

### Chaos Engineering

| Method  | Endpoint  | Description                          |
|---------|-----------|--------------------------------------|
| `POST`  | `/chaos`  | Shut down the current app instance   |

Use this to verify Nginx failover to the second replica.

---
## 🧪 Tests

Integration tests run against a real PostgreSQL instance via **Testcontainers** — no mocks, no fakes.

```bash
./mvnw test
```

Test coverage includes:
- Setting and retrieving bank stocks
- Buying stocks and verifying wallet state
- Bank quantity decrement after purchase
- Error handling for missing or depleted stocks

---

## 🛠️ Tech Stack

| Layer        | Technology                        |
|--------------|-----------------------------------|
| Framework    | Spring Boot 3.3                   |
| Database     | PostgreSQL 16                     |
| Migrations   | Flyway                            |
| ORM          | Spring Data JPA / Hibernate       |
| Proxy        | Nginx (Alpine)                    |
| Containers   | Docker Compose                    |
| Testing      | JUnit 5, Testcontainers           |
| Build        | Maven (via wrapper)               |
| Java         | 17 (Eclipse Temurin)              |

---

## 📁 Project Structure

```
src/
├── main/java/org/example/banksimulation/
│   ├── controllers/     # REST endpoints
│   ├── services/        # Business logic
│   ├── entieties/       # JPA entities
│   ├── reposiories/     # Spring Data repositories
│   └── dto/             # Request / response records
└── test/                # Integration tests (Testcontainers)
```
