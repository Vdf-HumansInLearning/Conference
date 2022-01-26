USE conference;

CREATE TABLE session
(
    session_id INT NOT NULL AUTO_INCREMENT PRIMARY KEY,
    title VARCHAR(45) NOT NULL,
    description VARCHAR(255) NOT NULL,
    type VARCHAR(45) NOT NULL,
    topic VARCHAR(45) NOT NULL,
    techlvl VARCHAR(45) NOT NULL,
    keywords VARCHAR(45) NOT NULL,
    length INT NOT NULL,
    date DATE NOT NULL,
    review INT NOT NULL
);