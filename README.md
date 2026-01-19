Requirements
------------
Each person has a name, age, email, company name, and multiple addresses.

A person has a one-to-one relationship with a company.

A company has a many-to-one relationship with persons.

A person can have multiple addresses.

Insert 1,000 records into a PostgreSQL database.

Functional Requirements

Display a list of persons with pagination.

Find a person by ID.

Implement AG Grid to display all persons in a table with the following columns:

Name

Age

Company Name

Add a “See More” option in each row.

When “See More” is clicked, display complete details of the selected person, including all addresses.



--------------------------------------------------------------------------------------------------------------

Person–Company–Address Management System



1. Overview

This system manages Person, Company, and Address data with proper relational mapping.
It supports bulk data insertion, pagination, detailed views, and tabular display using AG Grid.

2. Data Model & Relationships
2.1 Entity Relationships

Person ↔ Company

A Person is associated with one Company (One-to-One from Person to Company).

A Company can have many Persons (Many-to-One from Company to Person).

Person ↔ Address

A Person can have multiple Addresses.

Each Address belongs to one Person.

Relationship: One-to-Many

3. Database Schema (PostgreSQL)
 3.1 Company Table
 3.2 Person Table
 3.3 Address Table
 
4. Data Insertion(Person Details )


5. Backend API Requirements
5.1 List Persons (With Pagination)
Endpoint -> GET /api/persons?page=0&size=10
5.2 Find Person by ID
Endpoint -> GET /api/persons/{id}

Response 
{
  "id": 1,
  "name": "John Doe",
  "age": 28,
  "email": "john@example.com",
  "company": {
    "id": 2,
    "name": "ABC Pvt Ltd"
  },
  "addresses": [
    {
      "street": "MG Road",
      "city": "Bangalore",
      "state": "Karnataka",
      "country": "India",
      "pincode": "560001"
    }
  ]
}


6. Frontend (React + AG Grid)
6.1 Person List View (AG Grid)
 -Columns
 -Name
 -Age
 -Company Name
 -Action (See More)

 Features
 -Server-side pagination
 -Sorting
 -Filtering
 
6.2 See More (Person Details View)
When the user clicks “See More”:
 -Fetch person details by ID
 -Display:
   -Name
   -Age
   -Email
   -Company Name
   -All Addresses

This can be shown using:

Modal / Drawer

Separate details page


7. Functional Flow

  1.Load Person List using paginated API

  2.Display data in AG Grid

  3.User clicks See More

  4.Fetch person details using GET /api/persons/{id}

  5.Show full information including addresses
  
  
Technology Stack

-Backend: Spring Boot

-Frontend: React with Material UI (MUI)

-Data Grid: AG Grid

-API Type: REST API

------------------------------------------------------------------------------------------------Back End Prompts-----------------------------------------------------

Act as a senior software architect.

Design a Person–Company–Address Management System using:
- Spring Boot (REST API)
- Hibernate/JPA
- PostgreSQL
- React with Material UI
- AG Grid

Requirements:
- Person has name, age, email, company, and multiple addresses
- One-to-One: Person → Company
- Many-to-One: Company → Person
- One-to-Many: Person → Address
- Pagination, person detail view, and AG Grid integration

Provide:
- High-level architecture
- Entity relationships
- API flow
- Frontend-backend interaction diagram (textual)


Create a PostgreSQL database schema for a Person–Company–Address system.

Rules:
- Person has name, age, email
- Person belongs to one Company
- Company can have many Persons
- Person can have multiple Addresses
- Use proper foreign keys and constraints
- Optimize for pagination and joins

Provide:
- CREATE TABLE scripts
- Indexing suggestions
- Referential integrity rules


Generate PostgreSQL SQL scripts to insert:
- 10 companies
- 1,000 persons mapped to companies
- 2–3 addresses per person

Use generate_series where possible.
Ensure:
- Unique emails
- Realistic dummy data
- Proper foreign key mapping


Create Spring Boot JPA entities using Hibernate for:
- Person
- Company
- Address

Requirements:
- Proper annotations (@OneToOne, @ManyToOne, @OneToMany)
- Lazy loading where appropriate
- DTO-friendly structure
- Avoid infinite recursion (use Jackson annotations)

Also provide:
- Entity relationship explanation



Generate Spring Data JPA repositories for Person, Company, and Address.

Requirements:
- Pagination support
- Fetch Person with Company and Addresses
- Optimized queries using @EntityGraph or JOIN FETCH


Create REST APIs using Spring Boot for Person management.

Endpoints:
- GET /api/persons?page=0&size=10
- GET /api/persons/{id}

Requirements:
- Pagination response
- Clean DTO-based responses
- Proper HTTP status codes
- Exception handling (404, validation errors)

Return JSON exactly suitable for AG Grid.


Create a Spring Boot service layer for Person management.

Responsibilities:
- Business logic
- Pagination handling
- Mapping entities to DTOs
- Fetch person details with company and addresses

Follow clean architecture principles.


Design DTOs for:
- PersonListResponse
- PersonDetailResponse
- AddressResponse
- CompanyResponse

Ensure:
- No entity leakage
- Optimized for frontend usage
- Clean JSON structure





------------------------------------------------------------------------------------------------------- Front End Prompts-------------------------


Create a React component using:
- React
- Material UI
- AG Grid

Requirements:
- Display person list with columns:
  - Name
  - Age
  - Company Name
  - Action (See More)
- Server-side pagination
- Sorting and filtering
- Clean reusable component structure


Create a React component to show Person details.

Behavior:
- Triggered by "See More" click from AG Grid
- Fetch data from GET /api/persons/{id}
- Display:
  - Name
  - Age
  - Email
  - Company Name
  - List of all addresses

UI:
- Use Material UI
- Show in Modal or Drawer
- Responsive design


Create a frontend API service layer in React.

Requirements:
- Axios-based API calls
- Pagination support
- Error handling
- Reusable service functions for:
  - Fetch persons list
  - Fetch person by ID

-Database: PostgreSQL

-ORM: Hibernate
