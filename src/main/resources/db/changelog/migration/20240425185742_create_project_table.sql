CREATE TABLE IF NOT EXISTS project (
    uuid UUID PRIMARY KEY,
    title VARCHAR(255) NOT NULL,
    _user_uuid UUID NOT NULL,
    created_at TIMESTAMP,
    updated_at TIMESTAMP,
    FOREIGN KEY (_user_uuid) REFERENCES _user(uuid)
);
