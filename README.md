# 📌 Customer Management System

![Java](https://img.shields.io/badge/Java-23-blue) ![Spring Boot](https://img.shields.io/badge/Spring%20Boot-3.2.1-green) ![Hibernate JPA](https://img.shields.io/badge/Hibernate%20JPA-6.6.7-yellow)

## 🚀 Overview
The **Customer Management System** is a full-stack web application that allows users to manage customers efficiently. Users can:

✅ Register and log in to the system  
✅ Add, edit, and delete customer records  
✅ Upload and retrieve documents securely  
✅ Ensure content safety with filtering for inappropriate content  
✅ Search and view documentation in a structured format  

This project is built using **Java (JSP, Hibernate JPA, Spring Boot)** for the backend and follows MVC architecture.

---

## 🛠️ Tech Stack
### **Backend:**
- Java 23
- Spring Boot
- Hibernate JPA
- MySQL Database

### **Frontend:**
- JSP (Java Server Pages)
- HTML & CSS
- Bootstrap

### **Other Tools:**
- Lombok
- ModelMapper
- File Upload Handling

---

## 📌 Features
### ✅ User Authentication & Authorization
- Register/Login using email & password
- Role-based access for security (Admin/User)

### ✅ Customer Management
- Add, edit, and delete customer records
- List customers with pagination & search

### ✅ Document Management
- Upload customer-related documents
- List and retrieve uploaded files
- Automatic content filtering for vulgar/18+ content

### ✅ Search Functionality
- View uploaded documentation
- Logged-in users can add new documentation

---

## 🏗️ Project Structure
```
📂 CustomerManagementSystem
 ┣ 📂 src/main/java/com/example/customermanagement
 ┃ ┣ 📂 controller
 ┃ ┃ ┗ CustomerController.java
 ┃ ┣ 📂 model
 ┃ ┃ ┗ Customer.java
 ┃ ┣ 📂 repository
 ┃ ┃ ┗ CustomerRepository.java
 ┃ ┣ 📂 service
 ┃ ┃ ┗ CustomerService.java
 ┣ 📂 src/main/webapp
 ┃ ┣ 📂 WEB-INF
 ┃ ┃ ┗ views
 ┃ ┃ ┃ ┣ index.jsp
 ┃ ┃ ┃ ┣ customer-list.jsp
 ┃ ┃ ┃ ┗ upload.jsp
 ┣ 📄 pom.xml
 ┣ 📄 application.properties
 ┣ 📄 README.md
```

---

## 🏗️ Setup & Installation
### **1️⃣ Clone the Repository**
```sh
git clone https://github.com/reviewraj/customer-management.git
cd customer-management
```

### **2️⃣ Configure Database**
Modify `src/main/resources/application.properties`:
```properties
spring.datasource.url=jdbc:mysql://localhost:3306/customers_db
spring.datasource.username=root
spring.datasource.password=yourpassword
spring.jpa.hibernate.ddl-auto=update
```

### **3️⃣ Build & Run the Project**
```sh
mvn clean install
mvn spring-boot:run
```
The application will start on `http://localhost:8080` 🚀

---

## 📸 Screenshots
| Login Page  | Customer List  |
|------------|--------------|
| ![Login](https://via.placeholder.com/400) | ![Dashboard](https://via.placeholder.com/400) |

---

## 📜 API Endpoints
### 🔹 **Customer Management APIs**
| Method | Endpoint                | Description          |
|--------|-------------------------|----------------------|
| GET    | `/customers`            | Get all customers   |
| POST   | `/customers`            | Add a new customer  |
| PUT    | `/customers/{id}`       | Update a customer   |
| DELETE | `/customers/{id}`       | Delete a customer   |

### 🔹 **Document Management APIs**
| Method | Endpoint                    | Description                     |
|--------|------------------------------|---------------------------------|
| POST   | `/documents/upload`          | Upload a document              |
| GET    | `/documents/{id}`            | Get document by ID             |
| GET    | `/documents/search?query=xyz` | Search documents               |

---

## 🤝 Contributing
Contributions are welcome! To contribute:
1. Fork the repo
2. Create a new branch (`git checkout -b feature-name`)
3. Commit changes (`git commit -m 'Add new feature'`)
4. Push to the branch (`git push origin feature-name`)
5. Open a Pull Request 🎉

---

## 📧 Contact
- **Author:** [Nagaraju Golla](https://www.linkedin.com/in/nagaraju-golla-334793188/)
- **GitHub:** [reviewraj](https://github.com/reviewraj/)
- **Email:** nagaraju7876482@gmail.com

🌟 *If you like this project, give it a star! ⭐*
