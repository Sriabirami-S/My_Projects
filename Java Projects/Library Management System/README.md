# Library Management System (LMS)

## Overview
The **Library Management System (LMS)** is a Java desktop application built using **Swing** for the GUI and **MySQL** for the backend. It allows users to manage books, track borrowing and returns, and search for books efficiently.  

---

## Features
- **Add Books** – Add new books with title and author.  
- **Borrow Books** – Mark books as borrowed and update status.  
- **Return Books** – Return borrowed books and update status.  
- **Search Books** – Search books by title, author, or ID.  
- **Refresh Table** – Refresh the table to display the latest data.  

---

## Technologies Used
- **Java** – Core application logic  
- **Swing** – For GUI components  
- **MySQL** – Database for storing books and status  
- **JDBC** – For connecting Java with MySQL  

---

## Project Structure
Java Projects/
└─ Library Management System/
├─ LibraryGUI.java # Main GUI class for the LMS application
├─ DBHelper.java # Handles database connection
├─ Library.java # Handles database operations (add, borrow, return, search)

---

## Database Setup
1. Create a MySQL database called `library_db`.  
2. Create a table `books`:

```sql
CREATE TABLE books (
    id INT AUTO_INCREMENT PRIMARY KEY,
    title VARCHAR(255) NOT NULL,
    author VARCHAR(255),
    status TINYINT(1) DEFAULT 1
);
```
- status = 1 → Available

- status = 0 → Borrowed

3. Update DBHelper.java with your MySQL username, password, and database name.

## How to Run

1. Clone the repository:
```bash

git clone https://github.com/YourUsername/JavaProjects.git
```

2. Open the project in Eclipse or any Java IDE.

3. Make sure MySQL is running and the database is created.

4. Run LibraryGUI.java.

5. Use the buttons to Add, Borrow, Return, and Search books.

## Future Enhancements

- User authentication (Admin/Student roles)

- Export book list to CSV or PDF

- Notifications for overdue books

