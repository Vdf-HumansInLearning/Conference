USE conference;

CREATE TABLE conference
(
    id UUID NOT NULL PRIMARY KEY,
    location VARCHAR(45) NOT NULL,
    theme VARCHAR(45) NOT NULL,
    description VARCHAR(45) NOT NULL

    /*id uuid DEFAULT uuid_generate_v4() not null,
    description varchar(255) not null,
    location varchar(255) not null,
    theme varchar(255) not null,
    primary key (id)*/

    /*ticket_id UUID,
    participant_id UUID ,
    FOREIGN KEY(ticket_id) REFERENCES ticket(id),
    FOREIGN KEY(participant_id) REFERENCES participant(id)*/
);