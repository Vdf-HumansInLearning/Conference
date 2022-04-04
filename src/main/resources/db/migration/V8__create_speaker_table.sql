CREATE TABLE speaker
(
    id UUID NOT NULL PRIMARY KEY,
    title VARCHAR(45) NOT NULL,
    company VARCHAR(45) NOT NULL,
    linkedin_acc VARCHAR(45) NOT NULL,
    twitter_acc VARCHAR(45) NOT NULL,
    github_acc VARCHAR(45) NOT NULL,
    biography VARCHAR(255) NOT NULL,
    session_id UUID,
    participant_id UUID,
    FOREIGN KEY (session_id) REFERENCES session(id),
    FOREIGN KEY (participant_id) REFERENCES participant(id)
);