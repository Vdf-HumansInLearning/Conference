USE conference;

CREATE TABLE track
(
    id BINARY(16) NOT NULL PRIMARY KEY,
    title VARCHAR(45) NOT NULL,
    session_id BINARY(16),
    participant_id BINARY(16),
    FOREIGN KEY (session_id) REFERENCES session(id),
    FOREIGN KEY (participant_id) REFERENCES participant(id)
);