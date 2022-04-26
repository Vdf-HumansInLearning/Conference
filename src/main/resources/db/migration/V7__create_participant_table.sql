CREATE TABLE participant
(
    id UUID NOT NULL PRIMARY KEY,
    first_name VARCHAR(45) NOT NULL,
    last_name VARCHAR(45) NOT NULL,
    title VARCHAR(45) NOT NULL,
    email VARCHAR(45) NOT NULL UNIQUE,
    phone_number VARCHAR(15) NOT NULL UNIQUE ,
    username VARCHAR(45) NOT NULL UNIQUE,
    password VARCHAR(45) NOT NULL,
    session_id UUID,
    conference_id UUID,
    is_organiser boolean,
    is_speaker boolean,
    FOREIGN KEY(session_id) REFERENCES session(id),
    FOREIGN KEY(conference_id) REFERENCES conference(id)
);