# Sprint 1 Retrospectiva — Start / Stop / Continue

| Campo | Valor |
|---|---|
| Fecha | 30/09/2026 |
| Facilitador | Jhon Stiven Rivas Angulo (Scrum Master) |
| Participantes | Jhon Stiven Rivas Angulo, Reinaldo Daniel Niño Belalcazar, Kevin Andres Rosero Mestizo, Luis Eduardo Vera Orejuela |

---

## 1. Start (empezar a hacer)

- Trabajar cada historia en una rama `feature/*` y fusionar únicamente mediante Pull Request con revisión por pares (Peer Review), evitando subir cambios directos sobre `main`.
- Redactar mensajes de commit bajo el estándar **Conventional Commits** (`feat`, `fix`, `test`, `docs`, `chore`) para mantener la trazabilidad del historial.
- Ejecutar `mvn checkstyle:check` y `mvn test` localmente antes de realizar el commit, sin depender únicamente de la barrera de Husky.
- Documentar las excepciones y respuestas HTTP directamente en la capa `@RestControllerAdvice` desde el inicio del desarrollo técnico de cada endpoint.

---

## 2. Stop (dejar de hacer)

- Dejar la configuración del entorno (`JAVA_HOME`, versión de Checkstyle y JDK 17) sin documentar en el `README.md`, lo cual ocasionó bloqueos iniciales al ejecutar los hooks de Husky.
- Mantener archivos duplicados o con extensiones erróneas en la estructura del repositorio (por ejemplo, el archivo `docs/dod.md.txt` junto al contrato oficial `docs/dod.md`).
- Acumular revisiones de Pull Requests al final del sprint; la falta de fluidez en Code Review saturó la columna del tablero Kanban.

---

## 3. Continue (seguir haciendo)

- Mantener activos los hooks de **Husky** y la auditoría de **Checkstyle** como barrera automática e innegociable de calidad en la fase de pre-commit.
- Utilizar el pipeline de **GitHub Actions (CI/CD)** para validar la compilación limpia, ejecución de linter y paso del 100% de las pruebas automatizadas en JUnit 5.
- Estimar mediante **Planning Poker** (escala Fibonacci) y mantener la alineación estricta entre las tareas del Sprint Planning y el tablero en GitHub Projects.

---

## 4. Hallazgos Principales

1. Se logró una integración exitosa del Sprint 0 y Sprint 1 cumpliendo con la capacidad de 10 Story Points, la persistencia en PostgreSQL con Spring Data JPA y la cobertura de pruebas unitarias/integradas en JUnit 5.
2. Es necesario mejorar el flujo operativo en GitHub Projects respetando los límites de trabajo en progreso (WIP Limits) en las columnas *In Progress* ($\le 3$) y *Code Review* ($\le 2$) para evitar cuellos de botella antes de mover tarjetas a *Done*.

---

## 5. Acciones de Mejora para el Sprint 2

| Acción | Responsable | Fecha límite |
|---|---|---|
| Documentar paso a paso la configuración del entorno Java 17 / Checkstyle en el `README.md` | Kevin Andres Rosero Mestizo | 02/10/2026 |
| Aplicar el flujo estricto de rama `feature/*` $\rightarrow$ Pull Request $\rightarrow$ Peer Review en el 100% de los cambios del repositorio | Jhon Stiven Rivas Angulo | Durante todo el Sprint 2 |
| Depurar el repositorio eliminando archivos residuales (`docs/dod.md.txt`) y carpetas del IDE no ignoradas | Luis Eduardo Vera Orejuela | 01/10/2026 |
| Fortalecer la suite de pruebas automatizadas con `@SpringBootTest` para verificar el 60% de cobertura en JaCoCo | Reinaldo Daniel Niño Belalcazar | 05/10/2026 |