CREATE TABLE IF NOT EXISTS students (
    id SERIAL PRIMARY KEY,
    code VARCHAR(20) NOT NULL,
    name VARCHAR(100) NOT NULL,
    email VARCHAR(100) UNIQUE NOT NULL,
    year INT NOT NULL
    );

CREATE TABLE IF NOT EXISTS instructors (
    id SERIAL PRIMARY KEY,
    name VARCHAR(100) NOT NULL,
    email VARCHAR(100) UNIQUE NOT NULL
    );

CREATE TABLE IF NOT EXISTS packs (
    id SERIAL PRIMARY KEY,
    year INT NOT NULL,
    semester INT NOT NULL,
    name VARCHAR(100) NOT NULL
    );

CREATE TABLE IF NOT EXISTS courses (
    id SERIAL PRIMARY KEY,
    type VARCHAR(20) NOT NULL,
    code VARCHAR(20) NOT NULL,
    abbr VARCHAR(20),
    name VARCHAR(100) NOT NULL,
    instructor_id INT REFERENCES instructors(id),
    pack_id INT REFERENCES packs(id),
    group_count INT,
    description TEXT
    );
