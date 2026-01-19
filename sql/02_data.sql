-- Seed Data for Person-Company-Address System

-- 1. Insert 10 Companies
INSERT INTO companies (name, industry)
SELECT 
    'Company ' || char(64 + n),
    CASE 
        WHEN n % 3 = 0 THEN 'Technology'
        WHEN n % 3 = 1 THEN 'Health'
        ELSE 'Finance'
    END
FROM generate_series(1, 10) n;

-- 2. Insert 1,000 Persons
-- Distribute persons among the 10 companies
INSERT INTO persons (name, age, email, company_id)
SELECT 
    'Person ' || n,
    (20 + (n % 40)),
    'person' || n || '@example.com',
    (1 + (n % 10)) -- Distributing across 10 companies
FROM generate_series(1, 1000) n;

-- 3. Insert 2-3 Addresses per Person
-- First address for everyone
INSERT INTO addresses (street, city, state, zip_code, person_id)
SELECT 
    n || ' Main St',
    'City ' || (n % 20),
    'State ' || (n % 5),
    'ZIP-' || (10000 + n),
    n
FROM generate_series(1, 1000) n;

-- Second address for everyone
INSERT INTO addresses (street, city, state, zip_code, person_id)
SELECT 
    n || ' Business Ave',
    'Metropolis ' || (n % 10),
    'District ' || (n % 3),
    'B-' || (20000 + n),
    n
FROM generate_series(1, 1000) n;

-- Third address for even-numbered persons
INSERT INTO addresses (street, city, state, zip_code, person_id)
SELECT 
    n || ' Holiday Dr',
    'Resort Town ' || (n % 5),
    'Island ' || (n % 2),
    'V-' || (30000 + n),
    n
FROM generate_series(1, 1000) n
WHERE n % 2 = 0;
