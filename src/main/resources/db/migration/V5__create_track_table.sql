CREATE TABLE track
(
    id UUID NOT NULL PRIMARY KEY,
    title VARCHAR(45) NOT NULL,
    day_id UUID,
    conference_id UUID,
    FOREIGN KEY (day_id) REFERENCES day(id),
    FOREIGN KEY (conference_id) REFERENCES conference(id)
);