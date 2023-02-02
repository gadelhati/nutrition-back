-- CREATING SCHEMA
CREATE SCHEMA IF NOT EXISTS nutrition;

-- POPULING nutrition.food
INSERT INTO nutrition.food (id, name) VALUES ('ba65d957-d0ab-426b-b23e-4b28e195c323', 'cereja') ON CONFLICT DO NOTHING;

-- POPULING nutrition.food_category
INSERT INTO nutrition.food_category (id, name) VALUES ('e0ad567b-6d50-4f0d-9746-e60a276e5dc4', 'Cereais e leguminosas') ON CONFLICT DO NOTHING;
INSERT INTO nutrition.food_category (id, name) VALUES ('48c9f5ab-b1f8-4624-b07d-579d9ad8733b', 'Hortaliças tuberosas') ON CONFLICT DO NOTHING;
INSERT INTO nutrition.food_category (id, name) VALUES ('60fc881d-9dda-4948-aae5-aaf815e24948', 'Farinhas, féculas e massas') ON CONFLICT DO NOTHING;
INSERT INTO nutrition.food_category (id, name) VALUES ('84a62c39-e17f-430d-8834-a74321a899d4', 'Cocos, castanhas e nozes') ON CONFLICT DO NOTHING;
INSERT INTO nutrition.food_category (id, name) VALUES ('52266aba-7e68-400e-a65b-53cc89828a88', 'Hortaliças folhosas, frutosas e outras') ON CONFLICT DO NOTHING;
INSERT INTO nutrition.food_category (id, name) VALUES ('c0d350e1-1b01-4b2d-8cfa-14d5559f305a', 'Frutas') ON CONFLICT DO NOTHING;
INSERT INTO nutrition.food_category (id, name) VALUES ('3f161ce6-c5a3-43db-82e6-85674b240833', 'Açúcares e produtos de confeitaria ') ON CONFLICT DO NOTHING;
INSERT INTO nutrition.food_category (id, name) VALUES ('cafaf72a-880a-419c-944a-47d00d4f158e', 'Sais e condimentos') ON CONFLICT DO NOTHING;
INSERT INTO nutrition.food_category (id, name) VALUES ('0fbd0275-18a1-4c1f-b278-d4a7a682d0dc', 'Carnes e vísceras') ON CONFLICT DO NOTHING;
INSERT INTO nutrition.food_category (id, name) VALUES ('bc9f99f4-f9b6-42e5-97e9-719e18ca36e0', 'Pescados e frutos do mar') ON CONFLICT DO NOTHING;
INSERT INTO nutrition.food_category (id, name) VALUES ('2ea7f3a7-8d9a-47da-9655-f1d46b708304', 'Enlatados e conservas') ON CONFLICT DO NOTHING;
INSERT INTO nutrition.food_category (id, name) VALUES ('d6d72b87-6cfe-4258-adff-fcd6eea8e3cc', 'Aves e ovos') ON CONFLICT DO NOTHING;
INSERT INTO nutrition.food_category (id, name) VALUES ('6e51d63e-2df4-4243-941f-c02670462c57', 'Laticínios') ON CONFLICT DO NOTHING;
INSERT INTO nutrition.food_category (id, name) VALUES ('94dab297-a031-4266-8126-5e51c6789298', 'Panificados') ON CONFLICT DO NOTHING;
INSERT INTO nutrition.food_category (id, name) VALUES ('19197f9b-da8b-4333-9998-3e3201c98a58', 'Carnes industrializadas') ON CONFLICT DO NOTHING;
INSERT INTO nutrition.food_category (id, name) VALUES ('10b3e761-9279-4056-b366-9bb2c2fa92e0', 'Bebidas não alcoólicas e infusões') ON CONFLICT DO NOTHING;
INSERT INTO nutrition.food_category (id, name) VALUES ('7adb7b14-fae4-4e93-ad18-55b9b1df6c8e', 'Bebidas alcoólicas') ON CONFLICT DO NOTHING;
INSERT INTO nutrition.food_category (id, name) VALUES ('ab889209-5748-4273-a2b5-ba31a552eb47', 'Óleos e gorduras') ON CONFLICT DO NOTHING;
INSERT INTO nutrition.food_category (id, name) VALUES ('4c9fceb4-bfcb-4e62-a91e-7366df128998', 'Miscelâneas') ON CONFLICT DO NOTHING;

-- POPULING nutrition.role
INSERT INTO nutrition.role(id, name) VALUES('7c12004d-e843-4e00-be40-01845ad75834', 'ROLE_USER') ON CONFLICT DO NOTHING;
INSERT INTO nutrition.role(id, name) VALUES('52c57a80-4e3b-4a41-a864-58d0cea25b14', 'ROLE_MODERATOR') ON CONFLICT DO NOTHING;
INSERT INTO nutrition.role(id, name) VALUES('8652ec73-0a53-433c-93be-420f1d90c681', 'ROLE_ADMIN') ON CONFLICT DO NOTHING;

-- POPULING nutrition.user
INSERT INTO nutrition.user_entity(id, username, password, email, active) VALUES ('b4c48fe3-4cf2-411d-9d4b-82f7c63eff9c', '00038059', '$2a$10$v5q8rJ5T/OlmZ2NKSYB2YOOxkn9AI1K04Bn9pemlEZTAMybsq6ona', 'marcelo.gadelha@marinha.mil.br', true) ON CONFLICT DO NOTHING;

-- POPULING nutrition.user_role
INSERT INTO nutrition.user_roles(user_id, role_id) VALUES ('b4c48fe3-4cf2-411d-9d4b-82f7c63eff9c', '7c12004d-e843-4e00-be40-01845ad75834') ON CONFLICT DO NOTHING;