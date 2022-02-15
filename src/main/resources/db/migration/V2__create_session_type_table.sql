USE conference;

CREATE TABLE session_type
(
    session_type_id BINARY(16) NOT NULL PRIMARY KEY,
    type ENUM('presentation', 'workshop', 'demo', 'coffee_break', 'lunch_break'),
    length ENUM('15', '30', '45', '90')
);