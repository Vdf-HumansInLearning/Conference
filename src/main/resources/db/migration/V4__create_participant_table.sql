USE conference;

CREATE TABLE participant
(
    participant_id BINARY(16) NOT NULL PRIMARY KEY,
    first_name VARCHAR(45) NOT NULL,
    last_name VARCHAR(45) NOT NULL,
    title VARCHAR(45) NOT NULL,
    email VARCHAR(45) NOT NULL,
    phone_number VARCHAR(15) NOT NULL,
    username VARCHAR(45) NOT NULL,
    password VARCHAR(45) NOT NULL,
    session_id BINARY(16),
    is_organiser TINYINT(1) DEFAULT 0,
    is_speaker TINYINT(1) DEFAULT 0,
    FOREIGN KEY(session_id) REFERENCES session(session_id)
);