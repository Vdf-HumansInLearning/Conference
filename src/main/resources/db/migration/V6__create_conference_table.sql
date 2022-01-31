USE conference;

CREATE TABLE conference
(
    conference_id INT NOT NULL AUTO_INCREMENT PRIMARY KEY,
    location VARCHAR(45) NOT NULL,
    theme VARCHAR(45) NOT NULL,
    description VARCHAR(45) NOT NULL,
    schedule VARCHAR(45) NOT NULL,
    ticket_type VARCHAR(45) NOT NULL,
    participant_id INT,
    FOREIGN KEY(participant_id) REFERENCES participant(participant_id)
);