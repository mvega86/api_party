-- Crear ENUM personalizado en PostgreSQL si no existe
DO $$ BEGIN
    IF NOT EXISTS (SELECT 1 FROM pg_type WHERE typname = 'match_state') THEN
        CREATE TYPE match_state AS ENUM ('PENDING', 'IN_PROGRESS', 'STARTED', 'FINISHED');
    END IF;
END $$;

-- Agregar columna 'state' a la tabla match con valor por defecto
ALTER TABLE match
ADD COLUMN IF NOT EXISTS state match_state DEFAULT 'PENDING';