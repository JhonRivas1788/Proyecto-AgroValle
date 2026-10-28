# Arquitectura MVC en 4 Capas y Patrones GoF - AgroValle Connect

## 1. Arquitectura MVC en 4 Capas
* **Capa 1: Presentación / API REST (`@RestController` y DTOs):** Maneja las peticiones HTTP entrantes, valida la sintaxis mediante `@Valid` y retorna códigos de estado HTTP normalizados (200, 201, 400).
* **Capa 2: Lógica de Negocio y Dominio (`@Service` y `@Transactional`):** Centraliza las reglas de negocio agrícola (validación de cédulas, cálculos logísticos) y administra la concurrencia transaccional.
* **Capa 3: Acceso a Datos / Repositorio (`@Repository`):** Interfaces que extienden de `JpaRepository` para abstraer las consultas SQL y operaciones CRUD sobre PostgreSQL.
* **Capa 4: Persistencia Físico-Relacional (PostgreSQL):** Almacenamiento persistente que garantiza integridad referencial y propiedades ACID.

## 2. Patrones de Diseño GoF y Enterprise Implementados
* **Repository:** Desacopla la lógica de negocio de la persistencia de datos mediante Spring Data JPA.
* **Singleton / Inyección de Dependencias (IoC):** Gestión automática del ciclo de vida de los componentes mediante `@Autowired` por constructor en Java 17.
* **Strategy (Comportamiento):** Aplicado para el cálculo dinámico de fletes y comisiones según el municipio (Dagua vs. Palmira), cumpliendo con el principio Open/Closed (OCP).