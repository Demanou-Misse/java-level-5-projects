-- ================================
-- Drop tables if they already exist (to reset DB easily)
-- ================================
DROP TABLE IF EXISTS loans;
DROP TABLE IF EXISTS members;
DROP TABLE IF EXISTS books;

-- ================================
-- 📚 Table: books
-- ================================
CREATE TABLE books (
    id_book SERIAL PRIMARY KEY,
    title VARCHAR(200) NOT NULL,
    author VARCHAR(150) NOT NULL,
    category VARCHAR(100) NOT NULL,
    available BOOLEAN DEFAULT TRUE
);

-- ================================
-- 👥 Table: members
-- ================================
CREATE TABLE members (
    id_member SERIAL PRIMARY KEY,
    name VARCHAR(150) NOT NULL,
    email VARCHAR(150) UNIQUE NOT NULL
);

-- ================================
-- 📖 Table: loans
-- ================================
CREATE TABLE loans (
    id_loan SERIAL PRIMARY KEY,
    id_book INT NOT NULL,
    id_member INT NOT NULL,
    borrow_date DATE NOT NULL DEFAULT CURRENT_DATE,
    return_date DATE,

    -- 🔑 Foreign Keys
    CONSTRAINT fk_book FOREIGN KEY (id_book) REFERENCES books (id_book) ON DELETE CASCADE,
    CONSTRAINT fk_member FOREIGN KEY (id_member) REFERENCES members (id_member) ON DELETE CASCADE
);
