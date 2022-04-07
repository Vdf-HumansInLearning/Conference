CREATE TABLE conference
(
    id UUID NOT NULL PRIMARY KEY,
    location VARCHAR(45) NOT NULL,
    theme VARCHAR(45) NOT NULL,
    description VARCHAR(45) NOT NULL,
    ticket_id UUID,
    FOREIGN KEY(ticket_id) REFERENCES ticket(id)
);