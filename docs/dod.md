# Definition of Done (DoD) - AgroValle Connect

Contrato técnico basado en el estándar **ISO/IEC 25010**. Una tarea o historia de usuario se considera oficialmente "Terminada" (Done) al cumplir con:

- [ ] **Build Local**: El proyecto compila sin errores en Java 17 / Maven (`mvn clean install`).
- [ ] **Linter Pass (Mantenibilidad)**: Cero advertencias de Checkstyle (`mvn checkstyle:check`, basado en Google Java Style).
- [ ] **Functional Correctness**: El 100% de las pruebas unitarias existentes pasan (`mvn test`).
- [ ] **Cobertura de Código**: Cobertura mínima del 60% verificada con JaCoCo (`mvn verify`).
- [ ] **BDD**: Criterios de aceptación estructurados en formato Given-When-Then para cada historia.
- [ ] **Control de Versiones**: Commits bajo el estándar Conventional Commits (`feat:`, `fix:`, `docs:`, `test:`, `chore:`).
- [ ] **Ramas**: Ninguna tarea se desarrolla directamente sobre `main`; se usa una rama `feature/HU-xx-descripcion` por historia.
- [ ] **Peer Review**: Todo Pull Request revisado y aprobado por al menos un compañero de equipo antes de fusionar.
- [ ] **Automatización (Husky)**: Hooks de pre-commit activos que impiden el commit si falla el linter o las pruebas.
- [ ] **Integración Continua (CI)**: El pipeline de GitHub Actions (build + linter + tests) pasa en verde antes del merge.
- [ ] **Documentación**: El `README.md` y la documentación de `/docs` quedan actualizados con el cambio.
