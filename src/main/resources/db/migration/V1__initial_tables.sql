CREATE TYPE specialities_type AS ENUM (
    'ORTOPEDIA',
    'CARDIOLOGIA',
    'DERMATOLOGIA',
    'GINECOLOGIA',
    'UROLOGIA',
    'OFTALMOLOGIA');

CREATE TABLE IF NOT EXISTS doctors
(
    id         uuid PRIMARY KEY              NOT NULL,
    crm        character varying(255) UNIQUE NOT NULL,
    email      character varying(255) UNIQUE NOT NULL,
    name       character varying(255) UNIQUE NOT NULL,
    phone      character varying(255) UNIQUE NOT NULL,
    speciality specialities_type             NOT NULL
);


