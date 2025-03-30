-- Añadir columna location a match (si aún no se aplicó)
ALTER TABLE match
ADD COLUMN IF NOT EXISTS location VARCHAR(255);

-- =========================
-- Tabla diccionario: statistic_location
-- =========================
CREATE TABLE IF NOT EXISTS statistic_location (
    id SERIAL PRIMARY KEY,
    name VARCHAR(100) NOT NULL UNIQUE
);

-- Valores iniciales
INSERT INTO statistic_location (name) VALUES
('Fuera del área'),
('Dentro del área'),
('Punto de penalti'),
('Tiro de esquina'),
('Centro del campo');

-- =========================
-- Tabla diccionario: statistic_way
-- =========================
CREATE TABLE IF NOT EXISTS statistic_way (
    id SERIAL PRIMARY KEY,
    name VARCHAR(100) NOT NULL UNIQUE
);

-- Valores iniciales
INSERT INTO statistic_way (name) VALUES
('Pie derecho'),
('Pie izquierdo'),
('Cabeza'),
('Rodilla');

-- =========================
-- Relación con player_statistic
-- =========================
-- Añadir columnas opcionales
ALTER TABLE player_statistic
ADD COLUMN IF NOT EXISTS statistic_location_id INT,
ADD COLUMN IF NOT EXISTS statistic_way_id INT;

-- Claves foráneas
ALTER TABLE player_statistic
ADD CONSTRAINT fk_statistic_location
FOREIGN KEY (statistic_location_id)
REFERENCES statistic_location(id);

ALTER TABLE player_statistic
ADD CONSTRAINT fk_statistic_way
FOREIGN KEY (statistic_way_id)
REFERENCES statistic_way(id);
