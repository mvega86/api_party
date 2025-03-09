-- Crea la tabla players
CREATE TABLE players (
    id SERIAL PRIMARY KEY,
    full_name VARCHAR(255) NOT NULL,
    jersey_name VARCHAR(100),
    jersey_number INT CHECK (jersey_number > 0),
    birth_date DATE NOT NULL
);