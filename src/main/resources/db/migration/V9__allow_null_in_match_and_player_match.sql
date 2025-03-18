-- Permitir NULL en los tiempos del partido
ALTER TABLE match
ALTER COLUMN start_first_time DROP NOT NULL,
ALTER COLUMN end_first_time DROP NOT NULL,
ALTER COLUMN start_second_time DROP NOT NULL,
ALTER COLUMN end_second_time DROP NOT NULL,
ALTER COLUMN start_first_extra_time DROP NOT NULL,
ALTER COLUMN end_first_extra_time DROP NOT NULL,
ALTER COLUMN start_second_extra_time DROP NOT NULL,
ALTER COLUMN end_second_extra_time DROP NOT NULL;

-- Permitir NULL en los tiempos de entrada y salida de los jugadores en el partido
ALTER TABLE player_match
ALTER COLUMN in_ DROP NOT NULL,
ALTER COLUMN out DROP NOT NULL;
