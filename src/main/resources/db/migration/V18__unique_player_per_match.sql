ALTER TABLE player_match
ADD CONSTRAINT unique_player_match
UNIQUE (player_id, match_id);
