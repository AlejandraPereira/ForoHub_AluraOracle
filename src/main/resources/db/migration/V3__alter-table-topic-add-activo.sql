ALTER TABLE topico
ALTER COLUMN activo
SET DATA TYPE boolean
USING (activo = 1);
