-- 1. Cambiar columna 'state' de tipo enum a varchar temporalmente
ALTER TABLE match
ALTER COLUMN state DROP DEFAULT,
ALTER COLUMN state TYPE VARCHAR(20);

-- 2. Eliminar tipo ENUM match_state si existe
DO $$ BEGIN
    IF EXISTS (SELECT 1 FROM pg_type WHERE typname = 'match_state') THEN
        DROP TYPE match_state;
    END IF;
END $$;

-- 3. Agregar restricción CHECK a 'state'
ALTER TABLE match
ADD CONSTRAINT chk_match_state
CHECK (state IN ('PENDING', 'SUSPENDED', 'STARTED', 'FINISHED'));

-- 4. Establecer valor por defecto (opcional)
ALTER TABLE match
ALTER COLUMN state SET DEFAULT 'PENDING';