====================================================================
PROYECTO: AGROVALLE CONNECT
Documentacion del Repositorio
====================================================================

--------------------------------------------------------------------
1. DECLARACION DE LA VISION DEL PRODUCTO
--------------------------------------------------------------------
Para: los pequenos y medianos productores agricolas del Valle del
      Cauca,

Que:  necesitan vender sus cosechas de forma directa sin caer en
      margenes comerciales abusivos por intermediacion,

AgroValle Connect es: una aplicacion web empresarial basada en
      Java 17 / Spring Boot,

Que:  conecta la oferta agricola local con la demanda comercial e
      industrial urbana, a precios fair trade y transparentes,

A diferencia de: las cadenas tradicionales de intermediarios,

Nuestro producto: garantiza trazabilidad en tiempo real, contratos
      de API estandarizados y acceso equitativo a metricas de
      mercado regional.

------------------------------------------------------------------
2. INTEGRANTES DEL EQUIPO
-----------------------------------------------------------------
Nombre completo                            | Rol                | GitHub
-------------------------------------------|---------------------
Jhon Stiven Rivas Angulo                   | Scrum Master       | [@JhonRivas1788]
Reinaldo Daniel Niño Belalcazar            | Product Owner      | [@rdninoEstudiante]
Kevin Andres Rosero Mestizo                | Desarrollador      | [@KevinnRosero]
Luis Eduardo Vera Orejuela                 | Desarrollador      | [@leduardovera]  

--------------------------------------------------------------------
3. ESTRATEGIA DE RAMIFICACION Y JUSTIFICACION
--------------------------------------------------------------------
Estrategia seleccionada: Trunk-Based Development (con integracion
continua)

Justificacion Tecnica:
Para el desarrollo de AgroValle Connect se opto por Trunk-Based
Development con integracion continua (CI) en lugar de GitFlow. Las
razones principales son:

1. Agilidad y simplicidad: al trabajar en ciclos rapidos iterativos
   (Sprint 0 a Sprint N), GitFlow anade complejidad innecesaria con
   ramas de larga duracion (develop, release, hotfix), que no aportan
   valor en un equipo pequeno con entregas frecuentes.

2. Integracion continua eficiente: Trunk-Based promueve ramas de
   caracteristicas (feature branches) de corta vida que se integran
   rapidamente a 'main' mediante Pull Requests (PR) revisados con
   analisis estatico de codigo (Checkstyle/Maven).

3. Reduccion de tiempos de espera: al no depender de ramas
   intermedias de larga duracion (develop/release), ningun
   desarrollador debe esperar una fusion intermedia para integrar su
   trabajo. Cada Historia de Usuario se fusiona a 'main' apenas pasa
   el Pull Request y el pipeline de CI, reduciendo los tiempos
   muertos entre tareas del equipo.

4. Prevencion de conflictos de fusion (Merge Conflicts): al fusionar
   cambios pequenos y frecuentes, se reduce significativamente el
   impacto de grandes colisiones de codigo entre las Historias de
   Usuario.

--------------------------------------------------------------------
4. DIAGRAMA DE LA ESTRATEGIA DE RAMAS (Mermaid.js)
--------------------------------------------------------------------
```mermaid   
gitGraph

       commit id: "v0.0.1-Init" tag: "v0.0.1"
       commit id: "Config-pom-checkstyle"
       branch feature/HU-01-registro-agricultor
       checkout feature/HU-01-registro-agricultor
       commit id: "feat: entidad usuario y DTO"
       commit id: "feat: endpoint /auth/register"
       checkout main
       merge feature/HU-01-registro-agricultor id: "PR #1: Registro completado"
       branch feature/HU-02-publicacion-cosechas
       checkout feature/HU-02-publicacion-cosechas
       commit id: "feat: modelo cosecha y validacion fecha"
       checkout main
       merge feature/HU-02-publicacion-cosechas id: "PR #2: Publicacion cosechas"
       commit id: "v0.1.0-Sprint-0" tag: "v0.1.0"

```

====================================================================