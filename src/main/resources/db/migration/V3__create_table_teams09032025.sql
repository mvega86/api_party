-- Crear la tabla teams
CREATE TABLE teams (
    id SERIAL PRIMARY KEY,
    name VARCHAR(255) NOT NULL,
    acronym VARCHAR(255) NOT NULL,
    created_at TIMESTAMP DEFAULT now(),
    updated_at TIMESTAMP DEFAULT now(),
    modified_by VARCHAR(255)
);

-- Agregar la columna team_id a la tabla players para la relación 1 a muchos
ALTER TABLE players ADD COLUMN team_id BIGINT;
ALTER TABLE players ADD CONSTRAINT fk_team FOREIGN KEY (team_id) REFERENCES teams(id) ON DELETE SET NULL;

-- Crear función para actualizar updated_at automáticamente para todas las tablas
CREATE OR REPLACE FUNCTION set_updated_at()
RETURNS TRIGGER AS $$
BEGIN
    NEW.updated_at = now();
    RETURN NEW;
END;
$$ LANGUAGE plpgsql;

-- Agregar trigger para actualizar updated_at en teams
CREATE TRIGGER trigger_set_updated_at_teams
BEFORE UPDATE ON teams
FOR EACH ROW
EXECUTE FUNCTION set_updated_at();

-- Eliminar el trigger anterior si existía
DROP TRIGGER IF EXISTS trigger_set_updated_at ON players;

-- Agregar trigger para actualizar updated_at en players
CREATE TRIGGER trigger_set_updated_at_players
BEFORE UPDATE ON players
FOR EACH ROW
EXECUTE FUNCTION set_updated_at();

