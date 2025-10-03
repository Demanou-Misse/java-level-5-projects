-- ================================
-- 📚 Insert sample books
-- ================================
INSERT INTO books (title, author, category) VALUES
('Clean Code', 'Robert C. Martin', 'Programming'),
('Java: The Complete Reference', 'Herbert Schildt', 'Programming'),
('The Pragmatic Programmer', 'Andrew Hunt', 'Programming'),
('Effective Java', 'Joshua Bloch', 'Programming'),
('Head First Design Patterns', 'Eric Freeman', 'Programming'),
('Introduction to Algorithms', 'Thomas H. Cormen', 'Computer Science'),
('Artificial Intelligence: A Modern Approach', 'Stuart Russell', 'AI'),
('Database System Concepts', 'Abraham Silberschatz', 'Databases'),
('Modern Operating Systems', 'Andrew S. Tanenbaum', 'Operating Systems'),
('Computer Networks', 'Andrew S. Tanenbaum', 'Networking'),
('Don Quixote', 'Miguel de Cervantes', 'Literature'),
('Pride and Prejudice', 'Jane Austen', 'Literature'),
('Hamlet', 'William Shakespeare', 'Drama'),
('The Odyssey', 'Homer', 'Classic'),
('Moby Dick', 'Herman Melville', 'Classic'),
('The Great Gatsby', 'F. Scott Fitzgerald', 'Classic'),
('War and Peace', 'Leo Tolstoy', 'Classic'),
('To Kill a Mockingbird', 'Harper Lee', 'Classic'),
('1984', 'George Orwell', 'Dystopia'),
('Brave New World', 'Aldous Huxley', 'Dystopia');

-- ================================
-- 👥 Insert sample members
-- ================================
INSERT INTO members (name, email) VALUES
('Alice Johnson', 'alice.johnson@example.com'),
('Bob Smith', 'bob.smith@example.com'),
('Charlie Brown', 'charlie.brown@example.com'),
('Diana Prince', 'diana.prince@example.com'),
('Ethan Hunt', 'ethan.hunt@example.com'),
('Fiona Davis', 'fiona.davis@example.com'),
('George Martin', 'george.martin@example.com'),
('Hannah Wilson', 'hannah.wilson@example.com'),
('Ian Clarke', 'ian.clarke@example.com'),
('Julia Roberts', 'julia.roberts@example.com');

-- ================================
-- 📖 Insert sample loans (some returned, some active)
-- ================================
-- Alice borrows Clean Code
INSERT INTO loans (book_id, member_id, borrow_date, return_date)
VALUES (1, 1, '2025-09-01', '2025-09-10');

-- Bob borrows Java Reference (still active)
INSERT INTO loans (book_id, member_id, borrow_date)
VALUES (2, 2, '2025-09-15');

-- Charlie borrows Effective Java and returned it
INSERT INTO loans (book_id, member_id, borrow_date, return_date)
VALUES (4, 3, '2025-08-20', '2025-08-30');

-- Diana borrows Pride and Prejudice
INSERT INTO loans (book_id, member_id, borrow_date)
VALUES (12, 4, '2025-09-18');

-- Ethan borrows 1984 (returned late)
INSERT INTO loans (book_id, member_id, borrow_date, return_date)
VALUES (19, 5, '2025-08-01', '2025-08-20');

-- Fiona borrows The Odyssey (still active)
INSERT INTO loans (book_id, member_id, borrow_date)
VALUES (14, 6, '2025-09-22');

-- George borrows Database System Concepts (returned)
INSERT INTO loans (book_id, member_id, borrow_date, return_date)
VALUES (8, 7, '2025-07-01', '2025-07-15');

-- Hannah borrows Modern Operating Systems
INSERT INTO loans (book_id, member_id, borrow_date)
VALUES (9, 8, '2025-09-10');

-- Ian borrows Hamlet (still active)
INSERT INTO loans (book_id, member_id, borrow_date)
VALUES (13, 9, '2025-09-05');

-- Julia borrows Brave New World
INSERT INTO loans (book_id, member_id, borrow_date, return_date)
VALUES (20, 10, '2025-08-15', '2025-08-25');
