USE conference;

CREATE TABLE track
(
    id UUID NOT NULL PRIMARY KEY,
    title VARCHAR(45) NOT NULL,
    session_id UUID,
    participant_id UUID,
    FOREIGN KEY (session_id) REFERENCES session(id),
    FOREIGN KEY (participant_id) REFERENCES participant(id)
);