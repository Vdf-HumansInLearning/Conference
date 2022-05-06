-- CREATE TYPE ticket_type AS ENUM ('COMPLETE_ONLINE_EXPERIENCE', 'COMPLETE_ON_SITE_EXPERIENCE', 'ONLINE_EXPERIENCE', 'ON_SITE_EXPERIENCE');
-- CREATE TYPE ticket_price AS ENUM ('200', '250', '300', '350');

CREATE TABLE ticket
(
    id UUID NOT NULL PRIMARY KEY,
    type VARCHAR(45) NOT NULL,
    price INT NOT NULL
--     type ticket_type,
--     price ticket_price
);

INSERT INTO ticket (id, type , price) VALUES (gen_random_uuid(), 'COMPLETE_ONLINE_EXPERIENCE', 200);
INSERT INTO ticket (id, type , price) VALUES (gen_random_uuid(), 'COMPLETE_ON_SITE_EXPERIENCE', 250);
INSERT INTO ticket (id, type , price) VALUES (gen_random_uuid(), 'ONLINE_EXPERIENCE', 300);
INSERT INTO ticket (id, type , price) VALUES (gen_random_uuid(), 'ON_SITE_EXPERIENCE', 350);