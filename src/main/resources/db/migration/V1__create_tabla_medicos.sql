CREATE TABLE topico (
    topico_id BIGSERIAL PRIMARY KEY,
    titulo VARCHAR(200) NOT NULL,
    mensaje TEXT NOT NULL,
    fecha_creacion TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
    status VARCHAR(20) DEFAULT 'activo' CHECK (status IN ('solucionado', 'cerrado', 'activo')),
    autor BIGINT NOT NULL,
    curso BIGINT NOT NULL
);
