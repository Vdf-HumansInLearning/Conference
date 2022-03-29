USE conference;

CREATE TABLE ticket
(
    id UUID NOT NULL PRIMARY KEY,
    participant_id BINARY(16),
    type ENUM('COMPLETE_ONLINE_EXPERIENCE', 'COMPLETE_ON_SITE_EXPERIENCE', 'ONLINE_EXPERIENCE', 'ON_SITE_EXPERIENCE'),
    price ENUM('200', '250', '300', '350')

    /*FOREIGN KEY (participant_id) REFERENCES participant(id)*/
);