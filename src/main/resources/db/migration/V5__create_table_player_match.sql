-- V5_create_table_player_match09032025.sql
CREATE TABLE player_match (
    id BIGSERIAL PRIMARY KEY,
    match_id BIGINT NOT NULL,
    team_id BIGINT NOT NULL,
    player_id BIGINT NOT NULL,
    in_ TIMESTAMP NOT NULL,
    out TIMESTAMP,
    FOREIGN KEY (match_id) REFERENCES match(id),
    FOREIGN KEY (team_id) REFERENCES teams(id),
    FOREIGN KEY (player_id) REFERENCES players(id)
);
