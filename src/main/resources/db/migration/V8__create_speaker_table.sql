CREATE TABLE speaker
(
    id UUID NOT NULL PRIMARY KEY,
    company VARCHAR(45) NOT NULL,
    linkedin_acc VARCHAR(45) NOT NULL,
    twitter_acc VARCHAR(45) NOT NULL,
    github_acc VARCHAR(45) NOT NULL,
    biography VARCHAR(255) NOT NULL,
    participant_id UUID,
    conference_id UUID,
    session_id UUID,
    FOREIGN KEY (participant_id) REFERENCES participant(id),
    FOREIGN KEY (conference_id) REFERENCES conference(id),
    FOREIGN KEY (session_id) REFERENCES session(id)
);