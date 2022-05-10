
--CREATE TYPE types AS ENUM ('PRESENTATION', 'WORKSHOP', 'DEMO', 'COFFEE_BREAK', 'LUNCH_BREAK');
--CREATE TYPE session_length AS ENUM ('LENGTH_15', 'LENGTH_30', 'LENGTH_45', 'LENGTH_90');

CREATE TABLE session_type
(
    id UUID NOT NULL PRIMARY KEY,
    type VARCHAR(25) NOT NULL,
    session_length INT NOT NULL
);

CREATE EXTENSION "pgcrypto";
INSERT INTO session_type (id, type, session_length) VALUES (gen_random_uuid(), 'PRESENTATION', 45);
INSERT INTO session_type (id, type, session_length) VALUES (gen_random_uuid(), 'WORKSHOP', 90);
INSERT INTO session_type (id, type, session_length) VALUES (gen_random_uuid(), 'DEMO', 30);
INSERT INTO session_type (id, type, session_length) VALUES (gen_random_uuid(), 'COFFEE_BREAK', 15);
INSERT INTO session_type (id, type, session_length) VALUES (gen_random_uuid(), 'LUNCH_BREAK', 30);
