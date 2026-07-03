# 📚 Library Management System

A full-stack **Library Management System** developed using **Java, Spring Boot, Hibernate (JPA), PostgreSQL, HTML, CSS, and JavaScript**. This application provides a streamlined portal where managers can upload books along with their PDF files, and students can view, search, and read books online.

---

## 🌐 Live Full-Stack Deployment (Render Hosting)

The entire full-stack application is deployed and hosted using **Render cloud services**:

- **Frontend Interface Application:** https://library-management-system-1-lcwt.onrender.com/login.html
- **Backend Spring Boot API Service:** `https://library-management-system-28b8.onrender.com`

*The frontend application is built using Vite and deployed as a Static Site, which securely routes all data requests to the active Spring Boot REST API backend service running concurrently on Render.*

---

## 🚀 Features & Access Control

- 🔐 **Role-Based Authentication:** A secure login system that handles distinct user permissions for Managers and Students.
- ➕ **Manager-Exclusive Privileges:** Only users logged in with Manager credentials possess authorization to add new books and upload PDF files to the system.
- 📚 **Student-Exclusive Privileges:** Users logged in as Students are strictly limited to browsing the catalog, searching for titles, and reading available books.
- 📄 **PDF Management:** High-performance storage mapping that links uploaded PDF binary files directly to SQL metadata relations.
- 💾 **Robust Database Integrity:** Powered by PostgreSQL for secure data persistence.
- 🎨 **Responsive UI:** Minimalist and intuitive user interface built with semantic HTML and custom CSS.

---

## 🛠️ Technologies Used

### Backend
- **Java 21**
- **Spring Boot** (Spring MVC, Spring Data JPA, Spring Security)
- **Hibernate** (ORM Framework)
- **Maven** (Dependency Management)

### Frontend
- **HTML5** & **CSS3**
- **JavaScript (ES6+)**
- **Vite** (Frontend Build Tool / Bundler)

### Database
- **PostgreSQL**

### Tools & Platforms
- **IntelliJ IDEA** & **VS Code**
- **Render** (Cloud Platforms used for both Frontend and Backend hosting)
- **Postman** (API Testing)
- **Git** & **GitHub**

---

## 📂 Project Structure

```text
Library-Management-System
│
├── Backend/
│   ├── src/
│   │   ├── main/
│   │   │   ├── java/com/library/
│   │   │   │   ├── controller/
│   │   │   │   ├── service/
│   │   │   │   ├── repository/
│   │   │   │   ├── entity/
│   │   │   │   └── config/
│   │   │   └── resources/
│   │   └── test/
│   └── pom.xml
│
├── Frontend/
│   └── library-management-frontend/
│       ├── public/
│       ├── src/
│       ├── index.html
│       ├── login.html
│       ├── add-book.html
│       ├── view-book.html
│       ├── package.json
│       └── vite.config.js
│
└── README.md

# 👤 Sample Login Credentials

### 👨‍💼 Manager Login

| Username | Password |
|----------|----------|
| manager | manager123 |

Manager can:

- Add new books
- Upload PDF books
- View all books

---

### 🎓 Student Login

| Username | Password |
|----------|----------|
| student | student123 |

Student can:

- View available books
- Open uploaded PDF books

- View available books
- Open uploaded PDF books
