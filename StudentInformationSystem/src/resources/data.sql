-- ============================
-- 🧠 STUDENT INFORMATION SYSTEM
-- Sample Data Initialization
-- ============================

-- === Clear existing data (optional for testing) ===
DELETE FROM grades;
DELETE FROM enrollments;
DELETE FROM students;
DELETE FROM courses;

-- === Insert Students ===
INSERT INTO students (student_id, first_name, last_name, email) VALUES
(1, 'Alice', 'Johnson', 'alice.johnson@example.com'),
(2, 'Bob', 'Smith', 'bob.smith@example.com'),
(3, 'Clara', 'Davis', 'clara.davis@example.com'),
(4, 'David', 'Miller', 'david.miller@example.com');

-- === Insert Courses ===
INSERT INTO courses (course_id, course_name, credits) VALUES
(101, 'Database Systems', 4),
(102, 'Object-Oriented Programming', 3),
(103, 'Data Structures', 3),
(104, 'Web Development', 2);

-- === Insert Enrollments (many-to-many relationships) ===
-- Format: student_id, course_id
INSERT INTO enrollments (enrollment_id, student_id, course_id) VALUES
(1, 1, 101),
(2, 1, 102),
(3, 2, 102),
(4, 2, 103),
(5, 3, 101),
(6, 3, 104),
(7, 4, 103),
(8, 4, 104);

-- === Insert Grades ===
-- Format: grade_id, enrollment_id, grade_value
INSERT INTO grades (grade_id, enrollment_id, grade_value) VALUES
(1, 1, 15.0),   -- Alice - Database Systems
(2, 2, 17.5),   -- Alice - OOP
(3, 3, 14.0),   -- Bob - OOP
(4, 4, 12.5),   -- Bob - Data Structures
(5, 5, 18.0),   -- Clara - Database Systems
(6, 6, 16.0),   -- Clara - Web Development
(7, 7, 13.0),   -- David - Data Structures
(8, 8, 15.5);   -- David - Web Development
