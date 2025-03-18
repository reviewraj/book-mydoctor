# BookMyDoctor 🏥

BookMyDoctor is a web-based appointment booking system that allows users to schedule appointments with doctors efficiently. Built using **Java, Spring Boot, Hibernate, and MySQL**, this application provides a seamless experience for both patients and doctors.

## 🚀 Features
- **User Registration & Authentication** 🛡️
- **Doctor Management** 👨‍⚕️
- **Appointment Booking & Cancellation** 📅
- **Email Notifications** 📧
- **Role-based Access Control** 🔑
- **RESTful API** 🔗
- **Admin Dashboard** 📊

## 🛠️ Tech Stack
- **Backend:** Java 23, Spring Boot, Hibernate JPA
- **Database:** MySQL
- **Frontend:** (To be added)
- **Authentication:** Spring Security, JWT
- **Logging & Monitoring:** Logback, Prometheus, Grafana

## 📂 Project Structure
```
bookmydoctor/
│── src/main/java/com/bookmydoctor
│   ├── controller/    # REST Controllers
│   ├── entity/        # JPA Entities
│   ├── repository/    # Spring Data JPA Repositories
│   ├── service/       # Business Logic Services
│   ├── dto/           # Data Transfer Objects
│   ├── exception/     # Custom Exception Handling
│── src/main/resources
│   ├── application.yml # Configuration
│── pom.xml            # Maven Dependencies
```

## 🏗️ Setup & Installation

### Prerequisites
- Java 23
- MySQL
- Maven

### Steps to Run Locally
1. Clone the repository:
   ```sh
   git clone https://github.com/reviewraj/bookmydoctor.git
   cd bookmydoctor
   ```
2. Configure the database in `application.yml`:
   ```yml
   spring:
     datasource:
       url: jdbc:mysql://localhost:3306/bookmydoctor
       username: root
       password: yourpassword
   ```
3. Build and run the application:
   ```sh
   mvn clean install
   mvn spring-boot:run
   ```
4. Access the API at `http://localhost:8080/bookmydoctor/api`.

## 📌 API Endpoints

### User Management
- `POST /api/user/register` - Register a new user
- `POST /api/user/login` - Authenticate user

### Doctor Management
- `GET /api/doctors` - List all doctors
- `GET /api/doctors/{id}` - Get doctor details

### Appointment Booking
- `POST /api/appointment/book` - Book an appointment
- `DELETE /api/appointment/cancel/{id}` - Cancel an appointment

## 📢 Contributing
We welcome contributions! Please fork the repo and submit a pull request.

## 📜 License
This project is licensed under the MIT License.

## 🤝 Contact
For queries or collaborations, reach out to **[Nagaraju Golla](https://www.linkedin.com/in/nagaraju-golla-334793188/)** ✉️ nagaraju7876482@gmail.com
