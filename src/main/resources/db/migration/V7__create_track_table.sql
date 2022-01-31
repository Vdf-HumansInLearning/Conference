USE conference;

CREATE TABLE track
(
    track_id INT NOT NULL AUTO_INCREMENT PRIMARY KEY,
    title VARCHAR(45) NOT NULL,
    session_id INT,
    FOREIGN KEY (session_id) REFERENCES session(session_id)
);