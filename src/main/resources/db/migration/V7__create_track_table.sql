USE conference;

CREATE TABLE track
(
    track_id INT NOT NULL AUTO_INCREMENT PRIMARY KEY,
    session_id INT,
    FOREIGN KEY (session_id) REFERENCES session(session_id)
);