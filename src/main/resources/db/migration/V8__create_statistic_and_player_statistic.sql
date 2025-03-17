-- Crear tabla statistic (Diccionario de estadísticas)
CREATE TABLE statistic (
    id BIGSERIAL PRIMARY KEY,
    name VARCHAR(50) NOT NULL UNIQUE,
    description VARCHAR(255),
    unit VARCHAR(10)
);

-- Eliminar datos de prueba de match_statistic antes de renombrarla
DELETE FROM match_statistic;

-- Renombrar la tabla match_statistic a player_statistic
ALTER TABLE match_statistic RENAME TO player_statistic;

-- Eliminar columnas obsoletas si existen (por ejemplo, nombres en texto que ahora se manejan con claves foráneas)
ALTER TABLE player_statistic DROP COLUMN statistic_name;

-- Agregar nueva clave foránea hacia la tabla statistic
ALTER TABLE player_statistic ADD COLUMN statistic_id BIGINT NOT NULL;
ALTER TABLE player_statistic ADD CONSTRAINT fk_player_statistic_statistic FOREIGN KEY (statistic_id) REFERENCES statistic(id);

-- Agregar campos de auditoría a la tabla statistic
ALTER TABLE statistic ADD COLUMN created_at TIMESTAMP DEFAULT now();
ALTER TABLE statistic ADD COLUMN updated_at TIMESTAMP DEFAULT now();
ALTER TABLE statistic ADD COLUMN modified_by VARCHAR(255);

-- Trigger para match
CREATE TRIGGER trigger_set_updated_at_statistic
BEFORE UPDATE ON statistic
FOR EACH ROW
EXECUTE FUNCTION set_updated_at();
