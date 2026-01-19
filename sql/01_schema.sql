-- Database Schema for Person-Company-Address Management System

-- Drop tables if they exist to ensure idempotency
DROP TABLE IF EXISTS addresses;
DROP TABLE IF EXISTS persons;
DROP TABLE IF EXISTS companies;

-- Create Company Table
CREATE TABLE companies (
    id SERIAL PRIMARY KEY,
    name VARCHAR(255) NOT NULL,
    industry VARCHAR(100),
    created_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP
);

-- Create Person Table
CREATE TABLE persons (
    id SERIAL PRIMARY KEY,
    name VARCHAR(255) NOT NULL,
    age INT CHECK (age >= 0),
    email VARCHAR(255) UNIQUE NOT NULL,
    company_id INT REFERENCES companies(id) ON DELETE SET NULL,
    created_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP
);

-- Create Address Table (Many-to-One with Person)
CREATE TABLE addresses (
    id SERIAL PRIMARY KEY,
    street VARCHAR(255) NOT NULL,
    city VARCHAR(100) NOT NULL,
    state VARCHAR(100),
    zip_code VARCHAR(20),
    person_id INT REFERENCES persons(id) ON DELETE CASCADE,
    created_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP
);

-- Indexing for Optimization
CREATE INDEX idx_persons_company_id ON persons(company_id);
CREATE INDEX idx_addresses_person_id ON addresses(person_id);
CREATE INDEX idx_persons_email ON persons(email);

-- Comments for documentation
COMMENT ON TABLE companies IS 'Stores organization information';
COMMENT ON TABLE persons IS 'Stores individual profiles linked to a company';
COMMENT ON TABLE addresses IS 'Stores multiple contact/residential locations for a person';
