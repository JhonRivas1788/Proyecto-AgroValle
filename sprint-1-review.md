# Sprint 1 Review y Demo — AgroValle Connect

| Campo | Valor |
|---|---|
| Fecha de la revisión | 30/09/2026 |
| Asistentes | Jhon Stiven Rivas Angulo, Reinaldo Daniel Niño Belalcazar, Kevin Andres Rosero Mestizo, Luis Eduardo Vera Orejuela |
| Sprint Goal | Habilitar el registro inicial de agricultores del Valle del Cauca y la consulta filtrada del catálogo agrícola, validando la persistencia en PostgreSQL y la arquitectura REST. |
| Capacidad comprometida | 10 Story Points |
| Puntos completados | 10 / 10 |

---

## 1. Resultado por historia

| Historia | Puntos | Estado | Pull Request | Revisor |
|---|---|---|---|---|
| **HU-01:** Registro de Agricultores | 5 | Terminada | PR #1 (`feature/HU01-registro-productor`) | Reinaldo Daniel Niño Belalcazar |
| **HU-07:** Consulta de Perfil de Agricultor | 2 | Terminada | PR #2 (`feature/HU07-consulta-perfil`) | Jhon Stiven Rivas Angulo |
| **HU-04:** Filtro de Productos por Municipio y Categoría | 3 | Terminada | PR #3 (`feature/HU04-filtro-productos`) | Kevin Andres Rosero Mestizo |

---

## 2. Evidencia del incremento ejecutable

### HU-01 — `POST /api/v1/auth/register`

| Escenario BDD | Petición | Resultado esperado | Evidencia |
|---|---|---|---|
| **Registro exitoso** | Nombre, cédula válida y municipio del Valle | `201 Created` | Respuesta JSON con objeto Productor y estado `ACTIVO` (Validado en Postman) |
| **Dato obligatorio faltante** | Petición sin campo `nombre` | `400 Bad Request` | Cuerpo de error estructurado con mensaje de validación Bean Validation |
| **Cédula duplicada** | Cédula previamente registrada | `409 Conflict` | Mensaje de excepción controlada por `@RestControllerAdvice` |
| **Persistencia** | Verificación en base de datos PostgreSQL | Fila creada | Registro persistido correctamente en la tabla `usuarios_productores` |

### HU-07 — `GET /api/v1/productores/{id}`

| Escenario BDD | Resultado esperado | Evidencia |
|---|---|---|
| **Perfil existente** | `200 OK`, DTO de consulta retornado | Respuesta HTTP 200 con campos `id`, `nombre` y `ubicacionValle` (cédula omitida por privacidad) |
| **Perfil inexistente** | `404 Not Found` | Mensaje de error `ProductorNoEncontradoException` traducido a HTTP 404 |

### HU-04 — `GET /api/v1/productos?municipio=Dagua&categoria=Frutas`

| Escenario BDD | Resultado esperado | Evidencia |
|---|---|---|
| **Filtro con coincidencias** | `200 OK` y arreglo JSON con productos | Arreglo JSON retornado con las ofertas disponibles asociadas a Dagua y Frutas |
| **Filtro sin coincidencias** | `200 OK` y arreglo vacío `[]` | Arreglo JSON vacío retornado indicando ausencia de coincidencias sin generar error |

---

## 3. Verificación de calidad (Definition of Done)

| Criterio | Resultado | Evidencia |
|---|---|---|
| **Build:** `mvn clean install` sin errores | Cumplido | Compilación exitosa del proyecto en Java 17 / Spring Boot |
| **Linter:** `mvn checkstyle:check` | Cumplido | 0 advertencias estáticas bajo el estilo Google Java Style |
| **Pruebas:** `mvn test` en JUnit 5 | Cumplido | 100% de las pruebas unitarias y de integración en verde |
| **Cobertura:** Cobertura JaCoCo $\ge$ 60% | Cumplido | Cobertura verificada mediante reporte generado en `target/site/jacoco` |
| **CI/CD:** Pipeline de GitHub Actions | Cumplido | Integración continua ejecutada con éxito en las ramas fusionadas |
| **Code Review:** Revisión cruzada por pares | Cumplido | Todos los Pull Requests cuentan con al menos una aprobación explícita |

---

## 4. Retroalimentación del Product Owner

> **Reinaldo Daniel Niño Belalcazar (Product Owner):** "El incremento entregado cumple con las expectativas del Sprint Goal. Se validó la correcta integración de la API REST con la base de datos PostgreSQL y la separación estricta de DTOs para resguardar información sensible (cédula). El producto cuenta con una base sólida para iniciar el Sprint 2 enfocado en la publicación y reserva de cosechas."

---

## 5. Trabajo pendiente para el siguiente sprint

No quedaron tareas ni historias pendientes del Sprint 1. Para el **Sprint 2** se continuará con la implementación del catálogo de publicaciones y la gestión de pedidos directos (HU-02 y HU-05).