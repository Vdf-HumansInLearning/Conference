CREATE TYPE types AS ENUM ('PRESENTATION', 'WORKSHOP', 'DEMO', 'COFFEE_BREAK', 'LUNCH_BREAK');
CREATE TYPE session_length AS ENUM ('1', '2', '3', '4');

CREATE TABLE session_type
(
    id UUID NOT NULL PRIMARY KEY,
    type types,
    length session_length
);