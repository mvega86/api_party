-- V13__unique_jersey_per_team.sql
ALTER TABLE players
ADD CONSTRAINT unique_jersey_per_team
UNIQUE (team_id, jersey_number);
