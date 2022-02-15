USE conference;

CREATE TABLE ticket
(
    ticket_id BINARY(16) NOT NULL PRIMARY KEY,
    type VARCHAR(15) NOT NULL
);