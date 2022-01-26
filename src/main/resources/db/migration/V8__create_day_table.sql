USE conference;

CREATE TABLE day
(
    day_id INT NOT NULL AUTO_INCREMENT PRIMARY KEY,
    date TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
    conference_id INT,
    track_id INT,
    FOREIGN KEY (conference_id) REFERENCES conference(conference_id),
    FOREIGN KEY (track_id) REFERENCES track(track_id)
);