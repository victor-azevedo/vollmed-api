ALTER TABLE IF EXISTS doctors
    ADD COLUMN is_active BOOLEAN;

UPDATE doctors
SET is_active = TRUE
WHERE TRUE;