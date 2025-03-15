-- V6_create_table_match_statistic09032025.sql
CREATE TABLE match_statistic (
    id BIGSERIAL PRIMARY KEY,
    player_match_id BIGINT NOT NULL,
    statistic_name VARCHAR(50) NOT NULL,
    value DOUBLE PRECISION NOT NULL CHECK (value >= 0),
    unit VARCHAR(10),
    timestamp TIMESTAMP NOT NULL,
    FOREIGN KEY (player_match_id) REFERENCES player_match(id)
);
