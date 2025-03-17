-- Agregar campos de auditoría a la tabla match
ALTER TABLE match ADD COLUMN created_at TIMESTAMP DEFAULT now();
ALTER TABLE match ADD COLUMN updated_at TIMESTAMP DEFAULT now();
ALTER TABLE match ADD COLUMN modified_by VARCHAR(255);

-- Agregar campos de auditoría a la tabla player_match
ALTER TABLE player_match ADD COLUMN created_at TIMESTAMP DEFAULT now();
ALTER TABLE player_match ADD COLUMN updated_at TIMESTAMP DEFAULT now();
ALTER TABLE player_match ADD COLUMN modified_by VARCHAR(255);

-- Agregar campos de auditoría a la tabla match_statistic
ALTER TABLE match_statistic ADD COLUMN created_at TIMESTAMP DEFAULT now();
ALTER TABLE match_statistic ADD COLUMN updated_at TIMESTAMP DEFAULT now();
ALTER TABLE match_statistic ADD COLUMN modified_by VARCHAR(255);

-- Crear una función para actualizar `updated_at` automáticamente
CREATE OR REPLACE FUNCTION set_updated_at()
RETURNS TRIGGER AS $$
BEGIN
    NEW.updated_at = now();
    RETURN NEW;
END;
$$ LANGUAGE plpgsql;

-- Crear triggers para actualizar `updated_at` en cada tabla

-- Trigger para match
CREATE TRIGGER trigger_set_updated_at_match
BEFORE UPDATE ON match
FOR EACH ROW
EXECUTE FUNCTION set_updated_at();

-- Trigger para player_match
CREATE TRIGGER trigger_set_updated_at_player_match
BEFORE UPDATE ON player_match
FOR EACH ROW
EXECUTE FUNCTION set_updated_at();

-- Trigger para match_statistic
CREATE TRIGGER trigger_set_updated_at_match_statistic
BEFORE UPDATE ON match_statistic
FOR EACH ROW
EXECUTE FUNCTION set_updated_at();
