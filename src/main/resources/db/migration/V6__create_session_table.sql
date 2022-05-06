-- CREATE TYPE session_tech_level AS ENUM ('BEGINNER', 'MID_LEVEL', 'ADVANCED');

CREATE TABLE session
(
    id UUID NOT NULL PRIMARY KEY,
    title VARCHAR(45) NOT NULL,
    description VARCHAR(255) NOT NULL,
    session_type_id UUID,
    track_id UUID,
    topic VARCHAR(45) NOT NULL,
    tech_level VARCHAR(45) NOT NULL,
    keywords VARCHAR(45) NOT NULL,
    date TIMESTAMP NOT NULL,
    end_time TIMESTAMP NOT NULL,
    review INT NOT NULL,
    FOREIGN KEY(session_type_id) REFERENCES session_type(id),
    FOREIGN KEY(track_id) REFERENCES track(id)
);