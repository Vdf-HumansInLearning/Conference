USE conference;

CREATE TABLE day
(
    id UUID NOT NULL PRIMARY KEY,
    date TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
    conference_id UUID,
    track_id UUID,
    FOREIGN KEY (conference_id) REFERENCES conference(id),
    FOREIGN KEY (track_id) REFERENCES track(id)
);