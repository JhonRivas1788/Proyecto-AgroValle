-- DDL para PostgreSQL normalizado en 3FN
CREATE TABLE usuarios_productores (
    id BIGSERIAL PRIMARY KEY,
    cedula VARCHAR(20) UNIQUE NOT NULL,
    nombre VARCHAR(100) NOT NULL,
    municipio VARCHAR(50) NOT NULL,
    fecha_registro TIMESTAMP DEFAULT CURRENT_TIMESTAMP
);

CREATE TABLE ofertas_cosechas (
    id BIGSERIAL PRIMARY KEY,
    productor_id BIGINT NOT NULL,
    producto VARCHAR(100) NOT NULL,
    cantidad_kg DECIMAL(10, 2) NOT NULL,
    precio_kg DECIMAL(10, 2) NOT NULL,
    CONSTRAINT fk_productor FOREIGN KEY (productor_id) REFERENCES usuarios_productores(id) ON DELETE CASCADE
);