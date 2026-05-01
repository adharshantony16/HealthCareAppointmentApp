<div align="center">

# 🏥 Healthcare Appointment Management System

![Spring Boot](https://img.shields.io/badge/Spring_Boot-3.5.4-6DB33F?style=for-the-badge&logo=springboot&logoColor=white)
![Java](https://img.shields.io/badge/Java-21-ED8B00?style=for-the-badge&logo=openjdk&logoColor=white)
![MySQL](https://img.shields.io/badge/MySQL-8.0-4479A1?style=for-the-badge&logo=mysql&logoColor=white)
![JWT](https://img.shields.io/badge/JWT-Security-000000?style=for-the-badge&logo=jsonwebtokens&logoColor=white)
![Maven](https://img.shields.io/badge/Maven-Build-C71A36?style=for-the-badge&logo=apachemaven&logoColor=white)

A **production-ready REST API backend** for managing healthcare appointments, doctors, patients, and admin operations — featuring JWT authentication, automated email notifications, waitlist management, and PDF report generation.

</div>

---

## 🚀 Tech Stack

| Layer | Technology |
|---|---|
| Language | Java 21 |
| Framework | Spring Boot 3.5.4 |
| Security | Spring Security + JWT (io.jsonwebtoken) |
| Database | MySQL 8.0 + Hibernate (Spring Data JPA) |
| Build Tool | Maven |
| Testing | JUnit 5 |
| Notifications | JavaMail API (SMTP) |
| Reporting | PDF Generation + Email Delivery |

---

## ✨ Key Features

### 🔐 Authentication & Authorization
- JWT-based stateless authentication
- Role-based access control — `ADMIN`, `DOCTOR`, `PATIENT`
- Secure registration, login, forgot & reset password flows

### 📅 Appointment Management
- Book, reschedule, and cancel appointments
- View appointments by doctor or patient
- Real-time status updates

### ⏳ Smart Waitlist System
- Auto-managed waitlist per doctor
- Patients added/removed dynamically based on availability

### 👨‍⚕️ Doctor & Patient Management
- Doctor directory with specialization and location filters
- Patient profile retrieval and management
- Admin can onboard doctors directly

### 📨 Email Notifications
- Automated emails triggered on booking, rescheduling, and cancellation
- Monthly PDF reports generated and emailed to admins

### 📊 Admin Dashboard & Analytics
- Total appointments, cancellation rates, complaint stats
- Block/unblock users, manage doctors and complaints
- Broadcast announcements

### 🛡️ Robust Error Handling
- Global exception handler for clean, consistent API error responses

---

## 📁 Project Structure
src/main/java/com/healthcare/appointment/
├── controller/       # REST API endpoints (Auth, Doctor, Patient, Appointment, Admin, Reports...)
├── service/          # Core business logic
├── entity/           # JPA entities — User, Doctor, Patient, Appointment, Complaint, Feedback
├── repository/       # Spring Data JPA repositories
├── dto/              # Request & Response Data Transfer Objects
├── security/         # JWT filter, JwtUtil, SecurityConfig
└── exception/        # GlobalExceptionHandler, ResourceNotFoundException

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

### 2. Create the database
```sql
CREATE DATABASE hospital1;
```

### 3. Configure `application.properties`
```properties
spring.datasource.url=jdbc:mysql://localhost:3306/hospital1?useSSL=false&serverTimezone=UTC
spring.datasource.username=YOUR_DB_USERNAME
spring.datasource.password=YOUR_DB_PASSWORD

jwt.secret=YOUR_JWT_SECRET_KEY
jwt.expirationMs=3600000

spring.mail.host=smtp.gmail.com
spring.mail.port=587
spring.mail.username=YOUR_EMAIL
spring.mail.password=YOUR_APP_PASSWORD
```

### 4. Build and run
```bash
mvn spring-boot:run
```
> API available at `http://localhost:8080`

---

## 📡 API Reference

| Module | Base URL | Description |
|---|---|---|
| 🔐 Auth | `/api/auth` | Register, Login, Password Reset |
| 👨‍⚕️ Doctor | `/api/doctor` | Doctor profiles and search |
| 👤 Patient | `/api/patient` | Patient profile management |
| 📅 Appointment | `/api/appointment` | Book, reschedule, cancel |
| ⏳ Waitlist | `/api/waitlist` | Waitlist per doctor |
| 💬 Feedback | `/api/feedback` | Ratings and comments |
| 📋 Complaints | `/api/complaints` | Submit and track complaints |
| 🔧 Admin | `/api/admin` | User, Doctor, Complaint management |
| 📊 Analytics | `/api/admin/analytics` | Summary stats and insights |
| 📄 Reports | `/api/reports` | Monthly PDF report generation |

---

## 🧪 Running Tests

```bash
mvn test
```

---

## 👤 Author

<div align="center">

**Adharsh Chippalapalli**

[![LinkedIn](https://img.shields.io/badge/LinkedIn-Connect-0077B5?style=for-the-badge&logo=linkedin&logoColor=white)](https://linkedin.com/in/adharsh-ch-018506237)
[![GitHub](https://img.shields.io/badge/GitHub-Follow-181717?style=for-the-badge&logo=github&logoColor=white)](https://github.com/adharshantony16)

</div>
