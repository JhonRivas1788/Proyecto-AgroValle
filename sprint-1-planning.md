# Sprint 1 Planning — AgroValle Connect

| Campo | Valor |
|---|---|
| **Sprint** | 1 |
| **Fechas** | Inicio: 15/09/2026 — Fin: 30/09/2026 |
| **Capacidad** | 10 Story Points (equipo con nivel inicial en Java 17 / Spring Boot) |
| **Historias seleccionadas** | HU-01 (5 pts), HU-07 (2 pts), HU-04 (3 pts) |
| **Integrantes Célula Scrum** | Jhon Stiven Rivas Angulo (Scrum Master)<br>Reinaldo Daniel Niño Belalcazar (Product Owner)<br>Kevin Andres Rosero Mestizo (Desarrollador)<br>Luis Eduardo Vera Orejuela (Desarrollador) |

---

## 1. Sprint Goal

> **Sprint Goal:** Habilitar el registro inicial de agricultores del Valle del Cauca y la consulta filtrada del catálogo agrícola, validando la persistencia en PostgreSQL y la arquitectura REST.

---

## 2. Historias Seleccionadas

| ID | Historia de Usuario | Prioridad (MoSCoW) | Story Points |
|---|---|:---:|:---:|
| **HU-01** | Registro de Agricultores | **Must Have** | 5 |
| **HU-07** | Consulta de Perfil de Agricultor | **Must Have** | 2 |
| **HU-04** | Filtro de Categorías y Municipios | **Must Have** | 3 |
| **Total comprometido** | | | **10 pts** |

*Las tres historias son Must Have y forman un flujo vertical coherente: se registra un agricultor (HU-01), se consulta su perfil (HU-07) y se consultan los productos filtrados (HU-04).*

---

## 3. Refinamiento: De Escenarios BDD a Contratos REST

### HU-01: Registro de Agricultores
* **Endpoint:** `POST /api/v1/auth/register`
* **Entrada (JSON):** `{ "nombre": "String", "cedula": "String", "ubicacionValle": "String" }`
* **Éxito:** `201 Created` con el objeto Productor creado en estado `ACTIVO`.
* **Errores:** `400 Bad Request` si falta un dato obligatorio, si la cédula no tiene formato válido (6 a 10 dígitos) o si el municipio no pertenece al Valle del Cauca. `409 Conflict` si la cédula ya existe.

### HU-07: Consulta de Perfil de Agricultor
* **Endpoint:** `GET /api/v1/productores/{id}`
* **Éxito:** `200 OK` con DTO de consulta (`id`, `nombre`, `ubicacionValle`), sin exponer la cédula por confidencialidad.
* **Errores:** `404 Not Found` si el productor no existe.

### HU-04: Filtro de Categorías y Municipios
* **Endpoint:** `GET /api/v1/productos?municipio=Dagua&categoria=Frutas`
* **Éxito:** `200 OK` con un arreglo JSON de productos disponibles.
* **Errores/Casos Borde:** `200 OK` con arreglo vacío `[]` si no hay coincidencias (el cliente muestra mensaje "sin resultados"). Los productos en estado `AGOTADO` se excluyen automáticamente.

---

### Decisiones Técnicas del Refinamiento

* **Arquitectura:** Estructura estricta en capas `controller` $\rightarrow$ `service` $\rightarrow$ `repository` $\rightarrow$ `model`, utilizando DTOs para entrada y salida (`ProductorRequest`, `ProductorResponse`, `ProductoResponse`). Las entidades JPA no se exponen directamente en los controladores.
* **Persistencia:** PostgreSQL con Spring Data JPA. Para la ejecución rápida de pruebas en entornos de integración se utiliza base de datos en memoria (H2). Se incluye la prueba manual obligatoria contra PostgreSQL real (Tarea 1.7).
* **Seguridad y Confidencialidad:** La cédula se almacena y valida, pero no se devuelve en el DTO de respuesta pública de la HU-07.
* **Validaciones Bean Validation:** La cédula se valida con `@Pattern` (dígitos entre 6 y 10 caracteres). El municipio (`ubicacionValle`) se valida contra una lista cerrada de municipios del Valle del Cauca (`enum MunicipioValle`).
* **Estado de Cuenta:** El productor se registra con estado `ACTIVO` por defecto (`enum EstadoCuenta: ACTIVO, DESACTIVADO`).
* **Manejo de Dependencia entre Historias:** Como la HU-04 requiere la entidad `Producto` (cuya publicación completa se desarrolla en la HU-02 en un sprint posterior), en este sprint se construye el modelo mínimo de `Producto` (`id`, `nombre`, `categoria`, `municipio`, `disponible`) cargado con datos semilla en `data.sql`.
* **Dependencias Maven Agregadas:** `spring-boot-starter-web`, `spring-boot-starter-data-jpa`, `spring-boot-starter-validation`, `postgresql` y `h2` (scope test).

---

## 4. Descomposición en Tareas Técnicas e ISO/IEC 25010

### HU-01: Registro de Agricultores (5 Story Points)
**Rama de desarrollo:** `feature/HU01-registro-productor`

| Tarea | Duración | Descripción Técnica | Tecnología | Criterio ISO/IEC 25010 |
|---|:---:|---|---|---|
| **Tarea 1.1** | 3 h | Diseñar e implementar el DDL y mapeo JPA de la tabla `usuarios_productores` (`id`, `nombre`, `ubicacion_valle`, `cedula` única, `estado` por defecto `ACTIVO`). | PostgreSQL, Spring Data JPA, `ProductorEntity` | Mantenibilidad y Sostenibilidad |
| **Tarea 1.2** | 2 h | Crear `ProductorRepository` extendiendo `JpaRepository` con el método derivado `existsByCedula(String)`. | Java 17, Spring Data JPA | Adecuación Funcional |
| **Tarea 1.3** | 6 h | Implementar `ProductorService`: validar campos obligatorios, formato de cédula (6 a 10 dígitos), municipio en el Valle y duplicidad de cédula. | Java 17, Spring Service (`@Service`) | Seguridad y Tolerancia a Fallos |
| **Tarea 1.4** | 5 h | Implementar `ProductorController` con `POST /api/v1/auth/register`, DTO de entrada con Bean Validation (`@Valid`) y respuesta `201 Created`. | Spring Boot (`@RestController`) | Compatibilidad y Mantenibilidad |
| **Tarea 1.5** | 7 h | **[OBLIGATORIA]** Traducir escenarios BDD de HU-01 a JUnit 5 + MockMvc: registro exitoso (201), dato faltante (400), cédula inválida (400), municipio fuera del Valle (400) y cédula duplicada (409). | JUnit 5 (`@Test`, `@DisplayName`), Spring Boot Test, Mockito | Correctitud Funcional y Fiabilidad |
| **Tarea 1.6** | 1 h | Auditoría de linter: ejecutar `mvn checkstyle:check` y corregir advertencias. | Checkstyle, Maven | Mantenibilidad y Legibilidad |
| **Tarea 1.7** | 2 h | Verificar contra PostgreSQL real el registro de un productor, su consulta por id y el filtro de productos; guardar evidencias SQL/Postman para `docs/sprint-1-review.md`. | PostgreSQL, Spring Boot, Postman | Fiabilidad y Correctitud Funcional |

**Subtotal HU-01:** 26 horas de ingeniería

---

### HU-07: Consulta de Perfil de Agricultor (2 Story Points)
**Rama de desarrollo:** `feature/HU07-consulta-perfil`

| Tarea | Duración | Descripción Técnica | Tecnología | Criterio ISO/IEC 25010 |
|---|:---:|---|---|---|
| **Tarea 7.1** | 2 h | Crear `ProductorResponse` (DTO de consulta sin la cédula) y su conversor desde la entidad. | Java 17 (`record`), Patrón DTO | Seguridad (Confidencialidad) |
| **Tarea 7.2** | 2 h | Implementar `ProductorService.buscarPorId(Long)` lanzando `ProductorNoEncontradoException` si no existe. | Java 17, Spring Service (`@Service`) | Adecuación Funcional |
| **Tarea 7.3** | 3 h | Implementar `GET /api/v1/productores/{id}` y `@RestControllerAdvice` para traducir la excepción a `404 Not Found`. | Spring Boot, `@RestControllerAdvice` | Compatibilidad y Fiabilidad |
| **Tarea 7.4** | 3 h | **[OBLIGATORIA]** Traducir escenarios BDD de HU-07 a JUnit 5 + MockMvc: perfil existente (200 OK sin cédula) y perfil inexistente (404 Not Found). | JUnit 5, MockMvc, Mockito | Correctitud Funcional y Fiabilidad |
| **Tarea 7.5** | 1 h | Auditoría de linter: ejecutar `mvn checkstyle:check`. | Checkstyle, Maven | Mantenibilidad y Legibilidad |

**Subtotal HU-07:** 11 horas de ingeniería

---

### HU-04: Filtro de Categorías y Municipios (3 Story Points)
**Rama de desarrollo:** `feature/HU04-filtro-productos`

| Tarea | Duración | Descripción Técnica | Tecnología | Criterio ISO/IEC 25010 |
|---|:---:|---|---|---|
| **Tarea 4.1** | 3 h | Crear entidad mínima `Producto` (`nombre`, `categoria`, `municipio`, `disponible`) y datos semilla en `data.sql` (incluyendo productos agotados). | PostgreSQL, Spring Data JPA, `data.sql` | Mantenibilidad |
| **Tarea 4.2** | 3 h | Crear `ProductoRepository` con consulta por municipio y categoría filtrando solo los productos en estado disponible. | Spring Data JPA (`@Query` / Derivado) | Eficiencia de Desempeño |
| **Tarea 4.3** | 3 h | Implementar `ProductoService.filtrar(municipio, categoria)` y `ProductoResponse` (DTO). | Java 17, Spring Service (`@Service`) | Adecuación Funcional |
| **Tarea 4.4** | 3 h | Implementar `GET /api/v1/productos` con parámetros opcionales (`@RequestParam`), respuesta `200 OK` y arreglo JSON. | Spring Boot (`@RestController`) | Compatibilidad y Usabilidad |
| **Tarea 4.5** | 5 h | **[OBLIGATORIA]** Traducir escenarios BDD de HU-04 a JUnit 5 + MockMvc: filtro con resultados, filtro sin resultados (arreglo vacío) y exclusión de productos agotados. | JUnit 5, MockMvc, Spring Boot Test | Correctitud Funcional y Fiabilidad |
| **Tarea 4.6** | 1 h | Auditoría de linter: ejecutar `mvn checkstyle:check`. | Checkstyle, Maven | Mantenibilidad y Legibilidad |

**Subtotal HU-04:** 18 horas de ingeniería

---

**Total estimado del Sprint:** 55 horas de ingeniería

---

## 5. Asignación de Tareas por Integrante

| Integrante | Tareas Asignadas | Responsabilidad Principal |
|---|---|---|
| **Kevin Andres Rosero Mestizo** | Tareas 1.1, 1.2, 1.3, 1.7 | Capa Datos/Servicios HU-01, Pruebas en PostgreSQL real y Code Review de HU-04 |
| **Luis Eduardo Vera Orejuela** | Tareas 1.4, 1.5, 4.1 | Controlador y Pruebas JUnit 5 HU-01, Datos Semilla HU-04 |
| **Reinaldo Daniel Niño Belalcazar** | Tareas 7.1, 7.2, 7.3, 7.4 | Capa completa y Pruebas JUnit 5 HU-07, Code Review de HU-01 |
| **Jhon Stiven Rivas Angulo** | Tareas 4.2, 4.3, 4.4, 4.5 | Capa completa y Pruebas JUnit 5 HU-04, Configuración de Maven/Checkstyle y Code Review de HU-07 |
| **Todos los Integrantes** | Tareas 1.6, 7.5, 4.6 | Auditoría de linter (Checkstyle) sobre su propia base de código |

*Nota: Dado que el equipo cuenta con 4 integrantes y se mantiene un límite de trabajo en progreso de máximo 3 tarjetas en la columna "In Progress", quien no tenga una tarea activa en desarrollo se enfoca en la revisión de Pull Requests en "Code Review / Testing".*

---

## 6. Flujo de Trabajo del Sprint (Git & Kanban)

* **Ramas de características:** Una rama corta por historia (`feature/HU01-registro-productor`, `feature/HU07-consulta-perfil`, `feature/HU04-filtro-productos`), integradas a `main` únicamente mediante Pull Request.
* **Commits Semánticos:** Estándar Conventional Commits. Ejemplo: `feat(auth): exponer endpoint POST /api/v1/auth/register`, `test(productor): cubrir escenario BDD de registro exitoso`.
* **Pull Request (Peer Review):** Todo PR describe los escenarios BDD probados, adjunta evidencias de pruebas en JUnit 5 y requiere al menos una aprobación (`Approve`) de un revisor distinto al autor.
* **Gestión Visual (GitHub Projects):** 5 columnas obligatorias (`Product Backlog`, `Sprint Backlog`, `In Progress`, `Code Review / Testing`, `Done`) con límites WIP (`In Progress` $\le 3$, `Code Review` $\le 2$).
* **Política de Done:** Una tarjeta pasa a `Done` solo con PR aprobado por un par, pruebas JUnit 5 en verde y 0 advertencias en Checkstyle (ver `docs/dod.md`).

---

## 7. Gestión de Riesgos

| Riesgo Identificado | Impacto | Estrategia de Mitigación |
|---|:---:|---|
| **Nivel inicial del equipo en Spring Boot** | Alto | Limitar la capacidad inicial a 10 SP y aplicar revisión cruzada obligatoria en cada Pull Request. |
| **Configuración dispar de PostgreSQL en entornos locales** | Medio | Utilizar variables de entorno en `application.properties` y la base H2 para el entorno de pruebas automatizadas. |
| **Comportamiento divergente entre H2 y PostgreSQL** | Medio | Ejecución obligatoria de la Tarea 1.7 (verificación manual en PostgreSQL real con evidencias capturadas en Postman). |
| **HU-04 depende de la entidad Producto (HU-02 fuera de este sprint)** | Bajo | Crear un modelo mínimo de `Producto` con datos semilla (`data.sql`) en la Tarea 4.1 para habilitar la consulta. |
| **Fallas en hooks de Husky por rutas de `JAVA_HOME` o Checkstyle** | Medio | Documentar paso a paso la configuración de variables de entorno del JDK 17 en el `README.md`. |