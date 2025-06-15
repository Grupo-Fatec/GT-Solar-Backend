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
