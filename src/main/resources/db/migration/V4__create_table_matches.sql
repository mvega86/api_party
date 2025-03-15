-- V4_create_table_matches09032025.sql
CREATE TABLE match (
    id BIGSERIAL PRIMARY KEY,
    home_team_id BIGINT NOT NULL,
    away_team_id BIGINT NOT NULL,
    start_first_time TIMESTAMP NOT NULL,
    end_first_time TIMESTAMP NOT NULL,
    start_second_time TIMESTAMP NOT NULL,
    end_second_time TIMESTAMP NOT NULL,
    start_first_extra_time TIMESTAMP,
    end_first_extra_time TIMESTAMP,
    start_second_extra_time TIMESTAMP,
    end_second_extra_time TIMESTAMP,
    FOREIGN KEY (home_team_id) REFERENCES teams(id),
    FOREIGN KEY (away_team_id) REFERENCES teams(id)
);
