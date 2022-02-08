USE conference;

CREATE TABLE conference
(
    conference_id BINARY(16) NOT NULL PRIMARY KEY,
    location VARCHAR(45) NOT NULL,
    theme VARCHAR(45) NOT NULL,
    description VARCHAR(45) NOT NULL,
    ticket_id BINARY(16),
    participant_id BINARY(16),
    FOREIGN KEY(ticket_id) REFERENCES ticket(ticket_id),
    FOREIGN KEY(participant_id) REFERENCES participant(participant_id)
);