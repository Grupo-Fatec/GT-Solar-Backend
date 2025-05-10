# GT Solar Backend

## Overview
The GT Solar Backend project is a Spring Boot application designed to manage Admin entities for a solar energy management system. This application provides a RESTful API for creating, retrieving, updating, deleting, and deactivating Admin users.

## Project Structure
```
GT-Solar-Backend
├── src
│   ├── main
│   │   ├── java
│   │   │   └── org
│   │   │       └── github
│   │   │           └── gabrielgodoi
│   │   │               └── gtsolarbackend
│   │   │                   ├── controllers
│   │   │                   │   └── AdminController.java
│   │   │                   ├── entities
│   │   │                   │   └── Admin.java
│   │   │                   ├── repositories
│   │   │                   │   └── AdminRepository.java
│   │   │                   ├── services
│   │   │                   │   └── AdminService.java
│   │   │                   └── dtos
│   │   │                       └── AdminDTO.java
│   │   └── resources
│   │       └── application.properties
├── pom.xml
└── README.md
```

## Setup Instructions
1. **Clone the Repository**
   ```bash
   git clone <repository-url>
   cd GT-Solar-Backend
   ```

2. **Build the Project**
   Ensure you have Maven installed, then run:
   ```bash
   mvn clean install
   ```

3. **Run the Application**
   You can run the application using:
   ```bash
   mvn spring-boot:run
   ```

4. **Access the API**
   The API will be available at `http://localhost:8080/api/admin`. You can use tools like Postman or curl to interact with the endpoints.

## API Endpoints
- **Create Admin**: `POST /api/admin`
- **Get All Admins**: `GET /api/admin`
- **Get Admin by ID**: `GET /api/admin/{id}`
- **Update Admin**: `PUT /api/admin/{id}`
- **Delete Admin**: `DELETE /api/admin/{id}`
- **Deactivate Admin**: `PATCH /api/admin/{id}/deactivate`

## Dependencies
- Spring Boot
- Spring Data MongoDB
- Lombok

## License
This project is licensed under the MIT License. See the LICENSE file for details.