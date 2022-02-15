USE conference;

CREATE TABLE track
(
    track_id BINARY(16) NOT NULL PRIMARY KEY,
    title VARCHAR(45) NOT NULL,
    session_id BINARY(16),
    FOREIGN KEY (session_id) REFERENCES session(session_id)
);