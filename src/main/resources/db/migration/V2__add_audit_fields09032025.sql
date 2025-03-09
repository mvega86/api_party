ALTER TABLE players ADD COLUMN created_at TIMESTAMP DEFAULT now();
ALTER TABLE players ADD COLUMN updated_at TIMESTAMP DEFAULT now();
ALTER TABLE players ADD COLUMN modified_by VARCHAR(255);

-- Crear una función para actualizar `updated_at`
CREATE OR REPLACE FUNCTION set_updated_at()
RETURNS TRIGGER AS $$
BEGIN
    NEW.updated_at = now();
    RETURN NEW;
END;
$$ LANGUAGE plpgsql;

-- Crear el trigger para actualizar `updated_at`
CREATE TRIGGER trigger_set_updated_at
BEFORE UPDATE ON players
FOR EACH ROW
EXECUTE FUNCTION set_updated_at();