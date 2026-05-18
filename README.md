## 🏥 Healthcare Appointment Application

A comprehensive appointment scheduling platform built with Spring Boot, featuring user authentication, doctor/patient management, appointments, waitlists, reporting, email notifications, and admin analytics.

## ✨ Features

### 🔐 Authentication & Authorization
- JWT-based login
- Role-based access control: `ADMIN`, `DOCTOR`, `PATIENT`
- User registration and login
- Password reset endpoints

### 👨‍⚕️ Doctor & 👤 Patient Management
- Doctor directory: list, by specialization, by location
- Patient profile retrieval
- Admin can add doctors for users

### 📅 Appointments & ⏳ Waitlist
- Book, reschedule, cancel appointments
- List appointments by doctor or patient
- Manage waitlist per doctor

### 📨 Communication & Feedback
- Email notifications for booking/reschedule/cancel
- Submit and list complaints
- Submit feedback and list feedback for a doctor

### 📊 Reporting & Admin Analytics
- Monthly reports and appointment aggregates
- Export monthly report to PDF and email it
- Admin analytics summary (totals, cancellations)
- Admin user, doctor, and complaint management

## 🛠 Tech Stack

### Backend
- Java 24 (recommend Java 21 LTS for production)
- Spring Boot 3.5.4 (Web, Data JPA, Security, Validation, Actuator, Mail)
- Hibernate (via Spring Data JPA)

### Database
- MySQL 8.0 (primary)
- H2 dependency present but console disabled by default

### Security
- JWT (io.jsonwebtoken 0.11.5)
- Spring Security

### Build Tool
- Maven (wrapper included)

### Testing
- JUnit 5 (spring-boot-starter-test)

## 📋 Prerequisites

- Java 21+ (project currently targets 24)
- Maven 3.9+
- MySQL 8.0+
- Git (for cloning)

## 🚀 Getting Started

### 1. Clone the Repository

```bash
git clone <your-repository-url>
cd HealthCareAppointmentApp-1
```

### 2. Database Setup

1) Create a MySQL database:
```sql
CREATE DATABASE hospital1;
```

2) Update credentials in `src/main/resources/application.properties`:
```properties
spring.datasource.username=your_username
spring.datasource.password=your_password
```

### 3. Configure Email & JWT

Update `src/main/resources/application.properties`:
```properties
# Email (replace with your SMTP settings)
spring.mail.host=smtp.ethereal.email
spring.mail.port=587
spring.mail.username=your_email
spring.mail.password=your_app_password
spring.mail.properties.mail.smtp.auth=true
spring.mail.properties.mail.smtp.starttls.enable=true

# JWT
jwt.secret=your_jwt_secret_key
jwt.expirationMs=3600000
```

### 4. Build and Run

```bash
# Clean and compile
mvn clean compile

# Run the application (or use ./mvnw spring-boot:run)
mvn spring-boot:run
```

The application will start on `http://localhost:8080`.

### 5. Access the Application

- API Base URL: `http://localhost:8080/api`
- Health check: `http://localhost:8080/actuator/health`

## 📚 API Documentation

### Authentication Endpoints (`/api/auth`)
- POST `/register` — Register a new user (role: PATIENT/DOCTOR/ADMIN)
- POST `/login` — User login, returns JWT
- POST `/forgot-password` — Request password reset (demo stub)
- POST `/reset-password` — Reset password with token
- POST `/logout` — Stateless logout confirmation

### Doctor Endpoints (`/api/doctor`)
- GET `/all` — Get all doctors
- GET `/{id}` — Get doctor by ID
- GET `/specialization/{spec}` — Filter by specialization
- GET `/location/{loc}` — Filter by location
- POST `/add/{userId}` — Add doctor for a user [ADMIN]

### Patient Endpoints (`/api/patient`)
- GET `/{id}` — Get patient by ID

### Appointment Endpoints (`/api/appointment`)
- POST `/book` — Book appointment
- PUT `/{id}/reschedule` — Reschedule appointment
- DELETE `/{id}/cancel` — Cancel appointment
- GET `/doctor/{doctorId}` — Appointments by doctor
- GET `/patient/{patientId}` — Appointments by patient
- PUT `/{id}/status?status=...` — Update status

### Waitlist Endpoints (`/api/waitlist`)
- POST `/` — Add to waitlist
- GET `/doctor/{doctorId}` — Waitlist for doctor

### Complaint Endpoints (`/api/complaints`)
- POST `/` — Submit complaint
- GET `/` — List all complaints

### Feedback Endpoints (`/api/feedback`)
- POST `/` — Submit feedback
- GET `/doctor/{doctorId}` — Feedback for doctor

### Admin Endpoints (`/api/admin`) [ADMIN]
- GET `/users` — List users
- PUT `/users/{id}/block` — Block user
- PUT `/users/{id}/unblock` — Unblock user
- GET `/doctors` — List doctors
- GET `/complaints` — List complaints
- PUT `/complaints/{id}/status?status=...` — Update complaint status
- POST `/announcements` — Broadcast announcement (demo)

### Admin Analytics (`/api/admin/analytics`) [ADMIN]
- GET `/summary` — Totals, cancellations, etc.

### Reporting Endpoints (`/api/reports`)
- GET `/monthly` — Monthly report (DTO)
- GET `/appointments` — Appointment aggregates
- POST `/monthly/pdf?email=...` — Generate PDF and email

## 🗄 Database Schema

Main entities:
- `User` — User accounts and roles
- `Doctor` — Doctor profiles
- `Patient` — Patient profiles
- `Appointment` — Appointments and notes
- `Complaint` — Complaint tickets
- `Feedback` — Ratings and comments
- `WaitlistEntry` — Waitlist records

## 🔧 Configuration

Key properties in `application.properties` (can be overridden via environment variables):
```properties
# Database
spring.datasource.url=jdbc:mysql://localhost:3306/hospital1?useSSL=false&serverTimezone=UTC&allowPublicKeyRetrieval=true
spring.datasource.username=your_username
spring.datasource.password=your_password
spring.jpa.hibernate.ddl-auto=update
spring.jpa.show-sql=true

# JWT
jwt.secret=your_jwt_secret_key
jwt.expirationMs=3600000

# Server
server.port=8080

# Mail
spring.mail.host=smtp.ethereal.email
spring.mail.port=587
spring.mail.username=your_email
spring.mail.password=your_app_password
spring.mail.properties.mail.smtp.auth=true
spring.mail.properties.mail.smtp.starttls.enable=true
```

## 🧪 Testing

```bash
# Run all tests
mvn test

# Run a specific test class
mvn test -Dtest=HealthCareAppointmentApp1ApplicationTests
```

## 📁 Project Structure

```
HealthCareAppointmentApp-1/
├── pom.xml
├── src/
│   ├── main/
│   │   ├── java/com/healthcare/appointment/
│   │   │   ├── controller/      # REST controllers (auth, appointment, doctor, patient, admin, analytics, reports, waitlist, complaints, feedback)
│   │   │   ├── dto/             # Data Transfer Objects
│   │   │   ├── entity/          # JPA entities
│   │   │   ├── exception/       # Global exception handling
│   │   │   ├── repository/      # Spring Data JPA repositories
│   │   │   ├── security/        # JWT filter/util and SecurityConfig
│   │   │   └── service/         # Business logic services
│   │   └── resources/
│   │       ├── application.properties
│   │       └── templates/static (if any)
│   └── test/
│       └── java/com/healthcare/appointment/
└── test-endpoints.ps1           # PowerShell test helper
```

## 🚀 Deployment

### Production Deployment

1) Build the JAR file:
```bash
mvn clean package -DskipTests
```

2) Run the application:
```bash
java -jar target/doctor-management-0.0.1-SNAPSHOT.jar
```

Optional: configure environment variables for secrets (DB, JWT, Mail) instead of committing them to properties.

Happy scheduling! 🩺📅


