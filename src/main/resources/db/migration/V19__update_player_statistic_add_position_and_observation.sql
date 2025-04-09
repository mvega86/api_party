-- Add columns for coordinates
ALTER TABLE player_statistic
ADD COLUMN position_x DOUBLE PRECISION,
ADD COLUMN position_y DOUBLE PRECISION;

-- Add column for observation
ALTER TABLE player_statistic
ADD COLUMN observation VARCHAR(255);

-- Delete value column if it exists
ALTER TABLE player_statistic
DROP COLUMN IF EXISTS value;
