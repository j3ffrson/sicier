ALTER TABLE destination_inform
    ADD state VARCHAR(255);

ALTER TABLE destination_inform
DROP
COLUMN status;