# VetTurno - Agenda Digital para Veterinaria Huellitas

VetTurno es una API RESTful desarrollada con Spring Boot 3 para gestionar responsables, mascotas, veterinarios y agendamiento de citas sin cruces de horario.

## Tecnologías utilizadas
- **Java 17**
- **Spring Boot 3.4.3**
- **Spring Data JPA & Hibernate**
- **MySQL Database**
- **Spring Security & JWT (JJWT)**
- **Bean Validation (@Valid)**
- **Springdoc OpenAPI / Swagger UI**
- **Maven**

## Seguridad y Roles
- `USER` (Recepcionista - Paula): Registrar responsables, mascotas y agendar citas.
- `ADMIN` (Administradora - Doña Marta): Todo lo anterior + registro de veterinarios.

## Cómo ejecutar el proyecto
1. Clonar el repositorio.
2. Configurar la base de datos MySQL en `src/main/resources/application.properties`.
3. Iniciar el servidor mediante terminal:
   .\mvnw.cmd spring-boot:run
