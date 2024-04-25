CREATE TABLE IF NOT EXISTS _user (
    uuid UUID PRIMARY KEY,
    name VARCHAR(255),
    email VARCHAR(255),
    role VARCHAR(255) NOT NULL
);