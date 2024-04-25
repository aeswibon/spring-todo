CREATE TABLE IF NOT EXISTS todo (
    uuid UUID PRIMARY KEY,
    description VARCHAR(255) NOT NULL,
    status BOOLEAN,
    project_uuid UUID,
    createdAt TIMESTAMP NOT NULL,
    updatedAt TIMESTAMP NOT NULL,
    FOREIGN KEY (project_uuid) REFERENCES project(uuid)
);

CREATE INDEX uuidStatusIndex ON todo (uuid, status);