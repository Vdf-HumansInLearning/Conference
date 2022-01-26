USE conference;

CREATE TABLE organiser
(
    organiser_id INT NOT NULL AUTO_INCREMENT PRIMARY KEY,
    first_name VARCHAR(45) NULL,
    last_name VARCHAR(45) NULL,
    title VARCHAR(255) NULL,
    email VARCHAR(45) NULL,
    phone_number VARCHAR(15) NULL,
    username VARCHAR(45) NOT NULL,
    password VARCHAR(45) NOT NULL
);