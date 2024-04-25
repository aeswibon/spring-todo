CREATE TABLE IF NOT EXISTS todo (
    uuid UUID PRIMARY KEY,
    description VARCHAR(255) NOT NULL,
    status BOOLEAN,
    project_uuid UUID,
    created_at TIMESTAMP,
    updated_at TIMESTAMP,
    FOREIGN KEY (project_uuid) REFERENCES project(uuid)
);

CREATE INDEX uuidStatusIndex ON todo (uuid, status);