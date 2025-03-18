-- Eliminar la relación entre team y player_match si existe
ALTER TABLE player_match DROP CONSTRAINT IF EXISTS fk_players_team;

-- Modificar la relación team_id en players para que sea nullable y tenga ON DELETE SET NULL
--ALTER TABLE player_match DROP COLUMN team_id;

ALTER TABLE player_match DROP CONSTRAINT IF EXISTS player_match_team_id_fkey;
ALTER TABLE player_match DROP CONSTRAINT IF EXISTS fk_player_match_team;
ALTER TABLE players DROP CONSTRAINT IF EXISTS fk_players_team;