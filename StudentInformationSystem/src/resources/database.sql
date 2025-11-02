-- ============================================
-- 🧠 STUDENT INFORMATION SYSTEM - DATABASE SCHEMA
-- Author: Your Name
-- Description: Database structure for the SIS project
-- ============================================

-- === Drop existing tables (optional during dev) ===
DROP TABLE IF EXISTS grades;
DROP TABLE IF EXISTS enrollments;
DROP TABLE IF EXISTS courses;
DROP TABLE IF EXISTS students;

-- ============================================
-- 1️⃣ STUDENTS TABLE
-- ============================================
CREATE TABLE students (
    student_id SERIAL PRIMARY KEY,
    first_name VARCHAR(50) NOT NULL,
    last_name VARCHAR(50) NOT NULL,
    email VARCHAR(100) UNIQUE NOT NULL,
    registration_date TIMESTAMP DEFAULT CURRENT_TIMESTAMP
);

-- ============================================
-- 2️⃣ COURSES TABLE
-- ============================================
CREATE TABLE courses (
    course_id SERIAL PRIMARY KEY,
    course_name VARCHAR(100) NOT NULL,
    course_code VARCHAR(20) UNIQUE,
    credits INT CHECK (credits > 0),
    department VARCHAR(100)
);

-- ============================================
-- 3️⃣ ENROLLMENTS TABLE (many-to-many relation)
-- ============================================
CREATE TABLE enrollments (
    enrollment_id SERIAL PRIMARY KEY,
    student_id INT NOT NULL,
    course_id INT NOT NULL,
    enrollment_date TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
    UNIQUE (student_id, course_id),
    FOREIGN KEY (student_id) REFERENCES students(student_id) ON DELETE CASCADE,
    FOREIGN KEY (course_id) REFERENCES courses(course_id) ON DELETE CASCADE
);

-- ============================================
-- 4️⃣ GRADES TABLE
-- ============================================
CREATE TABLE grades (
    grade_id SERIAL PRIMARY KEY,
    enrollment_id INT NOT NULL,
    grade_value DECIMAL(4,2) CHECK (grade_value >= 0 AND grade_value <= 20),
    grade_date TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
    FOREIGN KEY (enrollment_id) REFERENCES enrollments(enrollment_id) ON DELETE CASCADE
);

-- ============================================
-- 5️⃣ OPTIONAL VIEW: GPA & RANKING
-- ============================================
-- This view simplifies queries for student performance
CREATE OR REPLACE VIEW student_gpa AS
SELECT
    s.student_id,
    s.first_name,
    s.last_name,
    ROUND(AVG(g.grade_value), 2) AS gpa
FROM students s
JOIN enrollments e ON s.student_id = e.student_id
JOIN grades g ON e.enrollment_id = g.enrollment_id
GROUP BY s.student_id, s.first_name, s.last_name
ORDER BY gpa DESC;
