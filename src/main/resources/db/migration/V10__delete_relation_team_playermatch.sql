-- Revertir la eliminación de la relación team-player_match
ALTER TABLE player_match
ADD CONSTRAINT fk_player_match_team FOREIGN KEY (team_id) REFERENCES teams(id) ON DELETE CASCADE;

-- Restaurar la clave foránea team_id en players como obligatoria
ALTER TABLE players
ALTER COLUMN team_id SET NOT NULL;

-- Restaurar la restricción original en players
ALTER TABLE players
DROP CONSTRAINT IF EXISTS fk_players_team;

ALTER TABLE players
ADD CONSTRAINT fk_players_team FOREIGN KEY (team_id) REFERENCES teams(id) ON DELETE CASCADE;

