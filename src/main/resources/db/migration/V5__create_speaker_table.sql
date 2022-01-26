USE conference;

CREATE TABLE speaker
(
    speaker_id INT NOT NULL AUTO_INCREMENT PRIMARY KEY,
    participant_id INT,
    title VARCHAR(45) NOT NULL,
    company VARCHAR(45) NOT NULL,
    linkedin_acc VARCHAR(45) NOT NULL,
    twitter_acc VARCHAR(45) NOT NULL,
    github_acc VARCHAR(45) NOT NULL,
    biography VARCHAR(255) NOT NULL,
    FOREIGN KEY (participant_id) REFERENCES participant(participant_id) ON DELETE CASCADE ON UPDATE CASCADE
);