USE conference;

CREATE TABLE session
(
    session_id BINARY(16) NOT NULL PRIMARY KEY,
    title VARCHAR(45) NOT NULL,
    description VARCHAR(255) NOT NULL,
    session_type_id BINARY(16),
    topic VARCHAR(45) NOT NULL,
    techlvl ENUM('beginner', 'mid-level', 'advanced'),
    keywords VARCHAR(45) NOT NULL,
    length INT NOT NULL,
    date TIMESTAMP NOT NULL,
    end_time TIMESTAMP NOT NULL,
    review INT NOT NULL,
    FOREIGN KEY(session_type_id) REFERENCES session_type(session_type_id)
);