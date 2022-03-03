USE conference;

CREATE TABLE session_type
(
    id BINARY(16) NOT NULL PRIMARY KEY,
    type ENUM('PRESENTATION', 'WORKSHOP', 'DEMO', 'COFFEE_BREAK', 'LUNCH_BREAK'),
    length ENUM('15', '30', '45', '90')
);