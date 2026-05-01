markdown# 🏥 Healthcare Appointment Management System

A production-ready **REST API backend** built with **Spring Boot** for managing healthcare appointments, doctors, patients, and admin operations — with JWT security, email notifications, and PDF report generation.

---

## 🚀 Tech Stack

| Layer | Technology |
|---|---|
| Language | Java 21 |
| Framework | Spring Boot 3.5.4 |
| Security | Spring Security + JWT |
| Database | MySQL 8.0 + Spring Data JPA |
| Build Tool | Maven |
| Testing | JUnit 5 |
| Notifications | JavaMail (SMTP) |

---

## ✨ Features

- 🔐 **JWT Authentication** — Secure login, registration, and role-based access control (`ADMIN`, `DOCTOR`, `PATIENT`)
- 📅 **Appointment Management** — Book, reschedule, cancel, and track appointments
- ⏳ **Waitlist System** — Auto-manage patient waitlists per doctor
- 👨‍⚕️ **Doctor & Patient Profiles** — Full CRUD with specialization and location filtering
- 📨 **Email Notifications** — Automated emails on booking, rescheduling, and cancellation
- 📊 **Admin Analytics** — Summary dashboards with totals, cancellations, and complaint stats
- 📄 **PDF Report Generation** — Monthly reports generated and emailed to admins
- 🛡️ **Global Exception Handling** — Clean, consistent error responses across all endpoints

---

## 📁 Project Structure
src/main/java/com/healthcare/appointment/
├── controller/       # REST API endpoints
├── service/          # Business logic
├── entity/           # JPA entities (User, Doctor, Patient, Appointment...)
├── repository/       # Spring Data JPA repositories
├── dto/              # Request & Response DTOs
├── security/         # JWT filter, util, and SecurityConfig
└── exception/        # Global exception handler

---

## ⚙️ Getting Started

### Prerequisites
- Java 21+
- MySQL 8.0+
- Maven 3.9+

### 1. Clone the repository
```bash
git clone https://github.com/adharshantony16/HealthCareAppointmentApp.git
cd HealthCareAppointmentApp/HealthCareAppointmentApp-1
```

### 2. Setup Database
```sql
CREATE DATABASE hospital1;
```

### 3. Configure application.properties
```properties
spring.datasource.url=jdbc:mysql://localhost:3306/hospital1?useSSL=false&serverTimezone=UTC
spring.datasource.username=YOUR_USERNAME
spring.datasource.password=YOUR_PASSWORD

jwt.secret=YOUR_JWT_SECRET_KEY
jwt.expirationMs=3600000

spring.mail.host=smtp.gmail.com
spring.mail.port=587
spring.mail.username=YOUR_EMAIL
spring.mail.password=YOUR_APP_PASSWORD
```

### 4. Run the application
```bash
mvn spring-boot:run
```

API runs at: `http://localhost:8080`

---

## 📡 API Overview

| Module | Base URL | Description |
|---|---|---|
| Auth | `/api/auth` | Register, Login, Password Reset |
| Doctor | `/api/doctor` | Doctor profiles and search |
| Patient | `/api/patient` | Patient profile management |
| Appointment | `/api/appointment` | Book, reschedule, cancel |
| Waitlist | `/api/waitlist` | Waitlist per doctor |
| Feedback | `/api/feedback` | Patient feedback and ratings |
| Complaints | `/api/complaints` | Submit and manage complaints |
| Admin | `/api/admin` | User/Doctor/Complaint management |
| Analytics | `/api/admin/analytics` | Summary stats for admin |
| Reports | `/api/reports` | Monthly PDF reports |

---

## 🧪 Testing

```bash
mvn test
```

---

## 👤 Author

**Adharsh Chippalapalli**
[![LinkedIn](https://img.shields.io/badge/LinkedIn-Connect-blue?logo=linkedin)](https://linkedin.com/in/adharsh-ch-018506237)
[![GitHub](https://img.shields.io/badge/GitHub-adharshantony16-black?logo=github)](https://github.com/adharshantony16)
