# Bitácora de Sincronización Diaria (Daily Scrum) — Sprint 1

Sesiones diarias de sincronización de 15 minutos en las que la célula Scrum registra los avances técnicos, los compromisos inmediatos y la gestión de impedimentos durante el Sprint 1.

**Participantes habituales:** Jhon Stiven Rivas Angulo (Scrum Master), Reinaldo Daniel Niño Belalcazar (Product Owner), Kevin Andres Rosero Mestizo (Desarrollador) y Luis Eduardo Vera Orejuela (Desarrollador).

---

## Daily Scrum 1 — 18/09/2026

**Asistentes:** Jhon Stiven Rivas Angulo, Reinaldo Daniel Niño Belalcazar, Kevin Andres Rosero Mestizo, Luis Eduardo Vera Orejuela.

| Integrante | ¿Qué se construyó? | ¿Qué se construirá? | Impedimentos |
|---|---|---|---|
| **Jhon Stiven Rivas Angulo** | Se configuró el entorno inicial del proyecto en Spring Boot, el hook de Husky en `.husky/pre-commit` y el archivo de reglas `checkstyle.xml`. | Se agregarán las dependencias Maven necesarias (`spring-boot-starter-validation`, `h2`) e iniciará el diseño de la capa repositorio para la HU-04. | Error del hook de pre-commit por ruta de `JAVA_HOME` inexistente y propiedades de Checkstyle incompatibles (`filePattern` y `LineLength` dentro de `TreeWalker`). Quedó resuelto ajustando la estructura de `checkstyle.xml`. |
| **Reinaldo Daniel Niño Belalcazar** | Se estructuraron los DTOs de consulta iniciales y se revisó la especificación del contrato REST para la HU-07. | Se implementará la clase `ProductorResponse` (DTO que omite la cédula por privacidad) y la excepción personalizada `ProductorNoEncontradoException`. | Ninguno. |
| **Kevin Andres Rosero Mestizo** | Se creó el script DDL y la entidad JPA `ProductorEntity` mapeada a la tabla `usuarios_productores` en PostgreSQL (Tarea 1.1). | Se creará la interfaz `ProductorRepository` extendiendo `JpaRepository` con el método derivado `existsByCedula(String)` (Tarea 1.2). | Conflictos de compilación locales al intentar ejecutar los tests por falta de configuración del driver de PostgreSQL en la variable de entorno local; solucionado configurando H2 para el perfil de pruebas. |
| **Luis Eduardo Vera Orejuela** | Se revisaron los DTOs de entrada con Bean Validation (`@NotNull`, `@Pattern`, `@Size`) para la HU-01. | Se implementará el controlador `ProductorController` exponiendo el endpoint `POST /api/v1/auth/register` (Tarea 1.4). | Ninguno. |

**Acuerdos del equipo:**
1. Mantener activo el límite de trabajo en progreso (**WIP Limit $\le 3$**) en la columna *In Progress* del tablero Kanban.
2. Asegurar que las entidades JPA no se expongan directamente en los controladores, utilizando DTOs para las peticiones y respuestas.

---

## Daily Scrum 2 — 22/09/2026

**Asistentes:** Jhon Stiven Rivas Angulo, Reinaldo Daniel Niño Belalcazar, Kevin Andres Rosero Mestizo, Luis Eduardo Vera Orejuela.

**Estado del tablero Kanban al iniciar la sesión:** *In Progress:* 3/3 · *Code Review:* 1/2 · *Done:* 1

| Integrante | ¿Qué se construyó? | ¿Qué se construirá? | Impedimentos |
|---|---|---|---|
| **Jhon Stiven Rivas Angulo** | Se creó el repositorio `ProductoRepository` con consultas derivadas para filtrar por municipio y categoría, considerando únicamente ofertas con estado disponible (Tarea 4.2). | Se implementará el servicio `ProductoService` y la respuesta estructurada `ProductoResponse` (Tarea 4.3). | Ninguno. |
| **Reinaldo Daniel Niño Belalcazar** | Se implementó el servicio `ProductorService.buscarPorId(Long)` y la traducción de excepciones a estado `404 Not Found` en la capa `@RestControllerAdvice` (Tareas 7.2 y 7.3). | Se redactarán las pruebas automatizadas en JUnit 5 con `MockMvc` para la HU-07 (Tarea 7.4). | Retrasos en la ejecución de pruebas locales por desalineación en las versiones de JUnit 5 en el `pom.xml`; solucionado unificando dependencias con el starter de Spring Boot Test. |
| **Kevin Andres Rosero Mestizo** | Se implementó la lógica del servicio `ProductorService` con validaciones de negocio para cédulas de 6 a 10 dígitos y verificación del municipio en el Valle del Cauca (Tarea 1.3). | Se construirá la suite de pruebas automatizadas en JUnit 5 y `MockMvc` para validar los escenarios BDD de la HU-01 (Tarea 1.5). | Ninguno. |
| **Luis Eduardo Vera Orejuela** | Se finalizó el endpoint `POST /api/v1/auth/register` retornando HTTP `201 Created` y el DTO correspondiente (Tarea 1.4). | Se creará el modelo mínimo de `Producto` con datos semilla (`data.sql`) para habilitar la consulta de la HU-04 (Tarea 4.1). | Ninguno. |

**Acuerdos del equipo:**
1. Los Pull Requests abiertos en GitHub deben revisarse en un plazo máximo de 24 horas para no congestionar la columna *Code Review / Testing* (**WIP Limit $\le 2$**).
2. Todo PR debe incluir la captura del reporte de JaCoCo que demuestre el cumplimiento de la cobertura mínima exigida.

---

## Daily Scrum 3 — 25/09/2026

**Asistentes:** Jhon Stiven Rivas Angulo, Reinaldo Daniel Niño Belalcazar, Kevin Andres Rosero Mestizo, Luis Eduardo Vera Orejuela.

**Estado del tablero Kanban al iniciar la sesión:** *In Progress:* 2/3 · *Code Review:* 2/2 · *Done:* 3

| Integrante | ¿Qué se construyó? | ¿Qué se construirá? | Impedimentos |
|---|---|---|---|
| **Jhon Stiven Rivas Angulo** | Se implementó el controlador `ProductoController` exponiendo `GET /api/v1/productos` con parámetros opcionales `municipio` y `categoria` (Tarea 4.4). | Se desarrollarán las pruebas en JUnit 5 y `MockMvc` para los escenarios con y sin coincidencias de la HU-04 (Tarea 4.5). | Ninguno. |
| **Reinaldo Daniel Niño Belalcazar** | Se construyeron las pruebas unitarias e integradas en JUnit 5 para la HU-07, cubriendo los escenarios de perfil existente (200 OK) e inexistente (404 Not Found) (Tarea 7.4). | Se ejecutará la auditoría de linter con `mvn checkstyle:check` y se revisará el Pull Request de la HU-01. | Advertencias de Checkstyle por líneas con longitud mayor a 120 caracteres en los métodos de prueba; corregido formateando las cadenas BDD. |
| **Kevin Andres Rosero Mestizo** | Se completó la traducción BDD a JUnit 5 + `MockMvc` para la HU-01, cubriendo registro exitoso (201), datos faltantes (400), cédula duplicada (409) y municipio inválido (400) (Tarea 1.5). | Se realizará la prueba de integración contra la base de datos PostgreSQL real y se tomarán las evidencias para el reporte de Sprint Review (Tarea 1.7). | Ninguno. |
| **Luis Eduardo Vera Orejuela** | Se poblaron los datos semilla en `data.sql` con productos disponibles de Dagua y Palmira, además de un registro en estado agotado para probar la exclusión en filtros (Tarea 4.1). | Se realizará la revisión del Pull Request de la HU-04 e integración a la rama principal `main`. | Ninguno. |

**Acuerdos del equipo:**
1. Ejecutar el comando `mvn clean install` de manera integral antes de enviar los Pull Requests finales a la rama `main`.
2. Consolidar el acta de la Sprint Review y la Retrospectiva del Sprint 1 para la entrega oficial del Primer Corte.