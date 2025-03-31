-- 1. Añadir la columna 'stadium' con valor por defecto
ALTER TABLE teams
ADD COLUMN IF NOT EXISTS stadium VARCHAR(100) NOT NULL DEFAULT 'Stadiumless';

-- 2. Actualizar estadio del FCB
UPDATE teams
SET stadium = 'Estadi Olímpic Lluís Companys'
WHERE UPPER(acronym) = 'FCB';

-- 3. Actualizar estadio del Real Madrid
UPDATE teams
SET stadium = 'Santiago Bernabéu'
WHERE UPPER(acronym) = 'RMA';  -- Asumiendo que usas 'RMA' como acrónimo