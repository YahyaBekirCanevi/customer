DROP TABLE IF EXISTS genre;

CREATE TABLE client_data (
    id uuid DEFAULT gen_random_uuid(),
    name VARCHAR(50) NOT NULL UNIQUE,
    password VARCHAR(50) NOT NULL,
    PRIMARY KEY (id)
);