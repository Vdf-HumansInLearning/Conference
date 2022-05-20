CREATE TABLE ticket
(
    id UUID NOT NULL PRIMARY KEY,
    type VARCHAR(45) NOT NULL,
    price INT NOT NULL
);

INSERT INTO ticket (id, type , price) VALUES (gen_random_uuid(), 'COMPLETE_ONLINE_EXPERIENCE', 200);
INSERT INTO ticket (id, type , price) VALUES (gen_random_uuid(), 'COMPLETE_ON_SITE_EXPERIENCE', 250);
INSERT INTO ticket (id, type , price) VALUES (gen_random_uuid(), 'ONLINE_EXPERIENCE', 300);
INSERT INTO ticket (id, type , price) VALUES (gen_random_uuid(), 'ON_SITE_EXPERIENCE', 350);