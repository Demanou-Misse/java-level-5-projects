# Java + PostgreSQL Projects 💾

Welcome!  
This repository contains three solid Java projects integrated with PostgreSQL.  
They are designed to strengthen my skills in **SQL, JDBC, database modeling, and layered architecture (DAO, Service, Model)**.

Each project is console-based, well-structured, and ready to be extended or refactored.

---

## 📚 Projects Overview

---

### ✅ 1. Library Management System
**Goals:**
- Manage books, members, and loan records  
- Implement CRUD operations with PostgreSQL  
- Handle book borrow/return logic with due dates and penalties  
- Generate reports (e.g., most borrowed books)  

**What it does:**
- Add, update, and delete books and members  
- Borrow and return books with proper stock management  
- Search books by title, author, or category  
- Generate simple statistics reports  

📌 **Concepts Used:** JDBC, SQL Joins, CRUD operations, Service-DAO pattern  
📂 **Folder:** `/LibraryManagementSystem`

---

### ✅ 2. Student Information System
**Goals:**
- Manage students, courses, enrollments, and grades  
- Work with one-to-many and many-to-many relationships  
- Automate GPA calculation and rankings  

**What it does:**
- Add, update, delete students and courses  
- Enroll students in multiple courses  
- Insert and update grades for each student  
- Compute averages and display top performers  

📌 **Concepts Used:** Relational database design, JDBC, SQL aggregation, Service-DAO pattern  
📂 **Folder:** `/StudentInformationSystem`

---

### ✅ 3. Expense Tracker
**Goals:**
- Track daily expenses with categories  
- Perform CRUD operations on expenses and categories  
- Generate monthly and category-based reports  

**What it does:**
- Add, update, delete expenses  
- Categorize expenses (Food, Transport, Leisure, etc.)  
- Search by date or category  
- Display total monthly spending and top expense category  

📌 **Concepts Used:** JDBC, SQL Group By, Aggregation functions, Service-DAO pattern  
📂 **Folder:** `/ExpenseTracker`

---

## 🛠️ Project Structure
Each project follows the same clean structure:  

src/
├── dao/ # Database access layer (CRUD operations)
├── model/ # Java classes mapping database tables
├── service/ # Business logic
├── util/ # Database connection and helpers
├── app/ # Main entry point
resources/
├── schema.sql # Database schema
├── data.sql # Sample data


---

## ▶️ How to Run the Projects
1. Clone this repository  
2. Set up PostgreSQL and run the `schema.sql` and `data.sql` scripts inside `/resources`  
3. Open the project in your IDE (IntelliJ, Eclipse, VS Code, etc.)  
4. Run `Main.java` from the `/app` folder  
5. Interact with the app via the console  

---

## 📬 Contact
If you have questions or suggestions, feel free to reach out:

- **GitHub:** Demanou-Misse  
- **Email:** missedemanou@gmail.com  

---

Keep learning, keep building.  
**Java + PostgreSQL unlocked 🚀**

