USE conference;

CREATE TABLE speaker
(
    id BINARY(16) NOT NULL PRIMARY KEY,
    title VARCHAR(45) NOT NULL,
    company VARCHAR(45) NOT NULL,
    linkedin_acc VARCHAR(45) NOT NULL,
    twitter_acc VARCHAR(45) NOT NULL,
    github_acc VARCHAR(45) NOT NULL,
    biography VARCHAR(255) NOT NULL,
    session_id BINARY(16),
    participant_id BINARY(16),
    FOREIGN KEY (session_id) REFERENCES session(id),
    FOREIGN KEY (participant_id) REFERENCES participant(id)
);