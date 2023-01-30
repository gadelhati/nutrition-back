-- CREATING SCHEMA
CREATE SCHEMA IF NOT EXISTS nutrition;

-- POPULING nutrition.food
INSERT INTO nutrition.food (id, name) VALUES ('ba65d957-d0ab-426b-b23e-4b28e195c323', 'cereja') ON CONFLICT DO NOTHING;

-- POPULING nutrition.role
INSERT INTO nutrition.role(id, name) VALUES('7c12004d-e843-4e00-be40-01845ad75834', 'ROLE_USER') ON CONFLICT DO NOTHING;
INSERT INTO nutrition.role(id, name) VALUES('52c57a80-4e3b-4a41-a864-58d0cea25b14', 'ROLE_MODERATOR') ON CONFLICT DO NOTHING;
INSERT INTO nutrition.role(id, name) VALUES('8652ec73-0a53-433c-93be-420f1d90c681', 'ROLE_ADMIN') ON CONFLICT DO NOTHING;

-- POPULING nutrition.user
INSERT INTO nutrition.user_entity(id, username, password, email, active) VALUES ('b4c48fe3-4cf2-411d-9d4b-82f7c63eff9c', '00038059', '$2a$10$v5q8rJ5T/OlmZ2NKSYB2YOOxkn9AI1K04Bn9pemlEZTAMybsq6ona', 'marcelo.gadelha@marinha.mil.br', true) ON CONFLICT DO NOTHING;

-- POPULING nutrition.user_role
INSERT INTO nutrition.user_roles(user_id, role_id) VALUES ('b4c48fe3-4cf2-411d-9d4b-82f7c63eff9c', '7c12004d-e843-4e00-be40-01845ad75834') ON CONFLICT DO NOTHING;