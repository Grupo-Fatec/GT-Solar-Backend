
# PDF Generator Service

This branch implements a basic PDF Generator Service for the GT-Solar Backend project. The service provides an endpoint to generate and download a simple PDF file.

## Features

- **PDF Generation Endpoint:**  
  Access the endpoint at `/pdf/generate` to download a PDF file containing a title and a paragraph.
- **Spring Boot Integration:**  
  The service is built using Spring Boot and leverages the [OpenPDF](https://github.com/LibrePDF/OpenPDF) library for PDF creation.
- **Customizable Output:**  
  The generated PDF currently includes a centered title and a left-aligned paragraph as a demonstration.

## Usage

1. Start the application.
2. Navigate to `http://localhost:<port>/pdf/generate` in your browser or use a tool like `curl` or Postman.
3. A PDF file will be downloaded with a timestamped filename.

## Future Improvements

This PDF Generator Service is a starting point and will be incremented in the future to support:
- Dynamic content generation based on user input or database data.
- Enhanced formatting and styling options.
- Support for tables, images, and other PDF features.
- Integration with other modules of the GT-Solar Backend.

Stay tuned for updates as the service evolves!
=======
# GT-Solar-Backend

Backend system for the **GT Solar** project, developed by **Grupo Fatec**.  
This project provides API endpoints and core business logic for managing solar energy data and operations.

---

## 📚 Table of Contents

- [About](#about)
- [Features](#features)
- [Tech Stack](#tech-stack)
- [Getting Started](#getting-started)
- [Configuration](#configuration)

---

## 📖 About

**GT-Solar-Backend** is a Java-based backend application designed to support solar energy management solutions.  
The project handles data processing, business logic, and integration with front-end or mobile clients.

---

## 🚀 Features

- RESTful API for solar energy data management  
- User authentication and authorization  
- CRUD operations for core entities  
- Integration with databases  
- Error handling and logging  

---

## 🛠 Tech Stack

- **Language**: Java  
- **Framework**: Spring Boot *(or Jakarta EE - especifique se aplicável)*  
- **Database**: MySQL / PostgreSQL *(especifique se aplicável)*  
- **Build Tool**: Maven / Gradle *(especifique se aplicável)*  
- **Others**: *(Adicione outras tecnologias utilizadas)*  

---

## 🏁 Getting Started

### ✅ Prerequisites

- Java 17+  
- Maven ou Gradle  
- Banco de dados configurado (ver seção [Configuration](#configuration))

### 📦 Installation

Clone o repositório:

```bash
git clone https://github.com/Grupo-Fatec/GT-Solar-Backend.git
cd GT-Solar-Backend
```

## ⚙️ Configuration

Configure as variáveis de ambiente e a conexão com o banco de dados.

Edite o arquivo `src/main/resources/application.properties` ou `application.yml` com as configurações adequadas ao seu ambiente.

### Exemplo (`application.properties`):

```properties
spring.datasource.url=jdbc:mysql://localhost:3306/gt_solar
spring.datasource.username=your_db_user
spring.datasource.password=your_db_password
spring.jpa.hibernate.ddl-auto=update
spring.jpa.show-sql=true
```
