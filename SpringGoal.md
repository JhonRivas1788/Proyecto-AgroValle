# AgroValle Connect — Sprint Planning y Desglose Técnico

## 1. Simulación de Sprint Planning

### Sprint Goal

> **"Habilitar la persistencia y publicación de cosechas para agricultores de Dagua y Palmira, garantizando la integridad de datos en PostgreSQL."**

### Parte 1: Selección y negociación con el Product Owner (El QUÉ)

El PO presenta las historias priorizadas del Product Backlog. El equipo revisa los escenarios BDD (Given-When-Then), aclara dudas sobre reglas de negocio (validación de cédula duplicada, expiración de sesión) y evalúa su capacidad con base en la velocidad estimada (Planning Poker).

| HU | Historia | Story Points | Justificación de estimación |
|---|---|---|---|
| HU-01 | Registro de Agricultores | 5 | CRUD simple + validación de cédula duplicada |
| HU-07 | Autenticación e Inicio de Sesión | 8 | Requiere Spring Security, hashing de contraseña y manejo de tokens; mayor incertidumbre técnica |
| HU-02 | Publicación de Productos | 5 | CRUD + regla de negocio (fecha de cosecha no anterior a hoy) |
| **Total** | | **18 pts** | Dentro de la capacidad estimada del equipo (~20 pts, 2 desarrolladores) |

**Resultado:** el equipo se compromete con estas 3 historias para el ciclo de 2 semanas. Sprint Goal construido y aprobado por el equipo y el PO.

---

## 2. Desglose técnico por historia de usuario

Basado en el stack definido en el proyecto (Java 17 / Spring Boot / PostgreSQL, ver `pom.xml` y `README.md`) y en el Definition of Done (`docs/dod.md`).

### HU-01: Registro de Agricultores

| ID Tarea | Descripción Técnica | Componente / Tecnología | Atributo ISO 25010 |
|---|---|---|---|
| T-01-01 | Modelar entidad `Agricultor` (nombre, cédula, ubicación, auditoría) | Spring Data JPA / PostgreSQL | Mantenibilidad – Modularidad |
| T-01-02 | DTO de registro con validaciones (`@NotBlank`, `@Pattern` cédula) | Jakarta Bean Validation | Adecuación funcional – Corrección funcional |
| T-01-03 | Repositorio con `existsByCedula()` | Spring Data JPA | Eficiencia de desempeño – Comportamiento temporal |
| T-01-04 | Servicio que valida cédula única antes de persistir | Java / Spring Service Layer | Adecuación funcional – Completitud funcional |
| T-01-05 | Endpoint `POST /api/agricultores/registro` | Spring MVC / REST Controller | Compatibilidad – Interoperabilidad |
| T-01-06 | Manejo centralizado de errores (`@ControllerAdvice`) | Spring Exception Handling | Fiabilidad – Tolerancia a fallos |
| T-01-07 | Pruebas unitarias e integración de escenarios BDD | JUnit 5 / Mockito / Testcontainers | Fiabilidad – Madurez |

### HU-02: Publicación de Productos

| ID Tarea | Descripción Técnica | Componente / Tecnología | Atributo ISO 25010 |
|---|---|---|---|
| T-02-01 | Modelar entidad `Producto` (tipo, cantidad, fecha cosecha, estado, FK agricultor) | Spring Data JPA / PostgreSQL | Mantenibilidad – Modularidad |
| T-02-02 | DTO con validación `@FutureOrPresent` en fecha de cosecha | Jakarta Bean Validation | Adecuación funcional – Corrección funcional |
| T-02-03 | Servicio con regla de negocio de fecha | Java / Spring Service Layer | Adecuación funcional – Completitud funcional |
| T-02-04 | Endpoint `POST /api/productos`, protegido (solo agricultor autenticado) | Spring MVC + Spring Security | Seguridad – Control de acceso |
| T-02-05 | Persistencia transaccional (`@Transactional`) | Spring Data JPA / PostgreSQL | Fiabilidad – Integridad |
| T-02-06 | Pruebas de casos éxito y fecha inválida | JUnit 5 / Mockito | Mantenibilidad – Capacidad de prueba |

### HU-03: Visualización de Precios Regionales

| ID Tarea | Descripción Técnica | Componente / Tecnología | Atributo ISO 25010 |
|---|---|---|---|
| T-03-01 | Modelar entidad `Transaccion` (producto, precio, fecha) | Spring Data JPA / PostgreSQL | Mantenibilidad – Modularidad |
| T-03-02 | Query de promedio filtrado por producto y rango de fechas recientes | Spring Data JPA (`@Query`) | Eficiencia de desempeño – Comportamiento temporal |
| T-03-03 | Servicio que calcula el promedio y formatea en COP | Java / Spring Service Layer | Adecuación funcional – Corrección funcional |
| T-03-04 | Endpoint `GET /api/productos/{id}/precio-promedio` | Spring MVC / REST Controller | Usabilidad – Capacidad de aprendizaje |
| T-03-05 | Mensaje "información insuficiente" si no hay transacciones | Spring Exception Handling | Fiabilidad – Tolerancia a fallos |
| T-03-06 | Pruebas unitarias del cálculo de promedio | JUnit 5 / Mockito | Mantenibilidad – Capacidad de prueba |

### HU-04: Filtro de Categorías y Municipios

| ID Tarea | Descripción Técnica | Componente / Tecnología | Atributo ISO 25010 |
|---|---|---|---|
| T-04-01 | Añadir campos `categoria` y `municipio` a `Producto` | Spring Data JPA / PostgreSQL | Mantenibilidad – Modularidad |
| T-04-02 | Query dinámica por categoría + municipio | Spring Data JPA Specifications | Eficiencia de desempeño – Utilización de recursos |
| T-04-03 | Servicio de filtrado de productos | Java / Spring Service Layer | Adecuación funcional – Completitud funcional |
| T-04-04 | Endpoint `GET /api/productos?categoria=&municipio=` | Spring MVC / REST Controller | Usabilidad – Operabilidad |
| T-04-05 | Respuesta con mensaje "sin resultados" ante lista vacía | Spring MVC | Fiabilidad – Tolerancia a fallos |
| T-04-06 | Pruebas de combinaciones de filtros | JUnit 5 | Mantenibilidad – Capacidad de prueba |

### HU-05: Contacto Directo con el Agricultor

| ID Tarea | Descripción Técnica | Componente / Tecnología | Atributo ISO 25010 |
|---|---|---|---|
| T-05-01 | Modelar entidad `Mensaje` (remitente, destinatario, producto, contenido, fecha) | Spring Data JPA / PostgreSQL | Mantenibilidad – Modularidad |
| T-05-02 | DTO con validación `@NotBlank` en contenido | Jakarta Bean Validation | Adecuación funcional – Corrección funcional |
| T-05-03 | Servicio que persiste el mensaje y dispara notificación | Java / Spring Service Layer | Adecuación funcional – Completitud funcional |
| T-05-04 | Endpoint `POST /api/productos/{id}/mensajes`, protegido | Spring MVC + Spring Security | Seguridad – Control de acceso |
| T-05-05 | Publicación de evento asíncrono al agricultor | Spring `ApplicationEventPublisher` | Eficiencia de desempeño – Comportamiento temporal |
| T-05-06 | Pruebas de validación de mensaje vacío | JUnit 5 | Mantenibilidad – Capacidad de prueba |

### HU-06: Actualización de Perfil de Agricultor

| ID Tarea | Descripción Técnica | Componente / Tecnología | Atributo ISO 25010 |
|---|---|---|---|
| T-06-01 | DTO de actualización con validaciones (`@Pattern` teléfono) | Jakarta Bean Validation | Adecuación funcional – Corrección funcional |
| T-06-02 | Servicio de actualización parcial (PATCH) | Java / Spring Service Layer | Mantenibilidad – Modificabilidad |
| T-06-03 | Endpoint `PATCH /api/agricultores/{id}`, protegido (solo dueño de cuenta) | Spring MVC + Spring Security | Seguridad – Control de acceso |
| T-06-04 | Persistencia transaccional de cambios | Spring Data JPA / PostgreSQL | Fiabilidad – Integridad |
| T-06-05 | Pruebas de datos inválidos | JUnit 5 | Mantenibilidad – Capacidad de prueba |

### HU-07: Autenticación e Inicio de Sesión

| ID Tarea | Descripción Técnica | Componente / Tecnología | Atributo ISO 25010 |
|---|---|---|---|
| T-07-01 | Configurar `SecurityFilterChain` y filtro de autenticación | Spring Security | Seguridad – Confidencialidad |
| T-07-02 | Hashing de contraseñas | `PasswordEncoder` (BCrypt) | Seguridad – Integridad |
| T-07-03 | Endpoint `POST /api/auth/login` | Spring MVC / REST Controller | Adecuación funcional – Corrección funcional |
| T-07-04 | Generación y firma de token con expiración configurable | JJWT | Seguridad – No repudio |
| T-07-05 | Mensaje de error genérico ante credenciales incorrectas | Spring Security | Seguridad – Confidencialidad |
| T-07-06 | Pruebas de credenciales inválidas y token expirado | JUnit 5 / Spring Security Test | Fiabilidad – Madurez |

### HU-08: Carrito de Compras

| ID Tarea | Descripción Técnica | Componente / Tecnología | Atributo ISO 25010 |
|---|---|---|---|
| T-08-01 | Modelar entidades `Carrito` e `ItemCarrito` | Spring Data JPA / PostgreSQL | Mantenibilidad – Modularidad |
| T-08-02 | Servicio que valida stock disponible antes de reservar | Java / Spring Service Layer | Adecuación funcional – Corrección funcional |
| T-08-03 | Reserva de stock con bloqueo optimista | Spring Data JPA (`@Version`) / PostgreSQL | Fiabilidad – Integridad |
| T-08-04 | Endpoint `POST /api/carrito/items`, protegido | Spring MVC + Spring Security | Seguridad – Control de acceso |
| T-08-05 | Mensaje de "cantidad máxima disponible" ante stock insuficiente | Spring Exception Handling | Fiabilidad – Tolerancia a fallos |
| T-08-06 | Pruebas de concurrencia y validación de stock | JUnit 5 / Mockito | Mantenibilidad – Capacidad de prueba |

### HU-09: Emisión de Orden de Compra

| ID Tarea | Descripción Técnica | Componente / Tecnología | Atributo ISO 25010 |
|---|---|---|---|
| T-09-01 | Modelar entidades `Pedido` y `PedidoItem` (estado inicial "Confirmado") | Spring Data JPA / PostgreSQL | Mantenibilidad – Modularidad |
| T-09-02 | Transacción que descuenta stock y genera número de orden | Spring `@Transactional` | Fiabilidad – Integridad |
| T-09-03 | Endpoint `POST /api/pedidos` a partir del carrito activo | Spring MVC / REST Controller | Adecuación funcional – Completitud funcional |
| T-09-04 | Validación de carrito no vacío antes de confirmar | Bean Validation / lógica de servicio | Fiabilidad – Tolerancia a fallos |
| T-09-05 | Generación de identificador único de orden | Java (UUID / secuencia PostgreSQL) | Adecuación funcional – Corrección funcional |
| T-09-06 | Pruebas de rollback si falla el descuento de stock | JUnit 5 / Testcontainers | Fiabilidad – Madurez |

### HU-10: Notificación de Estado de Pedido

| ID Tarea | Descripción Técnica | Componente / Tecnología | Atributo ISO 25010 |
|---|---|---|---|
| T-10-01 | Modelar entidad `Notificacion` (destinatario, tipo, estado envío, intentos) | Spring Data JPA / PostgreSQL | Mantenibilidad – Modularidad |
| T-10-02 | Listener del evento "PedidoConfirmado" | Spring `ApplicationEventListener` | Adecuación funcional – Completitud funcional |
| T-10-03 | Servicio de envío de notificación (email/push) | Spring Mail / integración externa | Compatibilidad – Interoperabilidad |
| T-10-04 | Cola de reintentos para notificaciones fallidas | Spring Retry / scheduler | Fiabilidad – Recuperabilidad |
| T-10-05 | Job programado para reintentos pendientes | Spring Scheduling (`@Scheduled`) | Eficiencia de desempeño – Comportamiento temporal |
| T-10-06 | Pruebas de fallo de envío y reintento | JUnit 5 / Mockito | Mantenibilidad – Capacidad de prueba |

### HU-11: Confirmación de Alistamiento y Ruta

| ID Tarea | Descripción Técnica | Componente / Tecnología | Atributo ISO 25010 |
|---|---|---|---|
| T-11-01 | DTO de alistamiento con `@NotNull` en fecha de despacho | Jakarta Bean Validation | Adecuación funcional – Corrección funcional |
| T-11-02 | Servicio que cambia estado y calcula fecha estimada | Java / Spring Service Layer | Adecuación funcional – Completitud funcional |
| T-11-03 | Endpoint `PATCH /api/pedidos/{id}/alistamiento`, protegido (solo agricultor asignado) | Spring MVC + Spring Security | Seguridad – Control de acceso |
| T-11-04 | Máquina de estados del pedido (Confirmado → En camino) | Java (transiciones con enum) | Mantenibilidad – Modificabilidad |
| T-11-05 | Notificación al comprador con fecha estimada (reutiliza HU-10) | Spring Events + `NotificacionService` | Compatibilidad – Interoperabilidad |
| T-11-06 | Pruebas de transición inválida (sin fecha) | JUnit 5 | Mantenibilidad – Capacidad de prueba |

### HU-12: Seguimiento del Pedido

| ID Tarea | Descripción Técnica | Componente / Tecnología | Atributo ISO 25010 |
|---|---|---|---|
| T-12-01 | Servicio que consulta estado y calcula tiempo estimado de llegada | Java / Spring Service Layer | Adecuación funcional – Corrección funcional |
| T-12-02 | Endpoint `GET /api/pedidos/{id}/estado` | Spring MVC / REST Controller | Usabilidad – Operabilidad |
| T-12-03 | DTO de solo lectura (proyección) para no exponer datos sensibles | Spring MVC (DTO Projection) | Seguridad – Confidencialidad |
| T-12-04 | Manejo de estado "en preparación" cuando aún no ha sido despachado | Spring MVC | Fiabilidad – Tolerancia a fallos |
| T-12-05 | Pruebas de los distintos estados del pedido | JUnit 5 | Mantenibilidad – Capacidad de prueba |

### HU-13: Historial de Ventas

| ID Tarea | Descripción Técnica | Componente / Tecnología | Atributo ISO 25010 |
|---|---|---|---|
| T-13-01 | Query de pedidos completados por agricultor, ordenados por fecha | Spring Data JPA (`@Query` / `Pageable`) | Eficiencia de desempeño – Comportamiento temporal |
| T-13-02 | Servicio de historial de ventas | Java / Spring Service Layer | Adecuación funcional – Completitud funcional |
| T-13-03 | Endpoint `GET /api/agricultores/{id}/ventas` con paginación | Spring MVC (`Pageable`) | Eficiencia de desempeño – Utilización de recursos |
| T-13-04 | Mensaje "sin historial disponible" ante respuesta vacía | Spring MVC | Fiabilidad – Tolerancia a fallos |
| T-13-05 | Pruebas de orden y paginación | JUnit 5 | Mantenibilidad – Capacidad de prueba |

### HU-14: Calificación del Agricultor

| ID Tarea | Descripción Técnica | Componente / Tecnología | Atributo ISO 25010 |
|---|---|---|---|
| T-14-01 | Modelar entidad `Calificacion` (pedido, estrellas, comentario) | Spring Data JPA / PostgreSQL | Mantenibilidad – Modularidad |
| T-14-02 | DTO con `@Min(1)` / `@Max(5)` en estrellas | Jakarta Bean Validation | Adecuación funcional – Corrección funcional |
| T-14-03 | Servicio que valida estado "Entregado" antes de aceptar la calificación | Java / Spring Service Layer | Adecuación funcional – Corrección funcional |
| T-14-04 | Endpoint `POST /api/pedidos/{id}/calificacion` | Spring MVC / REST Controller | Seguridad – Control de acceso |
| T-14-05 | Actualización del promedio de calificación en el perfil | Spring Data JPA (`@Query` de agregación) | Eficiencia de desempeño – Comportamiento temporal |
| T-14-06 | Pruebas de calificación sobre pedido no entregado | JUnit 5 | Mantenibilidad – Capacidad de prueba |

### HU-15: Gestión de Usuarios (Administrador)

| ID Tarea | Descripción Técnica | Componente / Tecnología | Atributo ISO 25010 |
|---|---|---|---|
| T-15-01 | Añadir campo `estado` (activo/inactivo) a `Usuario` | Spring Data JPA / PostgreSQL | Mantenibilidad – Modularidad |
| T-15-02 | Rol `ADMIN` y restricción de endpoints | Spring Security (`@PreAuthorize`) | Seguridad – Control de acceso |
| T-15-03 | Servicio que desactiva cuenta y valida estado previo | Java / Spring Service Layer | Adecuación funcional – Corrección funcional |
| T-15-04 | Endpoint `PATCH /api/admin/usuarios/{id}/desactivar` | Spring MVC / REST Controller | Seguridad – Responsabilidad (auditoría) |
| T-15-05 | Bloqueo de login para cuentas inactivas | Spring Security (`UserDetailsService`) | Seguridad – Confidencialidad |
| T-15-06 | Pruebas del caso "cuenta ya desactivada" | JUnit 5 | Mantenibilidad – Capacidad de prueba |
