USE conference;

CREATE TABLE day
(
    day_id BINARY(16) NOT NULL PRIMARY KEY,
    date TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
    conference_id BINARY(16),
    track_id BINARY(16),
    FOREIGN KEY (conference_id) REFERENCES conference(conference_id),
    FOREIGN KEY (track_id) REFERENCES track(track_id)
);