USE conference;

CREATE TABLE participant
(
    id BINARY(16) NOT NULL PRIMARY KEY,
    first_name VARCHAR(45) NOT NULL,
    last_name VARCHAR(45) NOT NULL,
    title VARCHAR(45) NOT NULL,
    email VARCHAR(45) NOT NULL UNIQUE,
    phone_number VARCHAR(15) NOT NULL UNIQUE ,
    username VARCHAR(45) NOT NULL UNIQUE,
    password VARCHAR(45) NOT NULL,
    session_id BINARY(16),
    is_organiser TINYINT(1) DEFAULT 0,
    is_speaker TINYINT(1) DEFAULT 0,
    FOREIGN KEY(session_id) REFERENCES session(id)
);