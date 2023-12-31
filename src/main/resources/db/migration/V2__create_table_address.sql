CREATE TABLE IF NOT EXISTS address
(
    id           uuid PRIMARY KEY NOT NULL,
    street       VARCHAR(255)     NOT NULL,
    neighborhood VARCHAR(255)     NOT NULL,
    postal_code  VARCHAR(8)       NOT NULL,
    city         VARCHAR(255)     NOT NULL,
    uf           VARCHAR(2)       NOT NULL,
    number       VARCHAR(255),
    complement   VARCHAR(255)
);

ALTER TABLE IF EXISTS doctors
    ADD address_id uuid,
    ADD CONSTRAINT fk_address_id FOREIGN KEY (address_id) REFERENCES address (id);