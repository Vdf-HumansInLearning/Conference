USE conference;

CREATE TABLE day
(
    id BINARY(16) NOT NULL PRIMARY KEY,
    date TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
    conference_id BINARY(16),
    track_id BINARY(16),
    FOREIGN KEY (conference_id) REFERENCES conference(id),
    FOREIGN KEY (track_id) REFERENCES track(id)
);